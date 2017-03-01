package org.serger.services.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.serger.services.entity.Book;

import java.util.Collection;

/**
 * Created by galichanin on 01.03.2017.
 */
@Mapper
public interface BookMapper {

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO book (title, author) VALUES (#{title}, #{author})")
    void insert(Book book);

    @Select("SELECT * FROM book")
    Collection <Book> selectAll();

    @Select("SELECT * FROM book WHERE title=#{title}")
    Collection <Book> selectByTitle(String title);
}
