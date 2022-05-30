package com.company;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BufferedWriter out;
        String name;
        Scanner in = new Scanner(System.in);
        

        System.out.print("❤ ♦ ♠ ♣ Welcome to my magic trick! ❤ ♦ ♠ ♣\n");
        System.out.print("Please enter your name:\n");
        name = in.nextLine();

        try {
            File myObj = new File(name + ".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("Warning: File already exists, changes will be made to the file!");
            }

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(myObj, true)));
            FileWriter myWriter = new FileWriter(myObj);

            boolean cond = false;
            String answer;
            String userCard;
            int counter = 0;


            System.out.println("Please select a random card from the list below: \n");

            //shuffle 52 cards then select 21 cards
            Deck deck = new Deck();
            deck.ShuffleCards();
            myWriter.write("\nThe original 21 cards: \n");
            deck.selectTwentyOne(myWriter);

            while (!cond) {
                System.out.print("Have you selected your card (y/n): ");
                answer = in.next();
                cond = answer.equalsIgnoreCase("Y");
            }
            while (counter < 3) {
                deck.TwentyOneStack();
                deck.TwentyOneDisplay(myWriter);
                System.out.print("\nWhere does your card appear (right, middle, left): ");
                answer = in.next();
                myWriter.write("\n ------------------------------ \n");
                myWriter.write("Player's choice: "+ answer + "\n");
                deck.MagicTrick(answer);
                counter++;

            }

            userCard = deck.finalCard();
            System.out.println("\nYour card is  (" + userCard + ") \n");
            myWriter.write("\n ------------------------------ \n");
            myWriter.write("❤ ♦ ♠ ♣ Result: "+ userCard + " ❤ ♦ ♠ ♣ \n");
            System.out.println("❤ ♦ ♠ ♣ Thank you for playing! ❤ ♦ ♠ ♣");

            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
