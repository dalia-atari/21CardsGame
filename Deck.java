package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Deck extends Card{
    Random rand = new Random();
    static final int SIZE = 52;
    static final int DECKS = 21;
    static final int SINGLE = 7;

    String currentCard;
    LimStack<String> deckStack = new ArrayStack<>(SIZE);
    LimStack<String> holderStack = new ArrayStack<>(SIZE);

    LimStack<String> rStack = new ArrayStack<>(SIZE);
    LimStack<String> fStack = new ArrayStack<>(SIZE);

    LimStack<String> masterStack = new ArrayStack<>(DECKS);
    LimStack<String> mHolderStack = new ArrayStack<>(DECKS);

    LimStack<String> right = new ArrayStack<>(SINGLE);
    LimStack<String> middle = new ArrayStack<>(SINGLE);
    LimStack<String> left = new ArrayStack<>(SINGLE);
    LimStack<String> tempStack = new ArrayStack<>(SINGLE);

    public Deck()
    {
        int cardValue;
        int currHand;
        int boundary;

        for(int i = 3; i >= 0; i--)
        {
            currHand = 14;
            boundary = 2;
            while(currHand >= boundary)
            {
                suitValue = suit[i];
                cardValue = currHand;
                switch (cardValue) {
                    case 14 -> finalCardValue = "Ace";
                    case 13 -> finalCardValue = "King";
                    case 12 -> finalCardValue = "Queen";
                    case 11 -> finalCardValue = "Jack";
                    default -> finalCardValue = Integer.toString(cardValue);
                }
                completeCard = this.toString();
                deckStack.push(completeCard);
                currHand--;
            }
        }
    }

    public void DisplayCards()
    {
        while(!holderStack.isEmpty())
        {
            currentCard = holderStack.top();
            deckStack.push(currentCard);
            holderStack.pop();
        }
    }

    public void ShuffleCards()
    {
        int selector;
        for(int i = 0; i < 100; i++)
        {
            while(!deckStack.isEmpty())
            {
                currentCard = deckStack.top();
                rStack.push(currentCard);
                deckStack.pop();
            }
            while(!rStack.isEmpty())
            {
                selector = rand.nextInt(2) + 1;
                currentCard = rStack.top();
                if(selector == 2)
                {
                    deckStack.push(currentCard);
                }
                else
                {
                    fStack.push(currentCard);
                }
                rStack.pop();
            }
            while(!fStack.isEmpty())
            {
                currentCard = fStack.top();
                deckStack.push(currentCard);
                fStack.pop();
            }
        }
        this.DisplayCards();
    }

    public void selectTwentyOne(FileWriter myWriter) throws IOException
    {
        while(!masterStack.isFull())
        {
            currentCard = deckStack.top();
            masterStack.push(currentCard);
            deckStack.pop();
        }
        printSelected(myWriter);
    }

    public void printSelected(FileWriter myWriter) throws IOException
    {
        int i = 1;
        while(!masterStack.isEmpty())
        {
            currentCard = masterStack.top();
            mHolderStack.push(currentCard);
            masterStack.pop();
            System.out.println(i++ + ". " + currentCard);
            myWriter.write(currentCard);
            myWriter.write("\n");
        }
        while(!mHolderStack.isEmpty())
        {
            currentCard = mHolderStack.top();
            masterStack.push(currentCard);
            mHolderStack.pop();

        }
    }

    public void TwentyOneStack()
    {
        while(!masterStack.isEmpty())
        {
            currentCard = masterStack.top();
            right.push(currentCard);
            masterStack.pop();
            currentCard = masterStack.top();
            middle.push(currentCard);
            masterStack.pop();
            currentCard = masterStack.top();
            left.push(currentCard);
            masterStack.pop();
        }

    }

    public void TwentyOneDisplay(FileWriter myWriter) throws IOException
    {
        int index = 1;
        System.out.println("Right Stack: ");
        myWriter.write("\n ------------------------------ \n");
        myWriter.write("Right Stack: \n");

        while(!right.isEmpty())
        {
            currentCard = right.top();
            tempStack.push(currentCard);
            System.out.println(index + ". " + currentCard);
            right.pop();
            index++;
            myWriter.write(currentCard);
            myWriter.write("\n");
        }
        while(!tempStack.isEmpty())
        {
            currentCard = tempStack.top();
            right.push(currentCard);
            tempStack.pop();

        }

        index = 1;

        System.out.println("\nMiddle Stack: ");
        myWriter.write("\n ------------------------------ \n");
        myWriter.write("Middle Stack: \n");
        while(!middle.isEmpty())
        {
            currentCard = middle.top();
            tempStack.push(currentCard);
            System.out.println(index + ". " + currentCard);
            middle.pop();
            index++;
            myWriter.write(currentCard);
            myWriter.write("\n");
        }
        while(!tempStack.isEmpty())
        {
            currentCard = tempStack.top();
            middle.push(currentCard);
            tempStack.pop();

        }

        index = 1;

        System.out.println("\nLeft Stack: ");
        myWriter.write("\n ------------------------------ \n");
        myWriter.write("Left Stack: \n");
        while(!left.isEmpty())
        {
            currentCard = left.top();
            tempStack.push(currentCard);
            System.out.println(index + ". " + currentCard);
            left.pop();
            index++;
            myWriter.write(currentCard);
            myWriter.write("\n");
        }
        while(!tempStack.isEmpty())
        {
            currentCard = tempStack.top();
            left.push(currentCard);
            tempStack.pop();

        }
    }

    public void MagicTrick(String answer)
    // parameter is user's answer: right left or middle
    {
        switch (answer) {
            case "right" -> {
                while (!left.isEmpty()) {
                    currentCard = left.top();
                    tempStack.push(currentCard);
                    left.pop();
                }
                while (!tempStack.isEmpty()) {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }

                while (!right.isEmpty()) {
                    currentCard = right.top();
                    tempStack.push(currentCard);
                    right.pop();
                }
                while (!tempStack.isEmpty()) {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }

                while (!middle.isEmpty()) {
                    currentCard = middle.top();
                    tempStack.push(currentCard);
                    middle.pop();
                }
                while (!tempStack.isEmpty()) {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }
            }
            case "middle" -> {
                while (!left.isEmpty()) {
                    currentCard = left.top();
                    tempStack.push(currentCard);
                    left.pop();
                }
                while (!tempStack.isEmpty()) {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }

                while (!middle.isEmpty()) {
                    currentCard = middle.top();
                    tempStack.push(currentCard);
                    middle.pop();
                }
                while (!tempStack.isEmpty()) {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }

                while (!right.isEmpty()) {
                    currentCard = right.top();
                    tempStack.push(currentCard);
                    right.pop();
                }
                while (!tempStack.isEmpty()) {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }
            }
            case "left" -> {
                while (!middle.isEmpty()) {
                    currentCard = middle.top();
                    tempStack.push(currentCard);
                    middle.pop();
                }
                while (!tempStack.isEmpty()) {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }

                while (!left.isEmpty()) {
                    currentCard = left.top();
                    tempStack.push(currentCard);
                    left.pop();
                }
                while (!tempStack.isEmpty()) {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }

                while (!right.isEmpty()) {
                    currentCard = right.top();
                    tempStack.push(currentCard);
                    right.pop();
                }
                while (!tempStack.isEmpty()) {
                    currentCard = tempStack.top();
                    masterStack.push(currentCard);
                    tempStack.pop();
                }
            }
        }
    }

    public String finalCard()
    //final shift that show the 11th card
    {
        String finalCard = "";
        int limit = 11;
        for(int i = 0; i < limit; i++)
        {
            finalCard = masterStack.top();
            masterStack.pop();
        }
        return finalCard;
    }
}