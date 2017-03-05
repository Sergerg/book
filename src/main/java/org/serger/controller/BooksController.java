package org.serger.controller;

import org.json.simple.parser.ParseException;
import org.serger.controller.model.BookJson;
import org.serger.controller.model.BookJsonModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by galichanin on 03.03.2017.
 */
@Component//("booksController")
public class BooksController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(BooksController.class);

    @Autowired
    BookJsonModel bookJsonModel;

    @Override
    public ActionResult get(String[] paths, Map params) throws ControllerException {
        ActionResult result = new ActionResult();
        long id = parseId(paths, true);
        log.debug("get, id="+id);
        if (id == 0) {
            result.body = bookJsonModel.selectAll();
        } else {
            result.body = bookJsonModel.select(id);
        }
        return result;
    }

    @Override
    public ActionResult put(String[] paths, Map params, String reqBody) throws ControllerException {
        ActionResult result = new ActionResult();
        BookJson book = new BookJson(reqBody);
        long id = parseId(paths, true);
        log.debug("put, id="+id+",params="+book);
        if (id == 0) {
            bookJsonModel.insert(book);
            result.status = HttpServletResponse.SC_CREATED;
        } else {
            book.setId(id);
            bookJsonModel.update(book);
        }
        result.body = "";
        return result;
    }

    @Override
    public ActionResult post(String[] paths, Map params, String reqBody) throws ControllerException {
        ActionResult result = new ActionResult();
        BookJson book = new BookJson(reqBody);
        log.debug("post, params="+book);
        bookJsonModel.update(book);
        result.body = "";
        return result;
    }

    @Override
    public ActionResult delete(String[] paths, Map params) throws ControllerException {
        ActionResult result = new ActionResult();
        long id = parseId(paths, false);
        log.debug("delete, id="+id);
        bookJsonModel.delete(id);
        result.body = "";
        return result;
    }

}
