package com.company;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.ArrayList;

import static com.company.hiddeninfo.Private.*;

public class SmsSender {

    static ArrayList<String> phoneNumbers = new ArrayList<>();

    /**
     * this static{} method allows us to add the numbers to the array
     * list without creating and calling a standard method to do so for us
     */

    static {
//        phoneNumbers.add(momsNum);
//        phoneNumbers.add(dadsNum);
//        phoneNumbers.add(tylersNum);
//        phoneNumbers.add(stephsNum);
//        phoneNumbers.add(parkersNum);
//        phoneNumbers.add(jsNum);
//        phoneNumbers.add(hollynsNum);
        phoneNumbers.add(myNum);

//        phoneNumbers.add(mrsverma);
//        phoneNumbers.add(kyle);
//        phoneNumbers.add(boylivo);
//        phoneNumbers.add(mk);
    }


    public static final String ACCOUNT_SID = accountSid;
    public static final String AUTH_TOKEN = authToken;

    public static void sendText(String textBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        for(String number : phoneNumbers) {
            Message message = Message
                    .creator(new PhoneNumber(number), // to
                            new PhoneNumber(twilioNum), // from
                            textBody)
                    .create();
        }

        System.out.println("Message sent to " + phoneNumbers);
    }

}
