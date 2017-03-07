package org.serger.domain.mapper;

import org.apache.ibatis.annotations.*;
import org.serger.domain.entity.Book;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by galichanin on 01.03.2017.
 */
//@Mapper // 3.4.2 vs 3.3.1
@Component
public interface BookMapper {//extends Mapper{

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO book (title, author) VALUES (#{title}, #{author})")
    void insert(Book book);

    void update(Book book);

    @Delete("DELETE FROM book where id=#{id}")
    void delete(long id);

//    @Select("SELECT * FROM book")
    Collection <Book> selectAll();

//    @Select("SELECT * FROM book WHERE title=#{title}")
//    Collection <Book> selectByTitle(String title);

    @Select("SELECT * FROM book WHERE id=#{id}")
    Collection <Book> selectById(long id);
}
