package ru.novemis.service;

import com.j256.ormlite.dao.Dao;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.novemis.model.Person;

import java.util.List;

@Component
public class PersonService {

    @Autowired
    private Dao<Person, Long> personDao;

    @SneakyThrows
    public List<Person> getAll() {
        return personDao.queryForAll();
    }

}
