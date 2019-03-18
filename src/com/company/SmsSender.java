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
//        phoneNumbers.add(m);
//        phoneNumbers.add(d);
//        phoneNumbers.add(t);
//        phoneNumbers.add(s);
//        phoneNumbers.add(p);
//        phoneNumbers.add(j);
//        phoneNumbers.add(h);
        phoneNumbers.add(me);

//        phoneNumbers.add(mv);
//        phoneNumbers.add(k);
//        phoneNumbers.add(b);
//        phoneNumbers.add(mk);
//        phoneNumbers.add(mkm);
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
