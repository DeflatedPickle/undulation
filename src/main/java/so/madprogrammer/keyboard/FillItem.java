package so.madprogrammer.keyboard;

public class FillItem implements KeyboardItem {
    private KeyConstraint keyConstraint;

    @Override
    public FillItem setKeyConstraint(KeyConstraint keyConstraint) {
        this.keyConstraint = keyConstraint;
        return this;
    }

    @Override
    public KeyConstraint getKeyConstraint() {
        return keyConstraint;
    }
}
