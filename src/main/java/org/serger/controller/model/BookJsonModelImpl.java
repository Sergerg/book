package org.serger.controller.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.serger.domain.entity.BookReader;
import org.serger.domain.mapper.BookMapper;
import org.serger.domain.mapper.BookReaderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by galichanin on 03.03.2017.
 */
@Component
public class BookJsonModelImpl implements BookJsonModel {

    private static final Logger log = LoggerFactory.getLogger(BookJsonModelImpl.class);

    public BookJsonModelImpl() {  }

    @Autowired
    BookMapper bookMapper;

    @Autowired
    BookReaderMapper bookReaderMapper;

    @Override
    public String select(long id) {
        BookJson bookSelected = new BookJson(bookMapper.selectById(id).stream().findFirst().get());
        log.debug("get, found book="+bookSelected);
        return bookSelected.buildBookJson().toJSONString();
    }

    @Override
    public String selectByReader(long readerId) {
        JSONArray jsonBooks = new JSONArray();
        bookReaderMapper.selectBooksByReader(readerId).stream().forEach( book1 -> {
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

    @Override
    public void delete(long id) {
        bookMapper.delete(id);
    }

    @Override
    public void update(BookJson book) {
        bookMapper.update(book);
    }

    @Override
    public void insert(BookJson book) {
        bookMapper.insert(book);
    }

    @Override
    public String selectAll() {
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

    @Override
    public void deleteReader(long readerId, long bookId) {
        bookReaderMapper.delete(readerId, bookId);
    }

    @Override
    public void insertReader(long readerId, long bookId) {
        bookReaderMapper.insert(new BookReader(null, bookId, readerId));
    }

}
