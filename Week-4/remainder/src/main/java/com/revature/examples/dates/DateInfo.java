package com.revature.examples.dates;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * java.time types are immutable -- plusDays(...) returns a new object rather
 * than changing the one you called it on.
 */
public class DateInfo {

    public static void main(String[] args) {
        now();
        parsing();
        formatting();
        arithmetic();
        zonesAndInstants();
        comparisons();
        epoch();
    }

    /** 1. The current moment. Variants: LocalDate.now(), LocalTime.now(),
     *  ZonedDateTime.now(zone), Instant.now() (UTC). */
    static void now() {
        System.out.println("-- now --");
        System.out.println(LocalDateTime.now());
    }

    /** 2. Text into objects. ISO-8601 parses with no formatter; anything else needs one. */
    static void parsing() {
        System.out.println("\n-- parsing --");
        System.out.println(LocalDate.parse("2026-09-17"));
    }

    /** 3. Objects back into text. ISO-8601 is the default format. */
    static void formatting() {
        var date = LocalDate.of(2026, 9, 17);
        var formatter = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", Locale.UK);

        System.out.println("\n-- formatting --");
        System.out.println(date.format(formatter));
    }

    /** 4. Moving through time. Every call returns a new object; use Period/Duration/ChronoUnit to measure the gap between two values. */
    static void arithmetic() {
        var date = LocalDate.of(2026, 9, 17);

        System.out.println("\n-- arithmetic --");
        System.out.println(date.plusDays(10));
    }

    /** 5. Zones. An Instant is UTC; the same moment reads differently per zone. */
    static void zonesAndInstants() {
        var instant = Instant.parse("2026-09-17T04:45:30Z");

        System.out.println("\n-- zones --");
        System.out.println(instant.atZone(ZoneId.of("Asia/Tokyo")));
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
