package org.serger.controller.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.serger.controller.ControllerException;
import org.serger.domain.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by galichanin on 04.03.2017.
 * Decorate book entity
 */
public class BookJson extends Book {

    private static final Logger log = LoggerFactory.getLogger(BookJson.class);

    public BookJson() { super(); }

    public BookJson(Book book) {
        this.setId(book.getId());
        this.setTitle(book.getTitle());
        this.setAuthor(book.getAuthor());
    }

    /**
     * JSON to BookJson constructor...
     * @param reqBody - json
     */
    public BookJson(String reqBody) throws ControllerException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(reqBody);
        } catch (ParseException e) {
            throw new ControllerException("Parse JSON error! "+e.toString(), HttpServletResponse.SC_BAD_REQUEST);
        }
        JSONObject jsonBook = (JSONObject) jsonObject.get("book");
        if (jsonBook == null) throw new ControllerException("Error param", HttpServletResponse.SC_BAD_REQUEST);
        setTitle((String)jsonBook.get("title"));
        setAuthor((String)jsonBook.get("author"));
        log.debug("After JSON parse = " + toString());
    }

    /**
     * JSON Cortage book
     * @return
     */
    public JSONObject buildBookJsonCortage() {
        JSONObject json = new JSONObject();
        json.put("id", getId());
        json.put("title", getTitle());
        json.put("author", getAuthor());
        return json;
    }

    /**
     * Get ready full JSON object.
     * @return JSONObject
     */
    public JSONObject buildBookJson() {
        JSONObject jsonBook = new JSONObject();
        jsonBook.put("book", buildBookJsonCortage());
        return jsonBook;
    }

}
