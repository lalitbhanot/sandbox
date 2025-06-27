package com.lalit.bookstoread;

import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class BookShelfV2 {


    private final List<Book> books = new ArrayList<>();

    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    public void add(Book... booksToAdd) {
        Arrays.stream(booksToAdd).forEach(books::add);
    }

    public List<Book> arrange() {
        return books.stream().sorted().collect(toList());
    }

    public List<Book> arrangeUsingComparator(Comparator<Book> comparator) {
        return books.stream()
                .sorted(comparator)
                .collect(toList());
        //This is a cleaner approach no need to make any change in the  Book class , can be used for multiple fields
        //Sort by Title: List<Book> sortedByTitle = arrangeUsingComparator(Comparator.comparing(Book::getTitle));
        //Sort by Author: List<Book> sortedByAuthor = arrangeUsingComparator(Comparator.comparing(Book::getAuthor));
        //Sort by Publication Date: List<Book> sortedByPublicationDate = arrangeUsingComparator(Comparator.comparing(Book::getPublicationDate));
        //A Custom Comparator : Comparator<Book> titleDescending = Comparator.comparing(Book::getTitle).reversed();

    }

    public List<Book> arrange(Comparator<Book> criteria) {
        return books.stream().sorted(criteria).collect(toList());
    }

//    public Map<Year, List<Book>> groupByPublicationYear() {
//        return books
//                .stream()
//                .collect(groupingBy(book -> Year.of(book.getPublishedOn().getYear())));
//    }

    public Map<Year, List<Book>> groupByPublicationYear() {
        return groupBy(book -> Year.of(book.getPublishedOn().getYear()));
    }

    public <K> Map<K, List<Book>> groupBy(Function<Book, K> fx) {
        return books
                .stream()
                .collect(groupingBy(fx));
    }

    public Progress progress() {
        int booksRead = Long.valueOf(books.stream().filter(Book::isRead).count()).intValue();
        int booksToRead = books.size() - booksRead;
        int percentageCompleted = booksRead * 100 / books.size();
        int percentageToRead = booksToRead * 100 / books.size();
        return new Progress(percentageCompleted, percentageToRead, 0);
    }

    public List<Book> findBooksByTitle(String code) {
        System.out.println(books.toString());
        List<Book> bookSearched = books.stream().filter(book -> book.getTitle().contains(code)).collect(Collectors.toList());
        return bookSearched ;
    }

//    public List<Book> findBooksByTitle(String title, Predicate<Book> filter) {
//        return books.stream()
//                .filter(b -> b.getTitle().toLowerCase().contains(title))
//                .filter(filter)
//                .collect(toList());
//    }

    public List<Book> findBooksByTitle(String title, BookFilter filter) {
        System.out.println(books.toString());
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title))
                .filter(b -> filter.apply(b))
                .collect(toList());
    }


}
