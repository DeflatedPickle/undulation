package so.madprogrammer.keyboard;

import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class KeyboardLayout implements LayoutManager2 {

    private final Map<Component, KeyConstraint> mapComponents = new HashMap<>(25);
    private final Matrix<Integer, List<JComponent>> matrix = new Matrix<>();

    private Dimension gridSize;

    @Override
    public void addLayoutComponent(String name, Component comp) {
        throw new UnsupportedOperationException("addLayoutComponent(String, Comp) is not supported");
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        KeyConstraint kc = mapComponents.get(comp);
        mapComponents.remove(comp);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        int rowHeight = getRowHeight();
        Dimension size = new Dimension();
        size.width = getMaxRowWidth();
        size.height = rowHeight * matrix.getRowCount();
        return size;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    protected List<JComponent> getCellContents(Matrix<Integer, List<JComponent>> matrix, KeyConstraint constraint) {
        return getCellContents(matrix, constraint.column, constraint.row);
    }

    protected List<JComponent> getCellContents(Matrix<Integer, List<JComponent>> matrix, int col, int row) {
        if (!matrix.contains(col, row)) {
            matrix.add(col, row, new ArrayList<>());
        }
        return matrix.get(col, row);
    }

    protected Dimension getGridSize() {
        if (gridSize == null) {
            int maxCellWidth = 0;
            int maxCellHeight = 0;
            for (int row = 0; row < matrix.getRowCount(); row++) {
                for (int col = 0; col < matrix.getColumnCount(); col++) {
                    List<JComponent> cell = getCellContents(matrix, col, row);
                    int cellWidth = 0;
                    int cellHeight = 0;
                    for (JComponent comp : cell) {
                        KeyConstraint kc = mapComponents.get(comp);
                        if (kc.span == 1) {
                            cellWidth = Math.max(cellWidth, comp.getPreferredSize().width);
                        }
                        cellHeight = Math.max(cellHeight, comp.getPreferredSize().height);
                    }
                    maxCellWidth = Math.max(cellWidth, maxCellWidth);
                    maxCellHeight = Math.max(cellHeight, maxCellHeight);
                }
            }
            gridSize = new Dimension(maxCellWidth, maxCellHeight);
        }
        return gridSize;
    }

    protected int getRowHeight() {
        Dimension size = getGridSize();
        return size.height;
    }

    protected int getRowWidth(int row) {
        int rowWidth = 0;
        for (int col = 0; col < matrix.getColumnCount(); col++) {
            Dimension size = getCellSize(col, row);
            rowWidth += size.width;
        }
        return rowWidth;
    }

    protected int getMaxRowWidth() {
        int rowWidth = 0;
        for (int row = 0; row < matrix.getRowCount(); row++) {
            rowWidth = Math.max(getRowWidth(row), rowWidth);
        }
        return rowWidth;
    }

    protected int getColumnWidth(int col) {
        int width = 0;
        for (int row = 0; row < matrix.getRowCount(); row++) {

            Dimension size = getCellSize(col, row);
            width = Math.max(size.width, width);

        }
        return width;
    }

    protected Dimension getCellSize(int col, int row) {
        List<JComponent> comps = matrix.get(col, row);
        Dimension size = new Dimension();
        size.height = getRowHeight();
        for (JComponent comp : comps) {
            Dimension subSize = getCellSize(col, row, comp);
            size.width = Math.max(size.width, subSize.width);
        }

        return size;
    }

    protected Dimension getCellSize(int col, int row, JComponent comp) {
        List<JComponent> comps = matrix.get(col, row);
        Dimension size = new Dimension();
        size.height = getRowHeight();
        int defaultWidth = getGridSize().width;
        KeyConstraint kc = mapComponents.get(comp);
        if (kc.span == 1) {
            size.width = defaultWidth;
        } else {
            size.width = (int) Math.round(defaultWidth * kc.span);
        }

        return size;
    }

    @Override
    public void layoutContainer(Container parent) {
        int rowHeight = getRowHeight();
        int y = 0;
        for (int row = 0; row < matrix.getRowCount(); row++) {
            int x = 0;
            for (int col = 0; col < matrix.getColumnCount(); col++) {
                List<JComponent> comps = matrix.get(col, row);
                Rectangle bounds = new Rectangle();
                bounds.x = x;
                bounds.y = y;
                int maxWidth = 0;
                for (JComponent comp : comps) {

                    Dimension size = getCellSize(col, row, comp);
                    bounds.setSize(size);
                    maxWidth = Math.max(maxWidth, size.width);
                    comp.setBounds(bounds);

                }
                x += maxWidth;
            }
            y += rowHeight;
        }
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if (constraints instanceof KeyConstraint) {
            mapComponents.put(comp, (KeyConstraint) constraints);
            getCellContents(matrix, (KeyConstraint) constraints).add((JComponent) comp);
        }
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return preferredLayoutSize(target);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0.5f;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0.5f;
    }

    @Override
    public void invalidateLayout(Container target) {
        gridSize = null;
    }

    public static class Matrix<I, O> {

        private Map<I, Map<I, O>> mapRows;

        public Matrix() {
        }

        protected Map<I, Map<I, O>> getRowMap() {
            if (mapRows == null) {
                mapRows = new HashMap<>(25);
            }
            return mapRows;
        }

        protected Map<I, O> getColumnMap(I row) {
            Map<I, Map<I, O>> rowMap = getRowMap();
            return rowMap.computeIfAbsent(row, k -> new HashMap<>(25));
        }

        public void add(I col, I row, O obj) {
            Map<I, O> columnMap = getColumnMap(row);
            columnMap.put(col, obj);
        }

        public void remove(I col, I row, O obj) {
            if (contains(col, row)) {
                Map<I, O> columnMap = getColumnMap(row);
                columnMap.put(col, obj);
            }
        }

        public void removeColumn(I col) {
            for (I row : getRowMap().keySet()) {
                Map<I, O> columnMap = getRowMap().get(row);
                if (columnMap != null) {
                    columnMap.remove(col);
                }
            }
        }

        public void removeRow(I row) {
            getRowMap().remove(row);
        }

        public int getRowCount() {
            return getRowMap().size();
        }

        public int getColumnCount() {
            int max = 0;
            for (I row : getRowMap().keySet()) {
                Map<I, O> mapColumns = getRowMap().get(row);
                max = Math.max(mapColumns.size(), max);
            }
            return max;
        }

        protected boolean containsRow(I row) {
            return getRowMap().containsKey(row);
        }

        protected boolean containsColumn(I col) {
            boolean contains = false;
            for (I row : getRowMap().keySet()) {
                Map<I, O> columnMap = getRowMap().get(row);
                if (columnMap != null && columnMap.containsKey(col)) {
                    contains = true;
                    break;
                }
            }
            return contains;
        }

        public boolean contains(I col, I row) {
            boolean contains = false;
            Map<I, O> colMap = getRowMap().get(row);
            if (colMap != null) {
                if (colMap.containsKey(col)) {
                    contains = true;
                }
            }

            return contains;
        }

        public O get(I col, I row) {
            O value = null;
            if (contains(col, row)) {
                Map<I, O> columnMap = getRowMap().get(row);
                value = columnMap.get(col);
            }
            return value;
        }

        public boolean contains(O value) {
            boolean contains = false;
            for (I row : getRowMap().keySet()) {
                Map<I, O> mapColumns = getRowMap().get(row);
                for (I col : mapColumns.keySet()) {
                    if (mapColumns.containsValue(value)) {
                        contains = true;
                        break;
                    }
                }
            }
            return contains;
        }

        public boolean rowContains(I row, O value) {
            boolean contains = false;
            Map<I, O> mapColumns = getRowMap().get(row);
            for (I col : mapColumns.keySet()) {
                if (mapColumns.containsValue(value)) {
                    contains = true;
                    break;
                }
            }
            return contains;
        }

        public boolean columnContains(I column, O value) {
            boolean contains = false;
            for (I row : getRowMap().keySet()) {
                Map<I, O> mapColumns = getRowMap().get(row);
                O colValue = mapColumns.get(column);
                if (colValue == value) {
                    contains = true;
                    break;
                }
            }
            return contains;
        }

        public O[] rowToArray(I row, O[] values) {
            List<O> lstValues = new ArrayList<>(25);
            Map<I, O> mapColumns = getRowMap().get(row);
            lstValues.addAll(mapColumns.values());
            return lstValues.toArray(values);
        }

        public O[] columnToArray(I col, O[] values) {
            List<O> lstValues = new ArrayList<>(25);
            for (I row : getRowMap().keySet()) {
                Map<I, O> mapCols = getRowMap().get(row);
                lstValues.add(mapCols.get(col));
            }
            return lstValues.toArray(values);
        }

        public Iterator<O> columnIterator(I col) {
            List<O> lstValues = new ArrayList<>(25);
            for (I row : getRowMap().keySet()) {
                Map<I, O> mapCols = getRowMap().get(row);
                lstValues.add(mapCols.get(col));
            }
            return lstValues.iterator();
        }

        public Iterator<O> rowIterator(I row) {
            List<O> lstValues = new ArrayList<>(25);
            Map<I, O> mapColumns = getRowMap().get(row);
            lstValues.addAll(mapColumns.values());
            return lstValues.iterator();
        }
    }
}
