package org.serger.domain.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.serger.domain.entity.Reader;

import java.util.Collection;

/**
 * Created by galichanin on 01.03.2017.
 */
@Mapper
public interface ReaderMapper {

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO reader (name) VALUES (#{name})")
    void insert(Reader reader);

    @Select("SELECT * FROM reader")
    Collection<Reader> selectAll();

    @Select("SELECT * FROM reader WHERE name=#{s}")
    Collection<Reader> selectByName(String s);
}
