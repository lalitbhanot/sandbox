package com.lalit.bookstoread;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.api.DynamicTest.stream;


public class MyDynamicTests {

    @TestFactory
    Collection<DynamicTest> generateFirstTest() {
        return Arrays.asList(
                dynamicTest("Week Test", () -> assertEquals(DayOfWeek.MONDAY, DayOfWeek.of(1))),
                dynamicTest("Month Test", () -> assertEquals(Month.JANUARY, Month.of(1)))
        );
    }

    @TestFactory
    Stream<DynamicTest> generateParameterizedTest() {
        LocalDate startDate = LocalDate.now();
        Iterator<LocalDate> daysIter = Stream.iterate(startDate, date -> date.plusDays(1)).limit(10).iterator();
        return  stream(daysIter, d -> DateTimeFormatter.ISO_LOCAL_DATE.format(d), d -> assertNotNull(d));
    }


}
