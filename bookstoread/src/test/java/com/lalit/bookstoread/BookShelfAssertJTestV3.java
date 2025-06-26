package com.lalit.bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;


public class BookShelfAssertJTestV3 {
    private BookShelfV2 shelf;

    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    void init() throws Exception {
        shelf = new BookShelfV2();
        effectiveJava = new Book("Effective Java","Joshua Bloch" , LocalDate.of(2008, Month.MAY, 8));
        codeComplete = new Book("Code Complete","Steve McConnel" , LocalDate.of(2004, Month.JUNE, 9));
        mythicalManMonth = new Book("The Mythical Man-Month","Frederick Phillips Brooks",  LocalDate.of(1975, Month.JANUARY, 1));
        cleanCode = new Book("cleanCode","Robert C. Martin" , LocalDate.of(2008, Month.JANUARY, 1));

    }

    @Test
    @DisplayName("bookshelf is arranged lexicographically by book title")
    void bookshelfArrangedByUserProvidedCriteria() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        Comparator<Book> reversed = Comparator.<Book>naturalOrder().reversed();
        List<Book> books = shelf.arrange(reversed);
        assertThat(books).isSortedAccordingTo(reversed);
    }

    @Test
    void groupBooksInsideBookShelfByPublicationYear() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        Map<Year, List<Book>> booksByPublicationYear = shelf.groupByPublicationYear();
        assertThat(booksByPublicationYear).containsKey(Year.of(2008)).containsValues(Arrays.asList(effectiveJava, cleanCode));
        assertThat(booksByPublicationYear).containsKey(Year.of(2004)).containsValues(singletonList(codeComplete));
        assertThat(booksByPublicationYear).containsKey(Year.of(1975)).containsValues(singletonList(mythicalManMonth));
    }

    @Test
    @DisplayName("books inside bookshelf are grouped according to user provided criteria(group by author name)")
    void groupBooksByUserProvidedCriteria() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        Map<String, List<Book>> booksByAuthor = shelf.groupBy(Book::getAuthor);
        assertThat(booksByAuthor).containsKey("Joshua Bloch").containsValues(singletonList(effectiveJava));
        assertThat(booksByAuthor).containsKey("Steve McConnel").containsValues(singletonList(codeComplete));
        assertThat(booksByAuthor).containsKey("Frederick Phillips Brooks").containsValues(singletonList(mythicalManMonth));
        assertThat(booksByAuthor).containsKey("Robert C. Martin").containsValues(singletonList(cleanCode));
    }

}
