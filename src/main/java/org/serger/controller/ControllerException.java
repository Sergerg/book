package org.serger.controller;

/**
 * Created by galichanin on 03.03.2017.
 */
public class ControllerException extends Exception {
    private int status;
    public ControllerException(String s, int status) {
        super(s+"("+status+")");
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
