package ru.novemis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.novemis.model.Person;


@Configuration
@ComponentScan("ru.novemis")
@PropertySource("classpath:/application.properties")
public class Beans {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Bean
    public Dao<Person, Long> personDao(JdbcConnectionSource jdbcConnectionSource) throws Throwable {
        return DaoManager.createDao(jdbcConnectionSource, Person.class);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JdbcConnectionSource jdbcConnectionSource() throws Throwable {
        return new JdbcConnectionSource(jdbcUrl);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
