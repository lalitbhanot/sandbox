package com.lalit.bookstoread;


import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


class BooksParameterResolver implements ParameterResolver {


    private final Map<String, Book> books;

    public BooksParameterResolver() {
        Map<String, Book> books = new HashMap<>();
        books.put("Effective Java", new Book("Effective Java", "Joshua Bloch", LocalDate.of(2008, Month.MAY, 8)));
        books.put("Code Complete", new Book("Steve McConnel", "Code Complete", LocalDate.of(2004, Month.JUNE, 9)));
        books.put("The Mythical Man-Month", new Book("Frederick Phillips Brooks", "The Mythical Man-Month", LocalDate.of(1975, Month.JANUARY, 1)));
        books.put("Clean Code", new Book("Robert C. Martin", "Clean Code", LocalDate.of(2008, Month.AUGUST, 1)));
        books.put("Refactoring", new Book("Martin Fowler", "Refactoring: Improving the Design of Existing Code", LocalDate.of(2002, Month.MARCH, 9)));
        this.books = books;
    }

    //We implemented the supportsParameter method that checked whether the parameterized type of the parameter has the type of java.util.Map<java.lang.String, bookstoread.Book>
    //ParameterContext: It contains the details of the parameter being asked for.
   // ExtensionContext: It contains the context of the current test. Each test execution creates its own ExentionContext. The context contains details like test method, id, and so on.
    @Override
    public boolean supportsParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        return Objects.equals(parameter.getParameterizedType().getTypeName(), "java.util.Map<java.lang.String, com.lalit.bookstoread.Book>"); // give the entire classpath of the class here
    }

    @Override
    public Map<String, Book> resolveParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) throws ParameterResolutionException {
        return books;
    }
}


//
//@Override
/// /Next, we implemented the resolveParameter method, which returned a Map containing the test data.
//public Object resolveParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) throws ParameterResolutionException {
//    Map<String, Book> books = new HashMap<>();
//    books.put("Effective Java", new Book("Joshua Bloch", "Effective Java", LocalDate.of(2008, Month.MAY, 8)));
//    books.put("Code Complete", new Book("Steve McConnel", "Code Complete", LocalDate.of(2004, Month.JUNE, 9)));
//    books.put("The Mythical Man-Month", new Book("Frederick Phillips Brooks", "The Mythical Man-Month", LocalDate.of(1975, Month.JANUARY, 1)));
//    books.put("Clean Code", new Book("Robert C. Martin", "Clean Code", LocalDate.of(2008, Month.AUGUST, 1)));
//    books.put("Refactoring", new Book("Martin Fowler", "Refactoring: Improving the Design of Existing Code", LocalDate.of(2002, Month.MARCH, 9)));
//    return books;
//
//    //this.books = books;
//}

