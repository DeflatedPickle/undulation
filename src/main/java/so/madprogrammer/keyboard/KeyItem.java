package so.madprogrammer.keyboard;

public class KeyItem implements KeyboardItem {
    private final String text;
    private final Integer keyCode;
    private KeyType keyType;
    private KeyConstraint keyConstraint;

    public KeyItem(String text, Integer keyCode) {
        this.text = text;
        this.keyCode = keyCode;
        this.keyType = KeyType.CHARACTER;
    }
    public KeyItem(String text, Integer keyCode, KeyType type) {
        this.text = text;
        this.keyCode = keyCode;
        this.keyType = type;
    }

    public String getText() {
        return text;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    public Integer getKeyCode() {
        return keyCode;
    }

    @Override
    public KeyItem setKeyConstraint(KeyConstraint keyConstraint) {
        this.keyConstraint = keyConstraint;
        return this;
    }

    @Override
    public KeyConstraint getKeyConstraint() {
        return keyConstraint;
    }
}
