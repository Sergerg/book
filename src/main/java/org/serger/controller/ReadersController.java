package org.serger.controller;

import org.serger.controller.model.BookJsonModel;
import org.serger.controller.model.ReaderJsonModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by galichanin on 05.03.2017.
 */
@Component
@Qualifier("readerController")
public class ReadersController  extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(ReadersController.class);

    @Autowired
    BookJsonModel bookJsonModel;

    @Autowired
    ReaderJsonModel readerJsonModel;

    /**
     * Check url for get readers books.
     * @param paths
     * @return
     */
    private boolean checkBooks(String[] paths) {
        return (paths.length > 3 && paths[3].equals("books"));
    }

    private long parseBookId(String[] paths) throws ControllerException {
        if (paths.length < 5 && !paths[3].equals("books")) {
            throw new ControllerException("Need id!", HttpServletResponse.SC_BAD_REQUEST);
        }
        String sId = paths[4];
        log.debug("book id = "+sId);
        long id = 0;
        try {
            id = Long.parseLong(sId);
        } catch (NumberFormatException e) {
            log.warn(e.getLocalizedMessage(), e);
        }
        return id;
    }

    @Override
    public ActionResult get(String[] paths, Map<String, String[]> params) throws ControllerException {
        ActionResult result = new ActionResult();
        long readerId = parseId(paths, true);
        boolean isBooks = checkBooks(paths);
        log.debug("get, id="+readerId);
        if (!isBooks) {
            throw new ControllerException("Not supp!", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            result.body = bookJsonModel.selectByReader(readerId);
        }
        return result;
    }

    @Override
    public ActionResult put(String[] paths, Map<String, String[]> params, String reqBody) throws ControllerException {
        ActionResult result = new ActionResult();
        long readerId = parseId(paths, true);
        long bookId = parseBookId(paths);
        log.debug("delete, readerId="+readerId+", bookId="+bookId);
        if (bookId == 0) {
            throw new ControllerException("Not supp!", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            bookJsonModel.insertReader(readerId, bookId);
            result.status = HttpServletResponse.SC_CREATED;
            result.body = "";
        }
        return result;
    }

    @Override
    public ActionResult post(String[] paths, Map<String, String[]> params, String reqBody) throws ControllerException {
        throw new ControllerException("Not supp!", HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    public ActionResult delete(String[] paths, Map<String, String[]> params) throws ControllerException {
        ActionResult result = new ActionResult();
        long readerId = parseId(paths, true);
        long bookId = parseBookId(paths);
        log.debug("delete, readerId="+readerId+", bookId="+bookId);
        if (bookId == 0) {
            throw new ControllerException("Not supp!", HttpServletResponse.SC_BAD_REQUEST);
        } else {
            bookJsonModel.deleteReader(readerId, bookId);
            result.body = "";
        }
        return result;
    }
}
