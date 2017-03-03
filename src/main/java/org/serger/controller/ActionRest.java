package org.serger.controller;

import java.util.Map;

/**
 * Created by galichanin on 03.03.2017.
 * XXX: rename methods, change/add some params...
 */
public interface ActionRest {
    /**
     * Like get one, get all
     * @param params
     * @return
     */
    String get(Map <String,String[]> params) throws ControllerException;

    /**
     * Like add
     * @param params
     * @return
     */
    String put(Map <String,String[]> params);

    /**
     * Like update
     * @param params
     * @return
     */
    String post(Map <String,String[]> params);

    /**
     * Like delete
     * @param params
     * @return
     */
    String delete(Map <String,String[]> params);
}
