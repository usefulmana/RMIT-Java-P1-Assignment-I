/*
 * RMIT University Vietnam - Saigon South Campus
 * Course: COSC2081 - Programming I
 * Semester: 2018C
 * Assignment: I
 * Author: Nguyen Le Bao Anh (s3616128)
 * Created on: 11/10/2018
 * Last updated on: 15/10/2018
 * Have not included the print statement in LotteryDraw() for sake of testing speed.
 */

package rmit.p1;
import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        // Main Menu
        int choiceNo2 = 0;
        //  Program's stop condition
        while (choiceNo2 != 1)
        {
            System.out.println("**************************\n" +
                    "Please choose an option: \n" +
                    "1. Add/Edit/Delete/View the customer list \n" +
                    "2. Add/Edit/Delete/View the Vietlot shop list \n" +
                    "3. Draws and display plays the average chance to win \n" +
                    "4. Exit \n" +
                    "**************************");

            Scanner in = new Scanner(System.in);
            System.out.print("Your choice: ");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    ;
                case 2:
                    ;
                case 3:
                    drawLottery();
                    System.out.println("Enter any integer number to return to main menu, 1 to quit");
                    System.out.print("Your choice: ");
                    boolean passCondition2 = false;
                    while (!passCondition2) {
                        try // catching input error

                        {   // new Scanner object to ask the user to input gain after error
                            Scanner in2 = new Scanner(System.in);
                            int choiceNo3 = in2.nextInt();
                            choiceNo2 = choiceNo3;
                            passCondition2 = true;
                        }
                        catch (InputMismatchException a)
                        {
                            System.out.print("Invalid Input! Try again \n Enter:\n" + "***1 to quit \n" + "***Any integer number to return to main menu.\n");
                            System.out.print("Your choice: ");
                        }
                    }
                    break;
                case 4:
                    System.out.print("Program exits. Have a good day!");
                    System.exit(0);
                    break;
                default:
                    boolean passCondition3 = false;
                    System.out.println("Invalid input! Try again.");
                    System.out.print("Your choice: ");
                    while (!passCondition3)
                    {
                        try{

                            Scanner in3 = new Scanner(System.in);
                            int choiceNo4 = in3.nextInt();
                            choiceNo2 = choiceNo4;
                            passCondition3 = true;
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.print("Invalid Input! Try again.\n Enter:\n" + "***1 to quit \n" + "***Any integer number to return to main menu.\n");
                            System.out.print("Your choice: ");
                        }
                    }

            }
        }

    }
    public static void drawLottery()
    {
        int totalCount = 0;
        int numberOfTests = 5; // Number of tests run
        for (int i = 0; i < numberOfTests; i++)
        {   // Using a ticket number generator class to generate the winning ticket

            int[] winningNumbers = new int[6];
            TicketNumberGenerator winningTicket = new TicketNumberGenerator(winningNumbers);
            winningTicket.generateNumbers();

            // Sorting the winning numbers in ascending order in order to help with comparison
            Arrays.sort(winningNumbers);

            // Using a ticket number generator class again to generate a ticket

            int[] drawnNumbers = new int[6];
            TicketNumberGenerator drawnTicket = new TicketNumberGenerator(drawnNumbers);
            drawnTicket.generateNumbers(); // First Draw
            Arrays.sort(drawnNumbers); // Same as above

            int count = 0;

            // Comparing the drawn numbers and the winning numbers
            while (!Arrays.equals(winningNumbers,drawnNumbers))
            {
                drawnTicket.generateNumbers(); // Second Draw
                Arrays.sort(drawnNumbers); // Sort again
                count++;

            }
            totalCount += count;
        }

        System.out.println(" ");

        System.out.println("Average number of times one needs to buys in order to win: " + totalCount/numberOfTests);
    }
}

class TicketNumberGenerator
{
    private int[] ticketNumbers = new int[6];

    public TicketNumberGenerator(int[] ticketNumbers)
    {
        this.ticketNumbers = ticketNumbers;
    }
    public void generateNumbers ()
    {
        for (int i = 0; i < ticketNumbers.length; i++)
        {
            ticketNumbers[i] = (int) (Math.random() * (45 + 1));
        }
    }
}

