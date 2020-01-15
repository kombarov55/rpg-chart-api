package ru.novemis.util;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.util.Properties;

@Component
public class HiddenProperties {

    @Value("${hiddenPropsPath}")
    private String hiddenPropsPath;

    private Properties internal = new Properties();

    @PostConstruct
    @SneakyThrows
    public void load() {
        internal.load(new FileReader(hiddenPropsPath));
    }

    public Integer getAppId() {
        return Integer.parseInt(internal.getProperty("vk.appId"));
    }

    public String getClientSecret() {
        return internal.getProperty("vk.clientSecret");
    }

    public String getServiceToken() {
        return internal.getProperty("vk.serviceToken");
    }

    public String getGroupSecret() {
        return internal.getProperty("vk.groupSecret");
    }

    public String getKeystorePassword() {
        return internal.getProperty("ssl.keystorePassword");
    }

    public String getTruststorePassword() {
        return internal.getProperty("ssl.truststorePassword");
    }

}
