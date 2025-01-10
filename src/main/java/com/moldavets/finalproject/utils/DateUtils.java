package com.moldavets.finalproject.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static String parseDateToString(Date date) {
        String inputDate = "2005-02-01 00:00:00.0";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date resultDate = null;
        try {
            resultDate = inputFormat.parse(inputDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String formattedDate = outputFormat.format(resultDate);
        System.out.println(formattedDate); // Вывод: 2005-02-01
        return formattedDate;
    }

    public static Date parseStringToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date result = null;

        try {
            result = dateFormat.parse(date);
            return result;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
