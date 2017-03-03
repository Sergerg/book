package org.serger.controller;

import org.serger.domain.entity.Book;
import org.serger.domain.mapper.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by galichanin on 03.03.2017.
 */
@Component//("bookController")
public class BookController implements ActionRest {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    // TODO: Decorate...
    @Autowired
    BookMapper bookMapper;

    private Book parseMap(Map <String,String[]> param) {
        Book book = new Book();
        String[] id = param.get("id");
        if (id != null && id.length != 0) {
            try {
                log.debug(""+id[0]);
                book.setId(Long.parseLong(id[0]));
            } catch (NumberFormatException e) {
                log.warn(e.getLocalizedMessage(), e);
            }
        }
        String[] title = param.get("title");
        if (title!= null && title.length != 0) book.setTitle(title[0]);
        String[] author = param.get("author");
        if (author != null && author.length != 0) book.setAuthor(author[0]);
        return book;
    }

    public String get(Map params) {
        Book book = parseMap(params);
        log.debug("get, params="+book);
        return "{'Book`s':'"+bookMapper.selectAll().size()+"'}";
    }

    @Override
    public String put(Map params) {
        Book book = parseMap(params);
        log.debug("put, params="+book);
        return "";
    }

    @Override
    public String post(Map params) {
        Book book = parseMap(params);
        log.debug("post, params="+book);
        return "";
    }

    @Override
    public String delete(Map params) {
        Book book = parseMap(params);
        log.debug("delete, params="+book);
        return "";
    }
}
