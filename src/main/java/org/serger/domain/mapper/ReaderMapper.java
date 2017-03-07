package org.serger.domain.mapper;

import org.apache.ibatis.annotations.*;
import org.serger.domain.entity.Reader;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by galichanin on 01.03.2017.
 */
//@Mapper
@Component
public interface ReaderMapper {

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO reader (name) VALUES (#{name})")
    void insert(Reader reader);

    void update(Reader reader);

    @Delete("DELETE FROM reader where id=#{id}")
    void delete(long id);

    @Select("SELECT * FROM reader")
    Collection<Reader> selectAll();

    @Select("SELECT * FROM reader WHERE name=#{s}")
    Collection<Reader> selectByName(String s);

    @Select("SELECT * FROM reader WHERE id=#{id}")
    Collection <Reader> selectById(long id);
}
