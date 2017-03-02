package org.serger.controller;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by galichanin on 03.03.2017.
 */
@Component("BookController")
public class BookController implements ActionRest {
    public String actionRest(Map params) {
        return "{'Book':'Test 1'}";
    }
}
