package com.company;

import java.time.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.company.SmsApp.SmsAppMethod;

public class Main {

    public static void main(String[] args) {

        /**
         * invoking the method that runs the localhost server and sets up the GET
         * and POST logic
         *
         * this includes responding to incoming text messages
         * i.e. adding/removing users based on "start", "stop" texts
         */

        SmsAppMethod();

        /**
         * this class sets up a periodic task to be run at 7am local time each day
         * for 365 days
         *
         * it runs class ChronJob(); which holds the main functionality of the app,
         * mainly the api request for the data, the parsing of the json object,
         * and the sending of the text to the users
         */

        // I want it to run only when it hits 7am not immediately

        LocalTime now = ZonedDateTime.now().toLocalTime();
        LocalTime sevenAM = LocalTime.of(14, 0, 0);

        LocalTime twentyFourHours = LocalTime.of(23, 59, 59, 99999);
        LocalTime zeroHours = LocalTime.of(0, 0, 0);

        System.out.println(now);
        System.out.println(sevenAM);

        // time until 7am today
        Duration duration = Duration.between(now, sevenAM);
        // time until the end of the day
        Duration duration2 = Duration.between(now, twentyFourHours);
        // time until 7am the following day
        Duration duration3 = Duration.between(zeroHours, sevenAM);

        long timeUntilSevenAM = duration.toMillis();

        if (now.isBefore(sevenAM)) {
            System.out.println("before 7am, will run today");
        } else {
            System.out.println("already past 7am, will run tomorrow");
            timeUntilSevenAM = duration2.toMillis() + duration3.toMillis();
        }

        System.out.println(timeUntilSevenAM/1000/60);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        ChronJob task = new ChronJob();
        scheduler.scheduleAtFixedRate(task, timeUntilSevenAM, TimeUnit.DAYS.toMillis( 1 ), TimeUnit.MILLISECONDS);
    }

}
