package com.lalit.bookstoread;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A bookshelf")
public class BookShelfTest {
   private  BookShelf bookShelf ;
    @BeforeAll
    @DisplayName("Before All Method")
    static void BeforeAllTestShelf() {
        System.out.println("Before All runs first");
    }

    @BeforeEach
    void init() {
        bookShelf= new BookShelf();
        System.out.println("Runs Before  Each Test Begins ");
    }

    @Test
    @DisplayName("is empty when no book is added to it")
    public void shelfEmptyWhenNoBookAdded() throws Exception {

        List<String> books = bookShelf.books();
        assertTrue(books.isEmpty(), () -> "Book Shelf should be empty");
        // see junit_learning.md point 1
    }

    @Test
    @DisplayName("Shelf To String Should Print Book Count And Titles")

    public void shelfToStringShouldPrintBookCountAndTitles() throws Exception {

        List<String> books = bookShelf.books();
        bookShelf.add("The Phoenix Project", "Java 8 in Action", "New Book added");
        //  shelf.addBooks();
        String shelfStr = bookShelf.toString();
        assertAll(() -> assertTrue(shelfStr.contains("The Phoenix Project"), () -> "1st book title "),
                () -> assertTrue(shelfStr.contains("Java 8 in Action"), () -> "2nd book title  "),
                () -> assertTrue(bookShelf.books().size() > 0, () -> " Current Book  count " + bookShelf.books().size()));

    }

    @Test
    public void emptyBookShelfWhenAddIsCalledWithoutBooks() {

        bookShelf.add();
        List<String> books = bookShelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
    }

    @Test
    void booksReturnedFromBookShelfIsImmutableForClient() {

        bookShelf.add("Effective Java", "Code Complete");
        List<String> books = bookShelf.books(); // Retreiveing the books
        try {
            books.add("The Mythical Man-Month"); // adding the books to book list basically modifing the booklist
                                                //the collection that we received from the books method
            // is mutable so we were able to successfully add a book to it.
            fail(() -> "Should not be able to add book to books");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
        }
    }


    @AfterEach
    void afterEachTestShelf() {
        System.out.println("Runs After  Each  Test Completes ");
    }

    @AfterAll
    static void AfterAllTestShelf() {
        System.out.println("Last method to run for clean up ");

    }

}