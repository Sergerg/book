package org.serger;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.serger.domain.mapper.BookMapper;
import org.serger.domain.mapper.BookReaderMapper;
import org.serger.domain.mapper.ReaderMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by galichanin on 06.03.2017.
 */
@Configuration
@MapperScan("org.serger.domain.mapper")
@EnableTransactionManagement
public class ApplicationConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("bookDb")
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.getObject().getConfiguration().addMapper(BookMapper.class);
        sessionFactory.getObject().getConfiguration().addMapper(ReaderMapper.class);
        sessionFactory.getObject().getConfiguration().addMapper(BookReaderMapper.class);
        return sessionFactory.getObject();
    }

}
