package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.stream.Stream;

/**
 * Represents a Parser to parse date and time.
 */
public class Parser {

    /**
     * Constructs an instance of Parser.
     */
    public Parser() {
        // Empty
    }

    /**
     * Parses a string representation of a date into LocalDate.
     * Expects input to be formatted as "YYYY-MM-DD" (fixed to "-" seperator).
     * TODO: need to catch exceptions
     *
     * @param dateStr String representation of a date.
     * @return Date in a LocalDate instance.
     */
    private LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr);
    }

    /**
     * Parses a string representation of a date into LocalDate.
     * Expects input to be formatted as "YYYY-MM-DD" but seperator is arbitrary.
     * Arbitrary seperator needs to be specified.
     * TODO: need to catch exceptions
     *
     * @param dateStr String representation of a date.
     * @param seperator Seperator used to separate the day, month, year.
     * @return Date in a LocalDate instance.
     */
    private LocalDate parseDate(String dateStr, char seperator) {
        String[] date = dateStr.split(String.valueOf(seperator));
        int[] dateInfo = Stream.of(date).mapToInt(Integer::parseInt).toArray();
        return LocalDate.of(dateInfo[0], dateInfo[1], dateInfo[2]);
    }

    /**
     * Parses a string representation of a time into LocalTime.
     * Expects input to be formatted as "HH:MM:SS" or "HH:MM" (fixed to ':' seperator).
     * TODO: need to catch exceptions
     *
     * @param timeStr String representation of a time.
     * @return Time in a LocalTime instance.
     */
    private LocalTime parseTime(String timeStr) {
        return LocalTime.parse(timeStr);
    }

    /**
     * Parses a string representation of a time into LocalTime.
     * Expects input to be formatted as "HH:MM:SS" or "HH:MM" but seperator is arbitrary.
     * Arbitrary seperator needs to be specified.
     * TODO: need to catch exceptions
     *
     * @param timeStr String representation of a time.
     * @param seperator Seperator used to separate the hour, minute, (second).
     * @return Time in a LocalTime instance.
     */
    private LocalTime parseTime(String timeStr, char seperator) {
        String[] time = timeStr.split(String.valueOf(seperator));
        int[] timeInfo = Stream.of(time).mapToInt(Integer::parseInt).toArray();
        if (timeInfo.length == 2)
            return LocalTime.of(timeInfo[0], timeInfo[1]);
        else
            return LocalTime.of(timeInfo[0], timeInfo[1], timeInfo[2]);
    }

    /**
     * Parses a string representation of a date and time into LocalDateTime.
     * Expects input to be formatted as "YYYY-MM-DD HH:MM:SS" or "YYYY-MM-DD HH:MM" (fixed to ' ' seperator).
     *
     * @param str String representation of a date & time.
     * @return Date and time in a LocalDateTime instance.
     */
    public LocalDateTime parseDateTime(String str) {
        return parseDateTime(str, ' ');
    }

    /**
     * Parses a string representation of a date and time into LocalDateTime.
     * Expects input to be formatted as "YYYY-MM-DD HH:MM:SS" or "YYYY-MM-DD HH:MM" but seperator is arbitrary.
     * Arbitrary seperator (between date and time) needs to be specified.
     *
     * @param str String representation of a date & time.
     * @param seperator Character separating the date and time.
     * @return Date and time in a LocalDateTime instance.
     */
    public LocalDateTime parseDateTime(String str, char seperator) {
        String[] s = str.split(String.valueOf(seperator));
        return LocalDateTime.of(parseDate(s[0]), parseTime(s[1]));
    }

}
