package com.bookstore;

import com.bookstore.dto.AuthorDtoNoSetters;
import com.bookstore.dto.AuthorDtoWithSetters;
import java.util.List;
import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("Fetch authors via a DTO with setters:");

            List<AuthorDtoWithSetters> authors1 = bookstoreService.fetchAuthorsWithSetters();

            for (AuthorDtoWithSetters author : authors1) {
                System.out.println("Author name: " + author.getName()
                        + " | Age: " + author.getAge());
            }

            System.out.println("\nFetch authors via a DTO without setters:");

            List<AuthorDtoNoSetters> authors2 = bookstoreService.fetchAuthorsNoSetters();

            for (AuthorDtoNoSetters author : authors2) {
                System.out.println("Author name: " + author.getName()
                        + " | Age: " + author.getAge());
            }
        };
    }
}
/*

How To Fetch DTO Via ResultTransformer and JPQL

Description: Fetching more data than needed is prone to performance penalties. Using DTO allows us to extract only the needed data. In this application we rely on Hibernate, ResultTransformer and JPQL.

Key points:

use AliasToBeanConstructorResultTransformer for DTO without setters, with constructor
use Transformers.aliasToBean() for DTO with setters
use EntityManager.createQuery() and unwrap(org.hibernate.query.Query.class)
starting with Hibernate 5.2, ResultTransformer is deprecated, but until a replacement will be available (in Hibernate 6.0) it can be used (read further)
for using Spring Data Projections check this item

*/