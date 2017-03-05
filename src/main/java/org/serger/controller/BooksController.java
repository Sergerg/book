package org.serger.controller;

import org.json.simple.parser.ParseException;
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
public class BooksController implements ActionRest {

    private static final Logger log = LoggerFactory.getLogger(BooksController.class);

    @Autowired
    BookJsonModel bookJsonModel;

//    /**
//     * Parse http map urls into Book
//     * XXX - not need
//     * @param param
//     * @return
//     */
//    private BookJson parseMap(Map <String,String[]> param) {
//        BookJson book = new BookJson();
//        String[] id = param.get("id");
//        if (id != null && id.length != 0) {
//            try {
//                log.debug(""+id[0]);
//                book.setId(Long.parseLong(id[0]));
//            } catch (NumberFormatException e) {
//                log.warn(e.getLocalizedMessage(), e);
//            }
//        }
//        String[] title = param.get("title");
//        if (title!= null && title.length != 0) book.setTitle(title[0]);
//        String[] author = param.get("author");
//        if (author != null && author.length != 0) book.setAuthor(author[0]);
//        return book;
//    }

    /**
     * XXX - uid?!
     * @param paths
     * @return
     */
    private long parseId(String[] paths, boolean skipId) throws ControllerException {
        if (paths.length < 3) {
            if (skipId) return 0;
            throw new ControllerException("Need id!");
        }
        String sId = paths[2];
        log.debug("id = "+sId);
        long id = 0;
        try {
            id = Long.parseLong(sId);
        } catch (NumberFormatException e) {
            log.warn(e.getLocalizedMessage(), e);
        }
        return id;
    }

    @Override
    public String get(String[] paths, Map params) throws ControllerException {
        long id = parseId(paths, true);
        log.debug("get, id="+id);
        if (id == 0) {
            return bookJsonModel.selectAll();
        }
        return bookJsonModel.select(id);
    }

    @Override
    public String put(String[] paths, Map params, String reqBody) throws ControllerException {
        BookJson book;
        try {
            book = new BookJson(reqBody);
        } catch (ParseException e) {
            throw new ControllerException("Parse JSON error! "+e.toString());
        }
        log.debug("put, params="+book);
        bookJsonModel.insert(book);
        return "";
    }

    @Override
    public String post(String[] paths, Map params, String reqBody) throws ControllerException {
        BookJson book;
        try {
            book = new BookJson(reqBody);
        } catch (ParseException e) {
            throw new ControllerException("Parse JSON error! "+e.toString());
        }
        log.debug("post, params="+book);
        bookJsonModel.update(book);
        return "";
    }

    @Override
    public String delete(String[] paths, Map params) throws ControllerException {
        long id = parseId(paths, false);
        log.debug("delete, id="+id);
        bookJsonModel.delete(id);
        return "";
    }

}
