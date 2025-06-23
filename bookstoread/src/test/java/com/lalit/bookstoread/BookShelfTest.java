package com.lalit.bookstoread;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("A bookshelf")
public class BookShelfTest
{
    @BeforeAll
    @DisplayName("Before All Method")
    static void BeforeAllTestShelf() {
        System.out.println("Before All runs first") ;
    }

    @BeforeEach
    void BeforeEachTestShelf() {
        System.out.println("Runs Before  Each Test Begins ") ;
    }

    @Test
    @DisplayName("is empty when no book is added to it")
    public void shelfEmptyWhenNoBookAdded() throws Exception {
        BookShelf bookShelf = new BookShelf();
        List<String> books = bookShelf.books();
        assertTrue(books.isEmpty(),()->"Book Shelf should be empty") ;
        // see junit_learning.md point 1
}
@Test
@DisplayName("Shelf To String Should Print Book Count And Titles")

public void shelfToStringShouldPrintBookCountAndTitles() throws Exception
{
    BookShelf shelf = new BookShelf();
    List<String> books = shelf.books();
    shelf.addBooks("The Phoenix Project");
    shelf.addBooks("Java 8 in Action");
    String shelfStr = shelf.toString();
    assertAll(()  -> assertTrue(shelfStr.contains("The Phoenix Project"),  "1st book title "),
            () -> assertTrue(shelfStr.contains("Java 8 in Action") , "2nd book title  "),
            () -> assertTrue(shelf.books().size()==2 , "Book  count "));

}
    @AfterEach
    void afterEachTestShelf() {
        System.out.println("Runs After  Each  Test Completes ") ;
    }

    @AfterAll
    static void AfterAllTestShelf() {
        System.out.println("Last method to run for clean up ") ;

    }

}