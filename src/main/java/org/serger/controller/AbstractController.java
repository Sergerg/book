package org.serger.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by galichanin on 05.03.2017.
 */
public class AbstractController implements ActionRest {

    private static final Logger log = LoggerFactory.getLogger(AbstractController.class);

    /**
     * XXX - uid?!
     * @param paths
     * @return
     */
    protected long parseId(String[] paths, boolean skipId) throws ControllerException {
        if (paths.length < 3) {
            if (skipId) return 0;
            throw new ControllerException("Need id!", HttpServletResponse.SC_BAD_REQUEST);
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
    public ActionResult get(String[] paths, Map<String, String[]> params) throws ControllerException {
        return null;
    }

    @Override
    public ActionResult put(String[] paths, Map<String, String[]> params, String reqBody) throws ControllerException {
        return null;
    }

    @Override
    public ActionResult post(String[] paths, Map<String, String[]> params, String reqBody) throws ControllerException {
        return null;
    }

    @Override
    public ActionResult delete(String[] paths, Map<String, String[]> params) throws ControllerException {
        return null;
    }
}
