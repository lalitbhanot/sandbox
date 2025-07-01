package com.lalit.bookstoread;

import org.junit.jupiter.api.BeforeEach;

class BookPublishedBeforeFilterTest implements FilterBoundaryTest {
    BookFilter filter;

    @BeforeEach
    void init() {
        filter = BookPublishedYearFilter.After(2007);
    }

    @Override
    public BookFilter get() {
        return filter;
    }
}