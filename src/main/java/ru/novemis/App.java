package ru.novemis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.novemis.config.Beans;

public class App {

    public static void main(String[] args) throws Throwable {
        AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext(Beans.class);
    }

}
