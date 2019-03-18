package com.company;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import static com.company.SmsSender.phoneNumbers;
import static spark.Spark.*;

public class SmsApp {

    public static void SmsAppMethod() {

        port(5678);

        // line of code below is for if you turn this into a web app
         get("/", (req, res) -> "Quote of the day running!");

        /**
         * when the twilio phone number receives an incoming text from any number...
         */

        post("/sms", (req, res) -> {

            res.type("application/xml");

            System.out.println(req.queryParams("From") + " said " + req.queryParams("Body"));

            /**
             * if the app receives a text "start", it checks to see if the number  is already
             * part of the phoneNumbers ArrayList. If so, it returns false to avoid issues of having
             * duplicate numbers in the array and therefore having double texts & trouble with the
             * 'stop' texts
             *
             * if the number is not already in the list, it is added to it. Then a message is built and
             * sent back to the user confirming their enrollment.
             */

            if (req.queryParams("Body").toLowerCase().equals("start")) {

                if (phoneNumbers.contains(req.queryParams("From"))) {
                    return false;
                }

                phoneNumbers.add(req.queryParams("From"));

                System.out.println("New member: " + req.queryParams("From"));

                Body body = new Body
                        .Builder("Thank you for enrolling in Quote of the Day! Respond 'stop' anytime to stop receiving texts.")
                        .build();
                Message sms = new Message
                        .Builder()
                        .body(body)
                        .build();
                MessagingResponse twiml = new MessagingResponse
                        .Builder()
                        .message(sms)
                        .build();
                return twiml.toXml();
            }

            /**
             * if the app receives a text 'stop', it makes sure the number is one of the app users
             * then removes them from the array.
             *
             * it sends them a confirmation text back that they have been removed from the group.
             */

            if (req.queryParams("Body").toLowerCase().equals("no")) {

                if (phoneNumbers.contains(req.queryParams("From"))) {
                    return true;
                }

                for (String number : phoneNumbers) {
                    if (number.equals(req.queryParams("From"))) {
                        phoneNumbers.remove(number);
                    }
                }

                Body body = new Body
                        .Builder("You will no longer receive these messages. Text 'enroll' anytime to start back up.")
                        .build();
                Message sms = new Message
                        .Builder()
                        .body(body)
                        .build();
                MessagingResponse twiml = new MessagingResponse
                        .Builder()
                        .message(sms)
                        .build();
                return twiml.toXml();
            }

            return req.queryParams("Body");

        });
    }
}
