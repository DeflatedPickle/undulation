package so.madprogrammer.keyboard;

import javax.swing.JComponent;

public interface KeyboardItem {
    KeyboardItem setKeyConstraint(KeyConstraint keyConstraint);
    KeyConstraint getKeyConstraint();

    KeyboardItem setComponent(JComponent component);
    JComponent getComponent();
}
