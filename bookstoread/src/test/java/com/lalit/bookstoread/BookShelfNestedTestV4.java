package com.lalit.bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

// Problems here are as follows
// 1. The test code is tightly coupled with test data.
// What if we want to run BookShelfSpec with different data based on some condition
//2. We can’t reuse test data.
public class BookShelfNestedTestV4 {
    private BookShelfV2 shelf;

    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    void init() throws Exception {
        shelf = new BookShelfV2();
        effectiveJava = new Book("Joshua Bloch", "Effective Java", LocalDate.of(2008, Month.MAY, 8));
        codeComplete = new Book("Steve McConnel", "Code Complete", LocalDate.of(2004, Month.JUNE, 9));
        mythicalManMonth = new Book("Frederick Phillips Brooks", "The Mythical Man-Month", LocalDate.of(1975, Month.JANUARY, 1));
        cleanCode = new Book("Robert C. Martin", "cleanCode", LocalDate.of(2008, Month.JANUARY, 1));

    }

    @Nested
    @DisplayName("is empty")
    class IsEmpty {
        @Test
        @DisplayName("when no book is added to it")
        public void emptyBookShelfWhenNoBookAdded() {
// Test case removed for brevity
        }

        @Test
        @DisplayName("when add is called without books")
        void emptyBookShelfWhenAddIsCalledWithoutBooks() {
// Test case removed for brevity
        }
    }

    @Nested
    @DisplayName("after adding books")
    class BooksAreAdded {
        @Test
        @DisplayName("contains two books")
        void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
// Test case removed for brevity
        }

        @Test
        @DisplayName("returns an immutable books collection to client")
        void bookshelfIsImmutableForClient() {
// Test case removed for brevity

        }
    }
// Test case removed for brevity
}

