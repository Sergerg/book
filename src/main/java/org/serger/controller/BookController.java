package org.serger.controller;

import org.serger.domain.mapper.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by galichanin on 03.03.2017.
 */
@Component("BookController")
public class BookController implements ActionRest {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    // TODO: Decorate...
    @Autowired
    BookMapper bookMapper;

    public String get(Map params) {
        log.debug("get");
        return "{'Book`s':'"+bookMapper.selectAll().size()+"'}";
    }

    @Override
    public String put(Map params) {
        log.debug("put");
        return "";
    }

    @Override
    public String post(Map params) {
        log.debug("post");
        return "";
    }

    @Override
    public String delete(Map params) {
        log.debug("delete");
        return "";
    }
}
