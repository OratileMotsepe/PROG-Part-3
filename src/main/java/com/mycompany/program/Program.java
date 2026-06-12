/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.program;

import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author Student
 */
public class Program {

        public static void main(String[] args) { 

        Scanner input = new Scanner(System.in); 

        

        System.out.println("Welcome to QuickChat");

        System.out.println("\nSelect one of the following options to get started: ");

        System.out.println("1) Register and Login");

        System.out.println("2) View Stored Messages");

        System.out.println("3) Exit");

        

        System.out.print("\nEnter option: ");

        int selectedOption = input.nextInt();

        input.nextLine();

        

        if (selectedOption == 1) {

            

            System.out.print("Create new account");

        

            System.out.print("\n");

        

            System.out.print("\nEnter First name: ");

            String firstName = input.nextLine();

        

            System.out.print("Enter Last name: "); 

            String lastName = input.nextLine();

        

            System.out.print("Enter Username: "); 

            String username = input.nextLine();

        

            System.out.print("Enter Password: "); 

            String password = input.nextLine();

        

            System.out.print("Enter a valid South African phone number: "); 

            String phoneNumber = input.nextLine();   

 

            User user = new User(firstName, lastName, username, password, phoneNumber); 

        

            System.out.println("\nAccount status:");

        

            String registrationMessage = Login.registerUser(username, password);

            System.out.println(registrationMessage);

        

            boolean validUsername = Login.checkUserName(user.username); 

            boolean validPassword = Login.checkPasswordComplexity(user.password); 

            boolean validPhone = Login.checkCellPhoneNumber(user.phoneNumber);

        

            System.out.println("------------------------------");

        

            if (!validUsername || !validPassword || !validPhone) {

                System.out.println("Registration was not successful.");

                return;

            } else {

                System.out.println("Registration was successful.");

            }

        

            System.out.println("\nLogin to Account ");

        

            System.out.print("\nEnter Username: "); 

            String loginUsername = input.nextLine(); 

        

            System.out.print("Enter Password: "); 

            String loginPassword = input.nextLine(); 

        

            String accountMessage = Login.returnLoginStatus(loginUsername, loginPassword, user, user.firstName, user.lastName);

            System.out.println("------------------------------");

            System.out.println(accountMessage);

        

        //POE Part 2

        

            if (Login.loginUser(loginUsername, loginPassword, user)) {

                String validChatRunner = Message.createChat(input, user);

                System.out.println(validChatRunner);

            

            } else {

                System.out.println("Login failed");

            

            }

            

        } else if (selectedOption == 2) {

            

            Message.loadStoredMessages();

            

            Message.storedMessagesOption(input);

            

        } else if (selectedOption == 3) {

            

            System.out.println("Program ended");

            

        } else {

            

            System.out.println("Invalid option. Please try again");

            

        }

        

        

        

        

        input.close();   

     

    }   

}  



class User { String firstName; String lastName; String username; String password; String phoneNumber;

    public User(String firstName, String lastName, String username, String password, String phoneNumber) { 

        

        this.firstName = firstName; 

        this.lastName = lastName; 

        this.username = username; 

        this.password = password; 

        this.phoneNumber = phoneNumber; 

 

    }   

}  



class Login { 

    public static boolean checkUserName(String username) { 

        if (!username.contains("_")) { 

            return false; 

        } else if (username.length() > 5) { 

            return false; 

        } else { 

            return true; 

    } 

}   

 

public static boolean checkPasswordComplexity(String password) { 

    if (!password.matches(".*[A-Z].*")) { 

        return false; 

    } else if (password.length() < 8) {   

        return false; 

    } else if (!password.matches(".*\\d.*")) {   

        return false; 

    } else if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {   

        return false; 

    } else {   

        return true; 

    }   

}   

 

public static boolean checkCellPhoneNumber(String phoneNumber) {   

    if (!phoneNumber.matches("^\\+27\\d{9}$")) {   

        System.out.println("Cell phone number incorrectly formatted or does not contain international code"); 

        return false; 

    } else {   

        System.out.println("Cell phone number successfully captured");

        return true; 

    }   

}  

 

public static String registerUser(String username, String password) { 

    if (!checkUserName(username)) {

        return "Username is not correctly formatted; please ensure your username contains an underscore and is no more than five characters in length.";

    } else if (!checkPasswordComplexity(password)) {

        return "Password is not correctly formatted; please ensure your password contains at least eight characters, a capital letter, a number, and a special character.";

    } else {

        return "Username and password successfully captured.";

    }

} 



public static boolean loginUser(String loginUsername, String loginPassword, User user) {

    if (loginUsername.equals(user.username) && loginPassword.equals(user.password)) { 

        return true; 

    } else {  

        return false;  

    }  

} 

 

public static String returnLoginStatus(String loginUsername, String loginPassword, User user, String firstName, String lastName) { 

    if (loginUser(loginUsername, loginPassword, user)) { 

        return "Welcome" + " " + user.firstName + " " + user.lastName + ", it is great to see you."; 

    } else {  

        return "Username or password incorrect, please try again.";

    } 

}



}



class Message {

    

    //Arrays and array counters - POE Part 3

    

    static String[] sentMessage;

    static String[] disregardedMessages;

    static String[] storedMessages;

    static String[] messageHash;

    static long[] messageID;

    

    static String[] recipients;

    static String[] sender;

    

    

    static int sentCount = 0;

    static int storedCount = 0;

    static int disregardedCount = 0;

    

    //method that creates new chat

    

    public static String createChat(Scanner input, User user) {

        

        int numberOfMessages;

        String message = "";

        String recipientCell;

        String result = "";

        

        //User must select an option to start chatting

        //Stored message option added - POE Part 3

        

        System.out.println("Welcome to QuickChat");

        System.out.println("\nSelect one of the following options: ");

        System.out.println("1) Send Messages");

        System.out.println("2) Show recently sent messages");

        System.out.println("3) Quit");

        System.out.println("4) Show stored messages ");

        

        System.out.print("\nEnter option: ");

        int option = input.nextInt();

        

        input.nextLine();

        

        int numberMessagesSent = 0;

    

        if (option == 1) {

            System.out.print("Enter the amount of messages you would like to send: ");

            numberOfMessages = input.nextInt();

            input.nextLine();

            

            //Arrays creation using numberOfMessages - POE Part 3

            

            sentMessage = new String[numberOfMessages];

            disregardedMessages = new String[numberOfMessages];

            storedMessages = new String[numberOfMessages];

            messageHash = new String[numberOfMessages];

            messageID = new long[numberOfMessages];

            

            recipients = new String[numberOfMessages];

            sender = new String[numberOfMessages];

            

            

            System.out.print("Enter recipient cell number: ");

            recipientCell = input.nextLine();

                

            String validRecipientCell = checkRecipientCell(recipientCell);

            

            if (validRecipientCell.equals("Recipient cell number incorrectly formatted or does not contain international code")) {

                

                System.out.println(validRecipientCell);

                

                return "\nPlease run program again";

                

            } else {

                System.out.println(validRecipientCell);

            }

            

            for (int i = 1; i <= numberOfMessages; i++) {

                

                long generatedMessageID = generateMessageID();

                

                System.out.print("\nEnter message: ");

                message = input.nextLine();

                

                if (message.isEmpty()) {

                    System.out.println("Message cannot be empty");

                    i--;

                    continue;

                }

                

                //Length of message

                if (message.length() > 250) {

                    System.out.println("Please enter a message of less than 250 characters in length");

                    i--;

                    continue;

                }

                

                // Message hash

                String newMessageHash = createMessageHash(generatedMessageID, message);

                

                //Storage

                String sentOption = sentMessage(input);

                System.out.println(sentOption);

                

                //Display message and message contents

                if (sentOption.equals("\nMessage successfully sent")) {

                    numberMessagesSent++;

                    

                    

                    System.out.println(printMessages(generatedMessageID, newMessageHash, recipientCell, message));

                    

                    sentMessage[sentCount] = message;

                    messageHash[sentCount] = newMessageHash;

                    messageID[sentCount] = generatedMessageID;

            

                    recipients[sentCount] = recipientCell;

                    sender[sentCount] = user.phoneNumber;

                    

                    sentCount++;

                    

                    result = "Message created";

                

                } else if (sentOption.equals("\nMessage deleted")) {

                    

                    disregardedMessages[disregardedCount] = message;

                    

                    disregardedCount++;

                    

                    result = "Message deleted"; 

                

                } else if (sentOption.equals("\nMessage successfully stored")) {

                    

                    storedMessages[storedCount] = message;

                    messageHash[storedCount] = newMessageHash;

                    messageID[storedCount] = generatedMessageID;

            

                    recipients[storedCount] = recipientCell;

                    sender[storedCount] = user.phoneNumber;

                    

                    storedCount++;

                    

                    storeMessage(generatedMessageID, newMessageHash, recipientCell, message);

                }

                

            }

            

            System.out.println("\nTotal messages sent: " + returnTotalMessages(numberMessagesSent));

            

        } else if (option == 2) {

            result = "\nFeature is coming soon";

            

        } else if (option == 3) {

            result = "\nProgram has ended";

            

        } else if (option == 4){

            

            loadStoredMessages();

            

            storedMessagesOption(input);

            

        } else {

            result = "\nInvalid option, please try again";

        }

        

        return result;

        

    }

    

    //method that generates unique MessageID

    

    public static long generateMessageID() {

        

        Random random = new Random();

        

        long id = 1000000000L + (long)(random.nextDouble() * 9000000000L);

        return id;

    }

    

    //method that checks recipient cell number

    

    public static String checkRecipientCell(String recipientCell) {

        

        if (!recipientCell.matches("^\\+27\\d{9}$")) {

            return "Recipient cell number incorrectly formatted or does not contain international code";

        } else {

            return "Recipient cell number successfully captured";

        }

    }

    

    //method that checks message Hash

    

    public static String createMessageHash(long uniqueMessageID,  String message) {

        

        String idString = String.valueOf(uniqueMessageID);

        String twoNumbers = idString.substring(0, 2);

        String[] words = message.trim().split("\\s+");

        

        if (words.length == 0) {

            return "Invalid";

        }

        

        String firstWord = words[0];

        String lastWord = words[words.length - 1];

        

        String hash = twoNumbers + ":" + firstWord + lastWord;

        

        return hash.toUpperCase();

    }

    

    //method that sends message

    

    public static String sentMessage(Scanner input) {

        

        String store;

        

        //User must select an option

        System.out.println("\nSelect one of the following options: ");

        System.out.println("1) Send Message");

        System.out.println("2) Discard message");

        System.out.println("3) Store message to send later");

        

        System.out.print("\nEnter option: ");

        int createdMessage = input.nextInt();

        input.nextLine();

        

        if (createdMessage == 1) {

            store = "\nMessage successfully sent";

            

        } else if (createdMessage == 2) {

           System.out.print("\nEnter 0 to delete message: ");

           

           int deleteMessage = input.nextInt();

           input.nextLine();

           

           if (deleteMessage == 0) {

               store = "\nMessage deleted";

           } else {

               store = "\nInvalid value";

           }

            

        } else if (createdMessage == 3) {

            store = "\nMessage successfully stored"; 

            

        } else {

            store = "\nInvalid option, please try again";

        }

        

        return store;

    }

    

    //method that prints message details

    

    public static String printMessages(long messageID, String newMessageHash, String recipientCell, String message) {

        

        return "\nMessage ID: " + messageID 

                + "\nMessage Hash: " + newMessageHash 

                + "\nRecipient cellphone: " + recipientCell

                + "\nMessage: " + message;

        

    }

    

    public static int returnTotalMessages(int numberMessagesSent) {

        

        return numberMessagesSent;

    }

    

    public static void storeMessage(long messageID, String messageHash, String recipient, String message) {

        

        try {

            FileWriter writer = new FileWriter("storedMessages.json",true);

            

            writer.write("{\n" +

                "\"MessageID\":\"" + messageID + "\",\n" +

                "\"MessageHash\":\"" + messageHash + "\",\n" +

                "\"Recipient\":\"" + recipient + "\",\n" +

                "\"Message\":\"" + message + "\"\n" +

                "}\n\n"

                );

                writer.close();

                

                System.out.println("Message stored successfully.");

                

        } catch (IOException e) {

            System.out.println( "Error storing message.");

            e.printStackTrace();

            

        }

        

    }

    

    //POE Part 3 methods

    

    //Method that displays the sender and recipient of all stored messages

    public static void displaystoredMessages() {

        

        for (int i = 0; i < storedCount; i++) {

            

            if (storedMessages[i] != null) {

                

                System.out.println("Sender: " + sender[i]);

                System.out.println("Recipient: " + recipients[i]);

                System.out.println("Message: " + storedMessages[i]);

                

            }

        }

        

    }

    

    //Method that dispalys the longest stored message

    public static void longestStoredMessage() {

        

        String longest = "";

        

        for (int i = 0; i < storedCount; i++) {

            if (storedMessages[i] != null && storedMessages[i].length() > longest.length()) {

                

                longest = storedMessages[i];

                

            }

        }

        System.out.println("Longest Message: " + longest);  

    }

    

    //Method that searches for a message ID and displays the corresponding recipient and message

    public static void searchMessageID(long searchID) {

        

        for (int i = 0; i < storedCount; i++) {

            if (messageID[i] == searchID) {

                

                System.out.println("\n");

                System.out.println("Recipient: " + recipients[i]);

                System.out.println("Message: " + storedMessages[i]);

                

                return;

                

            }

               

        }

        System.out.println("Message was not found. Enter a valid message ID");

    }

    

    //Method that searches for all messages stored for a particular recipient

    public static void searchRecipient(String recipientCell) {

        

        boolean found = false;

        

        for (int i = 0; i < storedCount; i++) {

            if (recipients[i] != null && recipients[i].equals(recipientCell)) {

                

                found = true;

                

                System.out.println("Recipient: " + recipients[i]);

                System.out.println("Message: " + storedMessages[i]);

                

            }

        }

        

        if (!found) {

            

            System.out.println("No Messages by recipient found");

            

        }

        

    }

    

    //Method that deletes a message using the message hash

    public static void deleteUsingHash(String hash) {

        

        for (int i = 0; i < storedCount; i++) {

            if (messageHash[i] != null && messageHash[i].equals(hash)) {

                storedMessages[i] = null;

                recipients[i] = null;

                messageHash[i] = null;

                messageID[i] = 0;

                

                System.out.println("Message has been deleted");

                

                return;

            }

        }

        

        System.out.println("Hash was not found. Enter a valid message hash");

        

    }

    

    //Method that displays a report with the full details of all the stored messages

    public static void fullDetailReport() {

        

        System.out.println("--------Stored message report--------");

        

        for (int i = 0; i < storedCount; i++) {

            if (storedMessages[i] != null) {

                System.out.println("\n");

                

                System.out.println("Sender: " + sender[i]);

                

                System.out.println("Recipient: " + recipients[i]);

                

                System.out.println("Message: " + storedMessages[i]);

                

                System.out.println("Message ID: " + messageID[i]);

                

                System.out.println("Message hash: " + messageHash[i]);

            }

        }

        

    }

    

    public static void loadStoredMessages() {

        

        if (storedMessages == null) {

            

            storedMessages = new String[100];

            recipients = new String[100];

            messageHash = new String[100];

            messageID = new long[100];

            sender = new String[100];

            

        }

        

        try {

            BufferedReader reader = new BufferedReader(new FileReader("storedMessages.json"));

            

            String storing;

            

            storedCount = 0;

            

            while((storing = reader.readLine()) !=null) {

                if (storing.trim().equals("{")) {

                    

                    String idLine = reader.readLine();

                    String hashLine = reader.readLine();

                    String recipientLine = reader.readLine();

                    String messageLine = reader.readLine();

                    

                    reader.readLine();

                    

                    String id = idLine.split(":\"")[1].replace("\",", "");

                    String hash = hashLine.split(":\"")[1].replace("\",", "");

                    String recipient = recipientLine.split(":\"")[1].replace("\",", "");

                    String message = messageLine.split(":\"")[1].replace("\",", "");

                    

                    messageID[storedCount] = Long.parseLong(id);

                    messageHash[storedCount] = hash;

                    recipients[storedCount] = recipient;

                    storedMessages[storedCount] = message;

                    sender[storedCount] = "Stored User";

                    

                    storedCount++;

   

                }

            }

            

            reader.close();

        } catch(IOException e) {

            

            System.out.println("No stored messages found");

        }

        

    }

    

    //Method that calls all the methods for the fourth menu option for stored messages

    public static void storedMessagesOption(Scanner input) {

        

        if (storedMessages == null) {

            

            System.out.println("No stored messages available");

            

            return;

            

        }

        

        System.out.println("\nSelect one of the following options: ");

        System.out.println("1) Display the sender and recipient of all stored messages");

        System.out.println("2) Display the longest stored message");

        System.out.println("3) Search for recipient and message using message ID");

        System.out.println("4) Search for all messages for a particular recipient");

        System.out.println("5) Delete message using a message hash");

        System.out.println("6) Display a report with the full details of all stored messages");

        

        System.out.print("\nEnter option: ");

        int userOption = input.nextInt();

        input.nextLine();

        

        if (userOption == 1) {

            displaystoredMessages();

            

        } else if (userOption == 2) {

           longestStoredMessage();

            

        } else if (userOption == 3) {

            

            System.out.print("Enter message ID: ");

            long inputMessageID = input.nextLong();

            input.nextLine();

            

            searchMessageID(inputMessageID);

            

        } else if (userOption == 4) {

            

            System.out.print("Enter recipient: ");

            String recipient = input.nextLine();

            

            searchRecipient(recipient);

        

        } else if (userOption == 5) {

            

            System.out.print("Enter message hash: ");

            String hashh = input.nextLine();

            

            deleteUsingHash(hashh);

            

        } else if (userOption == 6) {

            fullDetailReport();

            

        } else {

            System.out.println("\nInvalid option, please try again");

            

        }

        

    }

    

    

    

    

}
    
