package org.serger.domain.mapper;

import org.apache.ibatis.annotations.*;
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

    @Delete("DELETE FROM bookreader WHERE reader_id=#{readerId} AND book_id=#{bookId}")
    void delete(long readerId, long bookId);

    Collection<Book> selectBooksByReader(long readerId);
}
