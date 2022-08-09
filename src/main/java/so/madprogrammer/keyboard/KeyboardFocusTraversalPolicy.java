package so.madprogrammer.keyboard;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.Window;

public class KeyboardFocusTraversalPolicy extends FocusTraversalPolicy {
    @Override
    public Component getComponentAfter(Container aContainer, Component aComponent) {
        return null;
    }

    @Override
    public Component getComponentBefore(Container aContainer, Component aComponent) {
        return null;
    }

    @Override
    public Component getFirstComponent(Container aContainer) {
        return null;
    }

    @Override
    public Component getLastComponent(Container aContainer) {
        return null;
    }

    @Override
    public Component getDefaultComponent(Container aContainer) {
        return null;
    }

    @Override
    public Component getInitialComponent(Window window) {
        return null;
    }
}
