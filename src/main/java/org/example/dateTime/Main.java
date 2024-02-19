package org.example.dateTime;
import java.time.Duration;
import java.time.OffsetTime;
import java.util.Calendar;
import org.threeten.extra.DayOfYear;
import org.threeten.extra.Days;
import org.threeten.extra.Interval;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.threeten.extra.Quarter;
import org.threeten.extra.Weeks;
import org.threeten.extra.YearQuarter;
import org.threeten.extra.YearWeek;
import org.threeten.extra.Years;

// Java 5
public class Main {

  public static void main(String[] args) {
    //beforeJava5();
    //java5();
    //java8();
   //threeTenExtra();
    //java11_12();
  }

    public static void beforeJava5() {
      // Before java 5, there was no Timezone class
      // So, you had to use the following code to format the date in New York time zone
      Date date1 = new Date(System.currentTimeMillis());

      int timeZoneOffsetMillis = 3 * 60 * 60 * 1000; // 3 hours in milliseconds

      // Adjust the date by adding the time zone offset
      long adjustedTimeInMillis = date1.getTime() + timeZoneOffsetMillis;

      // Create a new Date object with the adjusted time
      Date adjustedDate = new Date(adjustedTimeInMillis);

      // 3 hours added to the current time
      System.out.println("Ajusted 3 hours: " + adjustedDate);

      Calendar calendar = Calendar.getInstance();

      System.out.println("Calendar: " + calendar.getTime()); // current time

    }


  // 2002 first version of Joda Time, many helpful classes,
  // DateTime, DateTimeZone, LocalDate, LocalTime, LocalDateTime, Period, Duration, Interval, and many more.

  // java 5 -> Timezone :D
  public static void java5() {
    Date date = new Date(System.currentTimeMillis());

    System.out.println("Local Now date" + date);

    // Use date1 to format in New York time zone and print it
    TimeZone tz = TimeZone.getTimeZone("America/New_York");

    // SimpleDateFormat is not thread safe, so create a new instance for each thread
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    sdf.setTimeZone(tz);

    // Format the date using the SimpleDateFormat object
    String formattedDate = sdf.format(date);

    // Print the formatted date
    System.out.println("Formatted date in New York time zone: " + formattedDate);

    // Quick way to convert from 1 day to hours
    // using units .toHours()

    long hours = TimeUnit.HOURS.convert(2, TimeUnit.DAYS);

    System.out.println("\nDays to hours: " + hours);

  }


    public static void java8() {

      // New classes in Java 8, JSR-310 (Date and Time API)
      // ZonedDateTime,
      var zonedDateTime = ZonedDateTime.now();
      System.out.printf("\nZoneDateTime: %s \n",zonedDateTime);
      // ZoneDateTime 6 month from now
      var zonedDateTime6Months = zonedDateTime.plusMonths(6);
      System.out.printf("ZoneDateTime 6 months from now: %s \n\n",zonedDateTime6Months);

      // LocalDate,
      var localDate = LocalDate.now();
      System.out.printf("LocalDate %s \n",localDate);

      // LocalTime,
      var localTime = LocalTime.now();
      System.out.printf("LocalTime %s \n",localTime);

      // LocalDateTime,
      var localDateTime = LocalDateTime.now();
      System.out.printf("LocalDateTime %s \n", localDateTime);

      // OffsetDateTime, Offset from UTC
      var offsetDateTime = zonedDateTime.toOffsetDateTime();
      System.out.printf("OffsetDateTime %s \n",offsetDateTime);



      // Period,
      var period = localDate.until(localDate.plusDays(5));
      System.out.printf("Period %s \n", period);
      // Duration,
     // Duration duration = localTime.until(localTime.plusHours(5), ChronoUnit.HOURS);
      //System.out.printf("Duration %s \n",duration);
      // DateTimeFormatter
      //  ISO 8601 supports date and time, and time zone
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      System.out.printf(formatter.format(localDateTime));

      // Instant is a point in time, it is not affected by time zone
      var instant = zonedDateTime.toInstant();
      System.out.printf("\nInstant %s \n",instant);

    }

    public static void threeTenExtra() {
      // ThreeTen-Extra library by Stephen Colebourne, the creator of Joda-Time
      // Interval
      // Interval is a time interval between two instants
      var zonedDateTime = ZonedDateTime.now();
      System.out.printf("\nZoneDateTime: %s \n",zonedDateTime);
      // ZoneDateTime 6 month from now
      var zonedDateTime6Months = zonedDateTime.plusMonths(6);
      Interval interval = Interval.of(zonedDateTime.toInstant(), zonedDateTime6Months.toInstant());
      System.out.printf("Interval %s \n",interval);

      // YearWeek
      YearWeek yearWeek = YearWeek.now();
      System.out.printf("YearWeek: %s \n",yearWeek);

      // YearQuarter
      YearQuarter yarQuarter = org.threeten.extra.YearQuarter.now();
      System.out.printf("YearQuarter: %s \n",yarQuarter);

      // DayOfYear
      DayOfYear dayOfYear = org.threeten.extra.DayOfYear.now();
      System.out.printf("DayOfYear: %s \n",dayOfYear);

      // Days
      Days days = org.threeten.extra.Days.of(5);
      System.out.printf("Days: %s \n",days);

      // Weeks
      Weeks weeks = org.threeten.extra.Weeks.of(5);
      System.out.printf("Weeks: %s \n",weeks);

      // Quarters
      Quarter quarters = org.threeten.extra.Quarter.of(4);
      System.out.printf("Quarters: %s \n",quarters);

      // Years
      Years years = org.threeten.extra.Years.of(2021);
      System.out.printf("Years: %s \n",years);
    }

    public static void java11_12() {
    var yearMonth = java.time.YearMonth.now();
    System.out.printf("\nYearMonth: %s \n",yearMonth);

    var monthDay = java.time.MonthDay.now();
    System.out.printf("MonthDay: %s \n",monthDay);

    }

    public static void java13() {

       LocalTime localTime = LocalTime.now();
       ZonedDateTime zonedDateTime = ZonedDateTime.now();

      // OffsetTime,
      OffsetTime offsetTime = localTime.atOffset(zonedDateTime.getOffset());
      System.out.printf("OffsetTime %s \n\n",offsetTime);
    }
}
