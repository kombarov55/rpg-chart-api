package ru.novemis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.novemis.model.Person;
import ru.novemis.service.PersonService;
import spark.Spark;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
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

    @PostConstruct
    public void defineRoutes() {
        configureSpark();

        Spark.get("/", (rq, rs) -> {
            List<Person> persons = personService.getAll();
            return objectMapper.writeValueAsString(persons);
        });

        Spark.get("/ping", (rq, rs) -> "pong");
    }


    private void configureSpark() {
        Spark.port(8080);
        Spark.secure(
          keystorePath, hiddenProperties.get("ssl.keystorePassword"),
          truststorePath, hiddenProperties.get("ssl.truststorePassword")
        );
    }

}
