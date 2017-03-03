package org.serger.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.serger.domain.entity.Book;
import org.serger.domain.mapper.BookMapper;
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

    // TODO: Decorate...
    @Autowired
    BookMapper bookMapper;

    /**
     * Parse
     * @param param
     * @return
     */
    private Book parseMap(Map <String,String[]> param) {
        Book book = new Book();
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

    /**
     * TODO: move into specific model, id change to UID.
     * @param book
     * @return
     */
    public String buildWeatherJson(Book book) {
        // для простоты примера просто хардкодим нужные данные в методе
        JSONObject jsonObjectBook = new JSONObject();
        jsonObjectBook.put("id", book.getId());
        jsonObjectBook.put("title", book.getTitle());
        jsonObjectBook.put("author", book.getAuthor());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("book", jsonObjectBook);
        return jsonObject.toJSONString();
    }

    public String get(Map params) throws ControllerException {
        Book book = parseMap(params); // XXX: AspectJ or/and AbstractRestController<Book>
        log.debug("get, params="+book);
        // TODO: move into specific object
        if (book.getId() == null || book.getId() == 0) {
            JSONArray jsonBooks = new JSONArray();
            bookMapper.selectAll().stream().forEach( book1 -> {
                JSONObject jsonObjectBook = new JSONObject();
                jsonObjectBook.put("id", book1.getId());
                jsonObjectBook.put("title", book1.getTitle());
                jsonObjectBook.put("author", book1.getAuthor());
                jsonBooks.add(jsonObjectBook);
            }); // XXX: Limit!!!
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("books", jsonBooks);
            return jsonObject.toJSONString();
        }
        Book bookSelected = bookMapper.selectById(book.getId()).stream().findFirst().get();
        log.debug("get, found book="+bookSelected);
        return buildWeatherJson(bookSelected); // XXX: AspectJ or/and AbstractRestController<Book>
    }

    @Override
    public String put(Map params) {
        Book book = parseMap(params);
        log.debug("put, params="+book);
        bookMapper.insert(book);
        return buildWeatherJson(book); // TODO ???
    }

    @Override
    public String post(Map params) {
        Book book = parseMap(params);
        log.debug("post, params="+book);
        bookMapper.update(book);
        return buildWeatherJson(book); // TODO ???
    }

    @Override
    public String delete(Map params) {
        Book book = parseMap(params);
        log.debug("delete, params="+book);
        if (book.getId() != null || book.getId() != 0) {
            bookMapper.delete(book.getId());
        }
        return ""; // TODO ?
    }
}
