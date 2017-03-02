package org.serger.servlets;

import org.serger.domain.mapper.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;

/**
 * Created by galichanin on 02.03.2017.
 */
public class BooksServlet extends GenericServlet {

    private static final Logger log = LoggerFactory.getLogger(BooksServlet.class);

    @Autowired
    BookMapper bookMapper;

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        log("Book servlet.");

        res.setContentType("text/plain");
        res.getWriter().append("Hello World="+bookMapper.selectAll().size());
    }

}
