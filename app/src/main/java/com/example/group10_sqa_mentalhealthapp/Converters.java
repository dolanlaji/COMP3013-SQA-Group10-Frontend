package com.example.group10_sqa_mentalhealthapp;

import androidx.room.TypeConverter;

import java.util.Date;


/**
 * Class containing Room database type converters for Date objects.
 *
 * <p>This class provides methods to convert Date objects to and from timestamps for storage in the Room database.
 */
public class Converters {
    /**
     * Converts a timestamp (Long) to a Date object.
     *
     * @param value The timestamp to convert.
     * @return A Date object representing the provided timestamp, or null if the input is null.
     */
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    /**
     * Converts a Date object to a timestamp (Long).
     *
     * @param date The Date object to convert.
     * @return A timestamp (Long) representing the provided Date, or null if the input is null.
     */
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
