package org.serger.domain.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.serger.domain.entity.Book;
import org.serger.domain.entity.BookReader;

import java.util.Collection;

/**
 * Created by galichanin on 01.03.2017.
 */
@Mapper
public interface BookReaderMapper {
    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO bookreader (book_id, reader_id) VALUES (#{bookId}, #{readerId})")
    void insert(BookReader bookReader);

    @Select("SELECT * FROM bookreader")
    Collection<BookReader> selectAll();

    Collection<Book> selectBooksByReader(long readerId);
}
