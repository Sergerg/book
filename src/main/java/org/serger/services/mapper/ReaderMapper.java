package org.serger.services.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.serger.services.entity.Reader;

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


}
