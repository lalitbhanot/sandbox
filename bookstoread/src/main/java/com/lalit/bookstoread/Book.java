package com.lalit.bookstoread;

import java.time.LocalDate;

public class Book implements Comparable<Book> {
    private final String title;
    private final String author;
    private final LocalDate publishedOn;
    private LocalDate startedReadingOn;
    private LocalDate finishedReadingOn;


    public Book(String title,String author, LocalDate publishedOn) {
        this.title = title;
        this.author = author;
        this.publishedOn = publishedOn;

    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getFinishedReadingOn() {
        return finishedReadingOn;
    }

    public void setFinishedReadingOn(LocalDate finishedReadingOn) {
        this.finishedReadingOn = finishedReadingOn;
    }


    public LocalDate getStartedReadingOn() {
        return startedReadingOn;
    }

    public void setStartedReadingOn(LocalDate startedReadingOn) {
        this.startedReadingOn = startedReadingOn;
    }

    @Override
    public int compareTo(Book that) {
        return this.title.compareToIgnoreCase(that.title);
    }

    public void startedReadingOn(LocalDate startedOn) {
        this.startedReadingOn = startedOn;
    }

    public void finishedReadingOn(LocalDate finishedOn) {
        this.finishedReadingOn = finishedOn;
    }

    public boolean isRead() {
        return startedReadingOn != null && finishedReadingOn != null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publishedOn=" + publishedOn +
                ", startedReadingOn=" + startedReadingOn +
                ", finishedReadingOn=" + finishedReadingOn +

                '}';
    }
}
