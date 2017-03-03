package org.serger.servlets;

import org.serger.controller.ActionRest;
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
public class BooksRestServlet extends HttpServlet {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String path = req.getPathInfo();
        log("Book servlet path = "+path);
        String method = req.getMethod();
        log("Method = "+method);

        // TODO: check URL
        if (path.split("/").length != 2) {
            throw new ServletException("Bad URL");
        }

        String controllerBeanName = prepareControllerBeanName(path);
        log("Try find bean:"+controllerBeanName);
        try {
            Object controller = applicationContext.getBean(controllerBeanName);
            log("Check ActionRest...");
            if (!(controller instanceof ActionRest)) {
                throw new ServletException("Inner error!");
            }
            ActionRest actionRest = ((ActionRest) controller);
            String rest;
            switch (method) {
                case "PUT":
                    rest = actionRest.put(req.getParameterMap());
                    break;
                case "POST":
                    rest = actionRest.post(req.getParameterMap());
                    break;
                case "DELETE":
                    rest = actionRest.delete(req.getParameterMap());
                    break;
                case "GET":
                default:
                    rest = actionRest.get(req.getParameterMap());
                    break;
            }

            // Standart REST response
            // TODO: check!!!
            res.getOutputStream().write( rest.getBytes("UTF-8") );
            res.setContentType("application/json; charset=UTF-8");
            res.setHeader("Access-Control-Allow-Origin", "*");
            res.setStatus( HttpServletResponse.SC_OK );
        } catch (NoSuchBeanDefinitionException e) {
            throw new ServletException("Method Not found", e);
        }
        log("Query ok!");
    }

    private String prepareControllerBeanName(String path) {
        String s = "";
        if (path.length()>=2) {
            s = path.substring(1, path.length()).toLowerCase();
        }
        return s+"Controller";
    }


}
