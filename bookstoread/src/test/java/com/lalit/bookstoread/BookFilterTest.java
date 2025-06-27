package com.lalit.bookstoread;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Nested
@DisplayName("search")
public class BookFilterTest {

    private BookShelfV2 shelf;

    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    void setup() {
        shelf = new BookShelfV2();
        effectiveJava = new Book("Effective Java", "Joshua Bloch", LocalDate.of(2008, Month.MAY, 8));
        codeComplete = new Book("Code Complete", "Steve McConnel", LocalDate.of(2004, Month.JUNE, 9));
        mythicalManMonth = new Book("The Mythical Man-Month", "Frederick Phillips Brooks", LocalDate.of(1975, Month.JANUARY, 1));
        cleanCode = new Book("cleanCode", "Robert C. Martin", LocalDate.of(2008, Month.JANUARY, 1));

        shelf.add(codeComplete, effectiveJava, mythicalManMonth, cleanCode);
    }

    @Test
    @DisplayName(" should find books with title containing text")
    void shouldFindBooksWithTitleContainingText() {
        List<Book> books = shelf.findBooksByTitle("Code");
        assertThat(books.size()).isEqualTo(2);
    }

    @Test
    @DisplayName(" should find books with title containing text and published after specified date.")
    void shouldFilterSearchedBooksBasedOnPublishedDate() {
        // these are boolean conditions
        List<Book> books = shelf.findBooksByTitle("code", b -> b.getPublishedOn().isBefore(LocalDate.of(2014, 12, 31)));
        assertThat(books.size()).isEqualTo(2);
    }

    @Test
    @DisplayName(" should find books with title containing text and published after specified date.")
    void shouldFilterSearchedBooksBasedOnAuthor() {
        // these are boolean conditions
        List<Book> books = shelf.findBooksByTitle("effective java", b -> b.getAuthor().equalsIgnoreCase("Joshua Bloch"));
        assertThat(books.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("is after specified year")
    void validateBookPublishedDatePostAskedYear() {
        BookFilter filter = BookPublishedYearFilter.After(2007);
        assertTrue(filter.apply(cleanCode));
        assertFalse(filter.apply(codeComplete));
    }

    @Test
    @DisplayName("Composite criteria is based on multiple filters")
    void shouldFilterOnMultiplesCriteria() {
        CompositeFilter compositeFilter = new CompositeFilter();
        compositeFilter.addFilter(b -> false);
        assertFalse(compositeFilter.apply(cleanCode));
    }

    @Test
    @DisplayName("Composite criteria does not invoke after first failure")
    void shouldNotInvokeAfterFirstFailure() {
        CompositeFilter compositeFilter = new CompositeFilter();
        compositeFilter.addFilter(b -> false);
        compositeFilter.addFilter(b -> true);
        assertFalse(compositeFilter.apply(cleanCode));
    }

    @Test
    @DisplayName("Composite criteria invokes all filters")
    void shouldInvokeAllFilters() {
        CompositeFilter compositeFilter = new CompositeFilter();
        compositeFilter.addFilter(b -> true);
        compositeFilter.addFilter(b -> true);
        assertTrue(compositeFilter.apply(cleanCode));
    }


}

