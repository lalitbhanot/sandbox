package com.lalit.bookstoread;


import java.util.ArrayList;
import java.util.List;

class CompositeFilter implements BookFilter {
    private List<BookFilter> filters;

    CompositeFilter() {
        filters = new ArrayList<>();
    }
//Streams support the method map, which takes a function as argument.
// The function is applied to each element, mapping it into a new element (the word mapping is used because it has a meaning similar to transforming but with the nuance of “creating a new version of” rather than “modifying”)
// map(bookFilter -> bookFilter.apply(b)) - Transforms each filter in the stream by applying it to the book b. Each filter's apply() method returns a boolean (true if the book passes that filter, false otherwise)
//reduce(true, (b1, b2) -> b1 && b2) - Combines all the boolean results using logical AND operation, starting with true as the initial value

//   @Override
    public boolean apply(final Book b) {
        return filters.stream().
                map(bookFilter -> bookFilter.apply(b)).
                reduce(true, (b1, b2) -> b1 && b2);

    }

    void addFilter(final BookFilter bookFilter) {
        filters.add(bookFilter);
    }
}