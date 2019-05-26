package com.byd.test.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateUtil {

    public static LocalDate getLocalDate(String date){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        LocalDate dat = LocalDate.parse(date, inputFormatter);
        return dat;
    }

    public static LocalTime getLocalTime(String date){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        LocalTime lt = LocalTime.parse(date,inputFormatter);
        return lt;
    }

    public static String getMonthYearStyle(LocalDate local){
        return local.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) +" "+ local.getYear();
    }

    public static String getDateFormatted(LocalDate local,String date){
        return local.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " +
                local.getDayOfMonth() + ", " + local.getDayOfYear()+ " at "+getLocalTime(date).getHour()+getLocalTime(date).getMinute();
    }

    public static int getMonth(LocalDate local){
        return local.getMonthValue();
    }
}
