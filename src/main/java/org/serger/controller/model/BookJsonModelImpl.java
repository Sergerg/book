package org.serger.controller.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.serger.domain.entity.Book;
import org.serger.domain.entity.BookReader;
import org.serger.domain.mapper.BookMapper;
import org.serger.domain.mapper.BookReaderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

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
        log.info("get, found book="+bookSelected);
        return bookSelected.buildBookJson().toJSONString();
    }

    @Override
    public String selectByReader(long readerId) {
        return selectBooks(bookReaderMapper.selectBooksByReader(readerId).stream());  // XXX: Limit!!!
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
        return selectBooks(bookMapper.selectAll().stream());  // XXX: Limit!!!
    }

    @Override
    public void deleteReader(long readerId, long bookId) {
        bookReaderMapper.delete(new BookReader(null, bookId, readerId));
    }

    @Override
    public void insertReader(long readerId, long bookId) {
        bookReaderMapper.insert(new BookReader(null, bookId, readerId));
    }

    /**
     * Query stream -> JSON
     * @param stream
     * @return
     */
    private String selectBooks(Stream<Book> stream) {
        JSONArray jsonBooks = new JSONArray();
        stream.forEach( book1 -> {
            JSONObject jsonObjectBook = new JSONObject();
            jsonObjectBook.put("id", book1.getId());
            jsonObjectBook.put("title", book1.getTitle());
            jsonObjectBook.put("author", book1.getAuthor());
            jsonBooks.add(jsonObjectBook);
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("books", jsonBooks);
        return jsonObject.toJSONString();
    }

}
