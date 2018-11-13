/*
 * RMIT University Vietnam - Saigon South Campus
 * Course: COSC2081 - Programming I
 * Semester: 2018C
 * Assignment: I
 * Author: Nguyen Le Bao Anh (s3616128)
 * Created on: 11/10/2018
 * Last updated on: 13/10/2018

 */

package rmit.p1;

import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        LotteryDraw();
    }
    // Drawing the winning ticket and calculate the average winning chance
    public static void LotteryDraw()
    {
        int totalCount = 0;
        for (int i = 0; i < 5; i++)
        {
            // Using a ticket number generator class to generate the winning ticket
            int[] winningNumbers = new int[6];
            NumberGenerator winningTicket = new NumberGenerator(winningNumbers);
            winningTicket.generateNumbers();

            // Sorting the winning numbers in ascending order in order to help with comparison
            Arrays.sort(winningNumbers);

            // Using a ticket number generator class again to generate a ticket
            // Initializing the initial conditions for comparison
            int[] drawnNumbers = new int[6];
            NumberGenerator drawnTicket = new NumberGenerator(drawnNumbers);
            drawnTicket.generateNumbers();
            Arrays.sort(drawnNumbers); // Same as above

            int count = 0;

            // Comparing the ticket and the winning ticket
            while (!Arrays.equals(winningNumbers,drawnNumbers))
            {
                drawnTicket.generateNumbers(); // Generate a new ticket
                Arrays.sort(drawnNumbers); // Sort again
                count++;
                System.out.print(count + " ");
            }
            totalCount += count;
        }

        System.out.println(" ");

        System.out.println("Average number of times one needs to buys in order to win: " + totalCount/5);
    }
}

class NumberGenerator
{
    private int[] ticketNumbers;

    public NumberGenerator(int[] ticketNumbers)
    {
        this.ticketNumbers = ticketNumbers;
    }

    public int[] generateNumbers()
    {
        for (int i = 0; i < ticketNumbers.length; i++)
        {
            ticketNumbers[i] = (int) (Math.random() * (45 + 1));
        }
        return ticketNumbers;
    }
}