package so.madprogrammer.keyboard;

import org.jetbrains.annotations.Nullable;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.function.Supplier;
import java.util.stream.Stream;

// https://stackoverflow.com/a/24625704
public class Keyboard<T extends AbstractButton> extends JPanel {
    private Supplier<T> supplier;

    private ButtonGroup characterGroup = new ButtonGroup();
    private ButtonGroup modifierGroup = new ButtonGroup();

    private KeyItem selectedKey;
    private KeyItem selectedModifier;

    private static final KeyboardItem[][] keys = new KeyboardItem[][]{
            {
                    createKey("`", KeyEvent.VK_BACK_QUOTE, 0, 0),
                    createKey("1", KeyEvent.VK_1, 0, 1),
                    createKey("2", KeyEvent.VK_2, 0, 2),
                    createKey("3", KeyEvent.VK_3, 0, 3),
                    createKey("4", KeyEvent.VK_4, 0, 4),
                    createKey("5", KeyEvent.VK_5, 0, 5),
                    createKey("6", KeyEvent.VK_6, 0, 6),
                    createKey("7", KeyEvent.VK_7, 0, 7),
                    createKey("8", KeyEvent.VK_8, 0, 8),
                    createKey("9", KeyEvent.VK_9, 0, 9),
                    createKey("0", KeyEvent.VK_0, 0, 10),
                    createKey("-", KeyEvent.VK_MINUS, 0, 11),
                    createKey("=", KeyEvent.VK_EQUALS, 0, 12),
                    createKey("Backspace", KeyEvent.VK_BACK_SPACE, 0, 14, 1.5d)
            },
            {
                    createKey("Tab", KeyEvent.VK_TAB, 1, 0, 1.2d),
                    createKey("Q", KeyEvent.VK_W, 1, 1),
                    createKey("W", KeyEvent.VK_W, 1, 2),
                    createKey("E", KeyEvent.VK_E, 1, 3),
                    createKey("R", KeyEvent.VK_R, 1, 4),
                    createKey("T", KeyEvent.VK_T, 1, 5),
                    createKey("Y", KeyEvent.VK_Y, 1, 6),
                    createKey("U", KeyEvent.VK_U, 1, 7),
                    createKey("I", KeyEvent.VK_I, 1, 8),
                    createKey("O", KeyEvent.VK_O, 1, 9),
                    createKey("P", KeyEvent.VK_P, 1, 10),
                    createKey("[", KeyEvent.VK_OPEN_BRACKET, 1, 11),
                    createKey("]", KeyEvent.VK_CLOSE_BRACKET, 1, 12),
            },
            {
                    createKey("Caps", KeyEvent.VK_CAPS_LOCK, 2, 0, 1.5d),
                    createKey("A", KeyEvent.VK_A, 2, 2),
                    createKey("S", KeyEvent.VK_S, 2, 3),
                    createKey("D", KeyEvent.VK_D, 2, 4),
                    createKey("F", KeyEvent.VK_F, 2, 5),
                    createKey("G", KeyEvent.VK_G, 2, 6),
                    createKey("H", KeyEvent.VK_H, 2, 7),
                    createKey("J", KeyEvent.VK_J, 2, 8),
                    createKey("K", KeyEvent.VK_K, 2, 9),
                    createKey("L", KeyEvent.VK_L, 2, 10),
                    createKey(";", KeyEvent.VK_SEMICOLON, 2, 11),
                    createKey("'", KeyEvent.VK_QUOTE, 2, 12),
                    createKey("#", KeyEvent.VK_NUMBER_SIGN, 2, 13),
                    createKey("Enter", KeyEvent.VK_ENTER, 2, 14)
            },
            {
                    createKey("Shift", KeyEvent.VK_SHIFT, KeyType.MODIFIER, 3, 0, 1.2d),
                    createKey("\\", KeyEvent.VK_BACK_SLASH, 3, 1),
                    createKey("Z", KeyEvent.VK_Z, 3, 2),
                    createKey("X", KeyEvent.VK_X, 3, 3),
                    createKey("C", KeyEvent.VK_C, 3, 4),
                    createKey("V", KeyEvent.VK_V, 3, 5),
                    createKey("B", KeyEvent.VK_B, 3, 6),
                    createKey("N", KeyEvent.VK_N, 3, 7),
                    createKey("M", KeyEvent.VK_M, 3, 8),
                    createKey(",", KeyEvent.VK_COMMA, 3, 9),
                    createKey(".", KeyEvent.VK_PERIOD, 3, 10),
                    createKey("/", KeyEvent.VK_SLASH, 3, 11),
                    createFill(3, 12, 0.3d),
                    createKey("\u2191", KeyEvent.VK_UP, 3, 13),
            },
            {
                    createKey("Ctrl", KeyEvent.VK_CONTROL, KeyType.MODIFIER, 4, 0),
                    createFill(4, 2, 2d),
                    createKey("Alt", KeyEvent.VK_ALT, KeyType.MODIFIER, 4, 3),
                    createKey(" ", KeyEvent.VK_SPACE, 4, 5, 6d),
                    createKey("Alt Gr", KeyEvent.VK_ALT_GRAPH, KeyType.MODIFIER, 4, 6),
                    createFill(4, 7, 0.5d),
                    createKey("\u2190", KeyEvent.VK_LEFT, 4, 8),
                    createKey("\u2193", KeyEvent.VK_DOWN, 4, 9),
                    createKey("\u2192", KeyEvent.VK_RIGHT, 4, 10),
            },
    };

    public Keyboard(Supplier<T> supplier) {
        this.supplier = supplier;

        setLayout(new KeyboardLayout());

        for (KeyboardItem[] value : keys) {
            for (KeyboardItem key : value) {
                JComponent component;
                if (key instanceof KeyItem) {
                    component = supplier.get();
                    ((AbstractButton) component).setText(((KeyItem) key).getText());

                    switch (((KeyItem) key).getKeyType()) {
                        case CHARACTER:
                            ((AbstractButton) component).addActionListener(e -> selectedKey = (KeyItem) key);
                            characterGroup.add((AbstractButton) component);
                            break;
                        case MODIFIER:
                            ((AbstractButton) component).addActionListener(e -> selectedModifier = (KeyItem) key);
                            modifierGroup.add((AbstractButton) component);
                            break;
                    }
                } else {
                    component = new JLabel();
                }
                add(component, key.getKeyConstraint());
            }
        }

        SwingUtilities.invokeLater(() -> SwingUtilities.windowForComponent(this)
                .setFocusTraversalPolicy(new KeyboardFocusTraversalPolicy()));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException ignored) {
            }

            JFrame frame = new JFrame("Testing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(new Keyboard<>(JToggleButton::new));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    @Nullable
    public KeyItem getSelectedCharacter() {
        return selectedKey;
    }

    @Nullable
    public KeyItem getSelectedModifier() {
        return selectedModifier;
    }

    public static KeyItem createKey(String text, Integer keyCode, int x, int y, double span) {
        return new KeyItem(text, keyCode).setKeyConstraint(new KeyConstraint(x, y, span));
    }

    public static KeyItem createKey(String text, Integer keyCode, KeyType type, int x, int y, double span) {
        return new KeyItem(text, keyCode, type).setKeyConstraint(new KeyConstraint(x, y, span));
    }

    public static KeyItem createKey(String text, Integer keyCode, int x, int y) {
        return new KeyItem(text, keyCode).setKeyConstraint(new KeyConstraint(x, y));
    }

    public static KeyItem createKey(String text, Integer keyCode, KeyType type, int x, int y) {
        return new KeyItem(text, keyCode, type).setKeyConstraint(new KeyConstraint(x, y));
    }

    public static FillItem createFill(int x, int y) {
        return new FillItem().setKeyConstraint(new KeyConstraint(x, y));
    }

    public static FillItem createFill(int x, int y, double span) {
        return new FillItem().setKeyConstraint(new KeyConstraint(x, y, span));
    }
}
