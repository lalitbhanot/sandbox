package com.lalit.bookstoread;

import java.time.LocalDate;
import java.util.function.Function;

public class BookPublishedYearFilterV2 implements BookFilter {
    private Function<LocalDate, Boolean> comparison;

    static BookPublishedYearFilterV2 After(int year) {
        final LocalDate date = LocalDate.of(year, 12, 31);
        BookPublishedYearFilterV2 filter = new BookPublishedYearFilterV2();
        filter.comparison = date::isBefore;
        return filter;
    }

    static BookPublishedYearFilterV2 Before(int year) {
        final LocalDate date = LocalDate.of(year, 1, 1);
        BookPublishedYearFilterV2 filter = new BookPublishedYearFilterV2();
        filter.comparison = date::isAfter;
        return filter;
    }

    @Override
    public boolean apply(final Book b) {
        return b!=null && comparison.apply(b.getPublishedOn());
    }
}