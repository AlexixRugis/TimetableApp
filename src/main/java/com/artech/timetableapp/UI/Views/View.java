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

    public <T extends Node> T lookup(String selector, Class<T> type) {
        if (this.content == null) this.content = build();
        return type.cast(this.content.lookup(selector));
    }
}
