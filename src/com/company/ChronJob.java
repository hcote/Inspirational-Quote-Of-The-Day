package com.company;

import java.util.TimerTask;

import static com.company.DataRequest.parseJsonForWord;
import static com.company.SmsSender.sendText;

public class ChronJob extends TimerTask {

            @Override
            public void run() {
                try {

//                    System.out.println("hiiii");
                    // makes API call returns json.toString() response
                    String json = DataRequest.getWordObject();

                    //parses response & returns the word, definition and sentence
                    // which will compose the text message the end user receives
                    String text = parseJsonForWord(json);

                    // sends text with the word, definition and sentence
                    sendText(text);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

}
