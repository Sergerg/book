package org.serger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.serger.domain.entity.Book;
import org.serger.domain.entity.BookReader;
import org.serger.domain.entity.Reader;
import org.serger.domain.mapper.BookMapper;
import org.serger.domain.mapper.BookReaderMapper;
import org.serger.domain.mapper.ReaderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(BookApplicationTests.class);

    @Autowired
    BookMapper bookMapper;
    @Autowired
    ReaderMapper readerMapper;
    @Autowired
    BookReaderMapper bookReaderMapper;

    @Test
    public void test() {
        // Create
        List<Book> books = Arrays.asList(
                new Book("Book 1", "Author 1", null),
                new Book("Book 2", "Author 1", null),
                new Book("Book 3", "Author 2", null)
        );
        books.forEach(book -> bookMapper.insert(book));
        assert (bookMapper.selectAll().size() == 3);
        List<Reader> readers = Arrays.asList(
                new Reader(null, "Sergey"),
                new Reader(null, "Alex")
        );
        readers.forEach(reader -> readerMapper.insert(reader));
        assert (readerMapper.selectAll().size() == 2);

        // Books
        assert (bookMapper.selectByTitle("1").size() == 0);
        Collection<Book> books1 = bookMapper.selectByTitle("Book 1");
        assert (books1.size() == 1);
        Book book1 = books1.stream().findFirst().get();
        log.info("+++++++++ Book 1 ID = "+book1.getId());
        Book book3 = bookMapper.selectByTitle("Book 3").stream().findFirst().get();
        log.info("+++++++++ Book 3 ID = "+book3.getId());

        // Readers
        assert (readerMapper.selectByName("1").size() == 0);
        Reader reader1 = readerMapper.selectByName("Sergey").stream().findFirst().get();
        log.info("+++++++++ Reader 1 ID = "+reader1.getId());
        Reader reader2 = readerMapper.selectByName("Alex").stream().findFirst().get();
        log.info("+++++++++ Reader 2 ID = "+reader2.getId());

        // BookReaders
        BookReader bookReader1 = new BookReader(null, book1.getId(), reader1.getId());
        BookReader bookReader2 = new BookReader(null, book3.getId(), reader1.getId());
        BookReader bookReader3 = new BookReader(null, book3.getId(), reader2.getId());
        bookReaderMapper.insert(bookReader1);
        bookReaderMapper.insert(bookReader2);
        try {
            bookReaderMapper.insert(bookReader3);
        } catch (Exception e) {
            log.warn(e.getLocalizedMessage(), e);
        }
        assert (bookReaderMapper.selectAll().size() == 2);

        // Test bookReader:
        assert (bookReaderMapper.selectBooksByReader(reader1.getId()).size()==2);
        assert (bookReaderMapper.selectBooksByReader(reader2.getId()).size()==0);
    }

}
