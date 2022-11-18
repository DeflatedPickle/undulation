package so.n0weak;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Component;
import java.awt.Font;

public class ExtendedComboBox extends JComboBox {
    public ExtendedComboBox() {
        setModel(new ExtendedComboBoxModel());
        setRenderer(new ExtendedListCellRenderer());
    }

    public void addDelimiter(String text) {
        this.addItem(new Delimiter(text));
    }

    private static class ExtendedComboBoxModel extends DefaultComboBoxModel {
        @Override
        public void setSelectedItem(Object anObject) {
            if (!(anObject instanceof Delimiter)) {
                super.setSelectedItem(anObject);
            } else {
                int index = getIndexOf(anObject);
                if (index < getSize()) {
                    setSelectedItem(getElementAt(index + 1));
                }
            }
        }

    }

    private static class ExtendedListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus
        ) {
            if (!(value instanceof Delimiter)) {
                return super.getListCellRendererComponent(list, value, index,
                        isSelected, cellHasFocus);
            } else {
                JLabel label = new JLabel(value.toString());
                Font f = label.getFont();
                label.setFont(f.deriveFont(f.getStyle()
                        | Font.BOLD | Font.ITALIC, 16));
                return label;
            }
        }
    }

    private static class Delimiter {
        private final String text;

        private Delimiter(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
