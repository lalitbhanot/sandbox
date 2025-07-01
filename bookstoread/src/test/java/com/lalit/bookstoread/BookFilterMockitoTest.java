package com.lalit.bookstoread;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Nested
@DisplayName("search")
public class BookFilterMockitoTest implements FilterBoundaryTest {
    // chapter 5 : need to study again
    private BookShelfV2 shelf;

    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;
    BookFilter filter;
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
    @DisplayName("Composite criteria does not invoke after first failure")
    void shouldNotInvokeAfterFirstFailure() {
        CompositeFilter compositeFilter = new CompositeFilter();
        BookFilter invokedMockedFilter = Mockito.mock(BookFilter.class);
        Mockito.when(invokedMockedFilter.apply(cleanCode)).thenReturn(false);
        compositeFilter.addFilter(invokedMockedFilter);

        BookFilter nonInvokedMockedFilter = Mockito.mock(BookFilter.class);
        Mockito.when(nonInvokedMockedFilter.apply(cleanCode)).thenReturn(true);
        compositeFilter.addFilter(nonInvokedMockedFilter);

        assertFalse(compositeFilter.apply(cleanCode));
        Mockito.verify(invokedMockedFilter).apply(cleanCode);
        Mockito.verifyNoInteractions(nonInvokedMockedFilter);
    }

    @Test
    @DisplayName("Composite criteria invokes all filters")
    void shouldInvokeAllFilters() {
        CompositeFilter compositeFilter = new CompositeFilter();
        BookFilter firstInvokedMockedFilter = Mockito.mock(BookFilter.class);
        Mockito.when(firstInvokedMockedFilter.apply(cleanCode)).thenReturn(true);
        compositeFilter.addFilter(firstInvokedMockedFilter);

        BookFilter secondInvokedMockedFilter = Mockito.mock(BookFilter.class);
        Mockito.when(secondInvokedMockedFilter.apply(cleanCode)).thenReturn(true);
        compositeFilter.addFilter(secondInvokedMockedFilter);

        assertTrue(compositeFilter.apply(cleanCode));

        Mockito.verify(firstInvokedMockedFilter).apply(cleanCode);
        Mockito.verify(secondInvokedMockedFilter).apply(cleanCode);

    }

    public BookFilter get() {
        return filter;
    }
}