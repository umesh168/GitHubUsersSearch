package com.umesh.github.app.githubsearch.events;

import java.io.Serializable;

/**
 * Created by dell on 19/7/18.
 */

public class NextPageEvent implements Serializable {

    private String next;
    private int position;
    private String action;

    public NextPageEvent(String next, int position, String action) {
        this.next = next;
        this.position = position;
        this.action = action;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
