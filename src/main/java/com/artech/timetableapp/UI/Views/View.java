package com.artech.timetableapp.UI.Views;

import javafx.scene.Node;

public abstract class View
{
    private Node content;

    protected abstract Node build();
    public abstract String getName();

    public Node getContent() {
        if (this.content == null) this.content = build();
        return this.content;
    }

    public Node lookup(String selector) {
        if (this.content == null) this.content = build();
        return this.content.lookup(selector);
    }
}
