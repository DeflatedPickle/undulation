package so.madprogrammer.keyboard;

import javax.swing.JComponent;

public class FillItem implements KeyboardItem {
    private KeyConstraint keyConstraint;
    private JComponent component;

    @Override
    public FillItem setKeyConstraint(KeyConstraint keyConstraint) {
        this.keyConstraint = keyConstraint;
        return this;
    }

    @Override
    public KeyConstraint getKeyConstraint() {
        return keyConstraint;
    }

    @Override
    public KeyboardItem setComponent(JComponent component) {
        this.component = component;
        return this;
    }

    @Override
    public JComponent getComponent() {
        return component;
    }
}
