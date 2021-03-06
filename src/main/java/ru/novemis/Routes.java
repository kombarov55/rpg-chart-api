package ru.novemis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.novemis.model.Person;
import ru.novemis.service.PersonService;
import ru.novemis.util.HiddenProperties;
import spark.Spark;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@Slf4j
public class Routes {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PersonService personService;
    @Autowired
    private HiddenProperties hiddenProperties;

    @Value("${ssl.keystorePath}")
    private String keystorePath;
    @Value("${ssl.truststorePath}")
    private String truststorePath;
    @Value("${server.port:8081}")
    private int port;
    @Value("${server.sslEnabled}")
    private boolean sslEnabled;

    @PostConstruct
    public void defineRoutes() {
        configureSpark();

        Spark.get("/", (rq, rs) -> {
            List<Person> persons = personService.getAll();
            return objectMapper.writeValueAsString(persons);
        });
        Spark.get("/ping", (rq, rs) -> "pong");
        Spark.get("/shutdown", (rq, rs) -> {
//            Spark.stop();
            System.exit(0);
            return "Stopped";
        });
    }

    private void configureSpark() {
        Spark.port(port);
        if (sslEnabled) {
            Spark.secure(
              keystorePath, hiddenProperties.getKeystorePassword(),
              truststorePath, hiddenProperties.getTruststorePassword()
            );
        }
    }

}
