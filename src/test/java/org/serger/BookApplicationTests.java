package org.serger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.serger.services.entity.Book;
import org.serger.services.entity.BookReader;
import org.serger.services.entity.Reader;
import org.serger.services.mapper.BookMapper;
import org.serger.services.mapper.BookReaderMapper;
import org.serger.services.mapper.ReaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookApplicationTests {

    @Autowired
    BookMapper bookMapper;
    @Autowired
    ReaderMapper readerMapper;
    @Autowired
    BookReaderMapper bookReaderMapper;

    @Test
    public void bookReaderMapper() {
        bookReaderMapper.insert(new BookReader());
        bookReaderMapper.insert(new BookReader());
        bookReaderMapper.insert(new BookReader());
        assert (bookReaderMapper.selectAll().size() == 3);
    }

    @Test
    public void bookMapper() {
        bookMapper.insert(new Book());
        bookMapper.insert(new Book());
        assert (bookMapper.selectAll().size() == 2);
    }

    @Test
    public void readerMapper() {
        readerMapper.insert(new Reader());
        assert (readerMapper.selectAll().size() == 1);
    }

}
