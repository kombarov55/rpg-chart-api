package ru.novemis.config;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.novemis.HiddenProperties;
import ru.novemis.model.Person;

@Component
public class OnStartup implements InitializingBean {

    @Autowired
    private ConnectionSource jdbcConnectionSource;
    @Autowired
    private HiddenProperties hiddenProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        TableUtils.createTableIfNotExists(jdbcConnectionSource, Person.class);
        hiddenProperties.load();
    }
}
