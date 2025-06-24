package com.lalit.bookstoread;

import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A bookshelf")
public class BookShelfTest {
    private BookShelf bookShelf;
    private BookShelf shelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;

    @BeforeAll
    @DisplayName("Before All Method")
    static void BeforeAllTestShelf() {
        System.out.println("Before All runs first");
    }

    @AfterAll
    static void AfterAllTestShelf() {
        System.out.println("Last method to run for clean up ");

    }

    @BeforeEach
    void init() {
        bookShelf = new BookShelf();
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

    @Test
    void bookshelfArrangedByBookTitle() {
        BookShelf shelf = new BookShelf();
        shelf.add("Effective Java", "Code Complete", "The Mythical Man-Month");
        List<String> books = shelf.arrange();
        assertEquals
                (Arrays.asList("Code Complete", "Effective Java", "The Mythical Man-Month"),
                        books, () -> "Books in a bookshelf should be arranged lexicographically by book title");
    }

    @Test
    void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {
        BookShelf shelf = new BookShelf();
        shelf.add("Effective Java", "Code Complete", "The Mythical Man-Month");
        shelf.arrange(); // c,e, t
        List<String> books = shelf.books();
        System.out.println(books.toString()) ;
        assertEquals(Arrays.asList("Effective Java", "Code Complete", "The Mythical Man-Month"),
                books, () -> "Books in bookshelf are in insertion order");
    }

    @AfterEach
    void afterEachTestShelf() {
        System.out.println("Runs After  Each  Test Completes ");
    }

}