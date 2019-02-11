package com.company;

import java.util.Calendar;
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
         * this class sets up a periodic task to be run at 11am local time each day
         * for 7 days
         *
         * it runs class ChronJob(); which holds the main functionality of the app,
         * mainly the api request for the data, the parsing of the json object,
         * and the sending of the text to the users
         */

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 7);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        java.util.Timer timer = new java.util.Timer();
        ChronJob mainFunctionality = new ChronJob();
        timer.schedule(mainFunctionality, today.getTime(), TimeUnit.MILLISECONDS.convert(365, TimeUnit.DAYS));

    }

}
