package com.revature.examples.dates;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * java.time types are immutable -- plusDays(...) returns a new object rather
 * than changing the one you called it on.
 */
public class DateInfo {

    public static void main(String[] args) {
//        now();
//        parsing();
//        formatting();
//        arithmetic();
//        zonesAndInstants();
//        comparisons();
        epoch();
    }

    /** 1. The current moment. Variants: LocalDate.now(), LocalTime.now(),
     *  ZonedDateTime.now(zone), Instant.now() (UTC). */
    static void now() {
        System.out.println("-- now --");
        // this is the simplest way to get the current date and time in Java
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
    }

    /** 2. Text into objects. ISO-8601 parses with no formatter; anything else needs one. */
    static void parsing() {
        System.out.println("\n-- parsing --");
        System.out.println(LocalDate.parse("2026-09-17"));
    }

    /** 3. Objects back into text. ISO-8601 is the default format. */
    static void formatting() {
        /*
            Note the var keyword: this tells the compiler to check the assigned value for the type of the variable. It is
            an alternate option for declaring your data type, it does not break Java's static typing
         */
        var date = LocalDate.of(2026, 9, 17);
        var formatter = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", Locale.UK);

        System.out.println("\n-- formatting --");
        System.out.println(date.format(formatter));
    }

    /** 4. Moving through time. Every call returns a new object; use Period/Duration/ChronoUnit to measure
     * the gap between two values. */
    static void arithmetic() {
        var date = LocalDate.of(2026, 9, 17);

        System.out.println("\n-- arithmetic --");
        System.out.println(date.plusDays(10));
        // remember your dates are immutable
        System.out.println(date);
    }

    /** 5. Zones. An Instant is UTC; the same moment reads differently per zone. */
    static void zonesAndInstants() {
        var instant = Instant.parse("2026-09-17T04:45:30Z");

        System.out.println("\n-- zones --");
        /*
            If you need to convert between time zones you can do so with the atZone method passing it a ZoneId
         */
        System.out.println(instant.atZone(ZoneId.of("Asia/Tokyo")));
        // Java has a comprehensive collection of available zone ids
        System.out.println(ZoneId.getAvailableZoneIds());

    }

    /** 6. Comparison. isBefore/isAfter compare points in time. */
    static void comparisons() {
        var a = LocalDate.of(2026, 9, 17);
        var b = LocalDate.of(2027, 1, 1);

        System.out.println("\n-- comparisons --");
        System.out.println(a.isBefore(b));
    }

    /** 7. Epoch time. Seconds (or millis) since 1970-01-01T00:00:00Z; an Instant round-trips with
     * ofEpochSecond/getEpochSecond (and the Milli pair). */
    static void epoch() {
        var instant = Instant.ofEpochSecond(1_787_000_000L);
        System.out.println("\n-- epoch --");
        System.out.println(instant);
    }
}
