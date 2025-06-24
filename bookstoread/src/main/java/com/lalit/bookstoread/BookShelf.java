package com.lalit.bookstoread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookShelf {
    private List<String> books = new ArrayList<>();

    public List<String> books() {
        // Returing Immutable class
        return Collections.unmodifiableList(books);

    }

    public void add(String... booksToAdd) {   //For each book executes the lambda expression book -> books.add(book), which adds the current book to the books collection.
        //   Arrays.stream(booksToAdd).forEach(book -> books.add(book));
        // using method referece
        Arrays.stream(booksToAdd).forEach(books::add);

    }

    @Override
    public String toString() {
        return books.toString();
    }
}
