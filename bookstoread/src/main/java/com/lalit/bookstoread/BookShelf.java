package com.lalit.bookstoread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class BookShelf {
    private List<String> books = new ArrayList<>();

    public List<String> books() {
        return books ;
    }
    public void  addBooks(String bookname)
    {
            books.add(bookname) ;
    }

    @Override
    public String toString() {
        return books.toString();
    }
}
