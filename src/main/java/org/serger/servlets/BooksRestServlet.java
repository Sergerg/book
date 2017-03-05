package org.serger.servlets;

import org.serger.controller.ActionRest;
import org.serger.controller.ActionResult;
import org.serger.controller.ControllerException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
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

        String[] paths = path.split("/");
        // TODO: check URL
//        if (paths.length < 2 && paths.length > 3) {
//            throw new ServletException("Bad URL");
//        }

        String reqBody = readRequestBody(req);
        log("reqBody = "+reqBody);

        String controllerBeanName = prepareControllerBeanName(paths[1]);
        log("Try find bean:"+controllerBeanName);
        try {
            Object controller = applicationContext.getBean(controllerBeanName);
            log("Check ActionRest...");
            if (!(controller instanceof ActionRest)) {
                throw new ControllerException("Wrong action!", HttpServletResponse.SC_BAD_REQUEST);
            }
            ActionRest actionRest = ((ActionRest) controller);
            ActionResult rest;
            switch (method) {
                case "PUT":
                    rest = actionRest.put(paths, req.getParameterMap(), reqBody);
                    break;
                case "POST":
                    rest = actionRest.post(paths, req.getParameterMap(), reqBody);
                    break;
                case "DELETE":
                    rest = actionRest.delete(paths, req.getParameterMap());
                    break;
                case "GET":
                    rest = actionRest.get(paths, req.getParameterMap());
                    break;
                default:
                    throw new ControllerException("Method "+method+" is not supported!", HttpServletResponse.SC_BAD_REQUEST);
            }

            makeResponse(rest, res);
        } catch (NoSuchBeanDefinitionException e) {
            throw new ServletException("Method Not found", e);
        } catch (ControllerException e) {
            String se = "{\"error\":\""+e.getLocalizedMessage()+"\"}";
            res.setContentType("application/json; charset=UTF-8");
            res.setHeader("Access-Control-Allow-Origin", "*");
            res.getOutputStream().write( se.getBytes("UTF-8") );
            res.setStatus(e.getStatus());
        }
        log("Query ok!");
    }

    private void makeResponse(ActionResult rest, HttpServletResponse res) throws IOException {
        // Standart REST response
        res.setContentType("application/json; charset=UTF-8");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.getOutputStream().write( rest.body.getBytes("UTF-8") );
        res.setStatus( (rest.status == 0) ? HttpServletResponse.SC_OK : rest.status);
    }

    /**
     * Name controller
     * XXX: Camel case!!! Define in Controller @Qualifier annotation
     * @param path
     * @return
     */
    private String prepareControllerBeanName(String path) {
        String s = path.substring(0,1).toLowerCase() + path.substring(1);
        return s+"Controller";
    }

    private String readRequestBody(HttpServletRequest request) {
        try {
            // Read from request
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (Exception e) {
            log("Failed to read the request body from the request.");
        }
        return null;
    }


}
