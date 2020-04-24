package com.apm.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ing. Oscar G. Medina Cruz on 28/08/17.
 *
 * Handle date actions and functions
 */

public class DateUtils {

    /**
     * Get days between two selected dates
     * @param simpleDateFormat Selected date format for input dates
     * @param startDate Start date in String following date format selected
     * @param endDate End date in String following date format selected
     * @param timeUnit Time unit in that the method return response
     * @return Number of days between two dates
     * @throws ParseException if something was wrong with dates
     */
    public static int GetDaysBetweenTwoDates(SimpleDateFormat simpleDateFormat, String startDate, String endDate, TimeUnit timeUnit) throws ParseException {
        Date date1 = simpleDateFormat.parse(startDate);
        Date date2 = simpleDateFormat.parse(endDate);
        long diff = date2.getTime() - date1.getTime();
        return (int) timeUnit.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * Get days between two selected dates
     * @param startCalendar Start date as calendar object
     * @param endCalendar End date as calendar object
     * @param timeUnit Time unit in that the method return response
     * @return Number of days between two dates
     */
    public static int GetDaysBetweenTwoDates(Calendar startCalendar, Calendar endCalendar, TimeUnit timeUnit){
        long diff = endCalendar.getTime().getTime() - startCalendar.getTime().getTime();
        return (int) timeUnit.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * Get days between two selected dates
     * @param startDate Start date as date object
     * @param endDate End date as date object
     * @param timeUnit Time unit in that the method return response
     * @return Number of days between two dates
     */
    public static int GetDaysBetweenTwoDates(Date startDate, Date endDate, TimeUnit timeUnit){
        long diff = endDate.getTime() - startDate.getTime();
        return (int) timeUnit.convert(diff, TimeUnit.MILLISECONDS);
    }

}
