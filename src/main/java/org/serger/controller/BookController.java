package org.serger.controller;

import org.serger.controller.model.BookJson;
import org.serger.controller.model.BookJsonModel;
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

    @Autowired
    BookJsonModel bookJsonModel;

    /**
     * Parse http map urls into Book
     * @param param
     * @return
     */
    private BookJson parseMap(Map <String,String[]> param) {
        BookJson book = new BookJson();
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

    @Override
    public String get(Map params) throws ControllerException {
        BookJson book = parseMap(params); // XXX: AspectJ or/and AbstractRestController<Book>
        log.debug("get, params="+book);
        if (book.getId() == null || book.getId() == 0) {
            return bookJsonModel.selectAll();
        }
        return bookJsonModel.select(book.getId());
    }

    @Override
    public String put(Map params) {
        BookJson book = parseMap(params);
        log.debug("put, params="+book);
        bookJsonModel.insert(book);
        return "";
    }

    @Override
    public String post(Map params) {
        BookJson book = parseMap(params);
        log.debug("post, params="+book);
        bookJsonModel.update(book);
        return "";
    }

    @Override
    public String delete(Map params) {
        BookJson book = parseMap(params);
        log.debug("delete, params="+book);
        if (book.getId() != null || book.getId() != 0) {
            bookJsonModel.delete(book.getId());
        }
        return "";
    }
}
