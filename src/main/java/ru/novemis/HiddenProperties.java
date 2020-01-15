package ru.novemis;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.Properties;

@Component
public class HiddenProperties {


    @Value("${hiddenPropsPath}")
    private String hiddenPropsPath;

    private Properties internal = new Properties();

    @SneakyThrows
    public void load() {
        internal.load(new FileReader(hiddenPropsPath));
    }

    public String get(String name) {
        if (internal.isEmpty()) {
            load();
        }

        return internal.getProperty(name);
    }

}
