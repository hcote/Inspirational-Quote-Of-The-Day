## Overview
This app integrates Twilio text messaging services and Wordnik API to send you a text every morning with a new vocabulary "word of the day".

## Technologies Used
1. Java
2. Twilio
3. Ngrok

## How It Works

### Sending texts
In Main.class there is a scheduled method to run at 11am each morning, which calls ChronJob.class. ChronJob's three methods are the core of the application.
The first is the API call to Wordnik which is a simple HTTP request for data and returns a json object.
The second method parses the json data and extracts the word, definition and example sentence.
The third method is what sends the text message.

The third method makes use of Twilio's technology and builds a message of whatever parameter is passed through (the extracted word, definition and sentence mentioned above). It loops through an ArrayList of phone numbers and sends that text to each one (at 11am). 

### Receiving texts
This application is also capable of receiving texts (SmsApp.class) and is where ngrok comes in. I start a localhost server with the SmsApp.main() method. Next, with the command $ ./ ngrok 4567 (the port my localhost server is running on) I am given a unique nkgro.io URL where traffic to that URL is automatically forwarded to my local server. 

I instruct Twilio to send any POST requests (received text messages) to my ngrok URL which then get forwarded to my localhost server which then get handled however I specify in SmsApp.class. In that file, for POST requests, if a text comes in that says "enroll" then the user's phone number is added to the ArrayList and will start receiving daily texts and when they text "stop" they will be removed from the ArrayList and no longer receive texts. The image below taken from Twilio explains the process a POST request goes through. 

<img width="654" alt="screen shot 2019-02-07 at 6 16 08 pm" src="https://user-images.githubusercontent.com/34493689/52455893-29596280-2b07-11e9-87ee-43a6ceff3ca6.png">
* when Twilio receives a request and sends an HTTP request to my server, this is called a webhook
