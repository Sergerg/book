package org.serger.controller;

import java.util.Map;

/**
 * Created by galichanin on 03.03.2017.
 * XXX: rename methods, change/add some params...
 */
public interface ActionRest {
    /**
     * Like get one, get all
     *
     * @param paths
     * @param params
     * @return
     */
    ActionResult get(String[] paths, Map<String, String[]> params) throws ControllerException;

    /**
     * Like add
     *
     * @param paths
     * @param params
     * @param reqBody
     * @return
     */
    ActionResult put(String[] paths, Map<String, String[]> params, String reqBody) throws ControllerException;

    /**
     * Like update
     *
     * @param paths
     * @param params
     * @param reqBody
     * @return
     */
    ActionResult post(String[] paths, Map<String, String[]> params, String reqBody) throws ControllerException;

    /**
     * Like delete
     *
     * @param paths
     * @param params
     * @return
     */
    ActionResult delete(String[] paths, Map<String, String[]> params) throws ControllerException;
}
