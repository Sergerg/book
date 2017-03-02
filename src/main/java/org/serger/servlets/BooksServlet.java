package org.serger.servlets;

import org.serger.controller.ActionRest;
import org.serger.domain.mapper.BookMapper;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Created by galichanin on 02.03.2017.
 */
public class BooksServlet extends HttpServlet {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String path = req.getPathInfo();
        log("Book servlet path = "+path);

        if (path.split("/").length != 2) {
            return; // TODO: err
        }

        String controllerBeanName = prepareControllerBeanName(path);
        log("Try find bean:"+controllerBeanName);
        try {
            Object controller = applicationContext.getBean(controllerBeanName);
            log("Check ActionRest...");
            if (!(controller instanceof ActionRest)) {
                throw new Exception("AAA"); // XXX: AAA!!!
            }
            String rest = ((ActionRest) controller).actionRest(req.getParameterMap());

            res.setContentType("text/json");
            res.getWriter().append(rest);
        } catch (NoSuchBeanDefinitionException e) {
            log(e.getLocalizedMessage(), e);
            // TODO: err
        } catch (Exception e) {
            log(e.getLocalizedMessage(), e);
            // TODO: err
        }
        log("Query ok!");
    }

    private String prepareControllerBeanName(String path) {
        String s = "";
        if (path.length()>=2) {
            s = path.substring(1,2).toUpperCase()
                    + path.substring(2, path.length()).toLowerCase();
        }
        return s+"Controller";
    }


}
