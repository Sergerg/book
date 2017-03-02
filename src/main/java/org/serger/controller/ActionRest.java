package org.serger.controller;

import java.util.Map;

/**
 * Created by galichanin on 03.03.2017.
 * TODO: rename methods, change/add some params...
 */
public interface ActionRest {
    String get(Map params);
    String put(Map params);
    String post(Map params);
    String delete(Map params);
}
