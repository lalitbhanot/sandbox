package com.lalit.bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// Problems here are as follows
// 1. The test code is tightly coupled with test data.
// What if we want to run BookShelfSpec with different data based on some condition
//2. We can’t reuse test data.
@ExtendWith(BooksParameterResolver.class)
public class BookShelfBookExceptionHandlingTestV6 {
    private BookShelfV2 shelf;

    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;


//injected Map into Init
// To make your test aware of your own custom resolver,
// you should annotate your test class with the ExtendWith annotation

    @BeforeEach
    void init(Map<String, Book> books)  {
        shelf = new BookShelfV2();
        this.effectiveJava = books.get("Effective Java");
        this.codeComplete = books.get("Code Complete");
        this.mythicalManMonth = books.get("The Mythical Man-Month");
        this.cleanCode = books.get("Clean Code");
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


    @Test
    public void shelfEmptyWhenNoBookAdded() {
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
    }

    @Test
    public void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
        shelf.add(effectiveJava, codeComplete);
        List<Book> books = shelf.books();
        assertEquals(2, books.size(), () -> "BookShelf should have two books.");
    }

    @Test
    public void emptyBookShelfWhenAddIsCalledWithoutBooks() {
        shelf.add();
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
    }


    //
    // Primitive Way: Using the try-catch-fail
    @Test
    public void booksReturnedFromBookShelfIsImmutableForClient() throws BookShelfCapacityReached {
        shelf.addNew(effectiveJava, codeComplete);
        List<Book> books = shelf.books();
        try {
            books.add(mythicalManMonth);
            fail(() -> "Should not be able to add book to books");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
        }
    }
    @Test
    void throwsExceptionWhenBooksAreAddedAfterCapacityIsReached() throws BookShelfCapacityReached {
        BookShelfV2 bookShelfV2 = new BookShelfV2(2);
        bookShelfV2.addNew(effectiveJava, codeComplete);
        BookShelfCapacityReached throwException = assertThrows(BookShelfCapacityReached.class, () -> bookShelfV2.add(mythicalManMonth));
        assertEquals("BookShelf capacity of 2 is reached. You can't add more books.", throwException.getMessage());
    }
}