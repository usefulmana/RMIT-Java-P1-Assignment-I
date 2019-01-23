package rmit.p1.Ticket;

import java.util.*;

public class Lottery extends TicketGenerator {

    /**This class contains the necessary methods for the ticket menu*/
    private static Lottery lottery; // Create a default Lottery class

    private Lottery(){} // private Constructor to prevent addition obj creation

    public static Lottery getInstance() // obtain the current instance of this object
    {
        if(lottery ==  null)
        {
            lottery = new Lottery();
        }
        return lottery;
    }


    public void printDrawCount(){
        /** Print the number of draw until jackpot*/
        int[] winningTicket = generateTicket(); // create a winning ticket
        Arrays.sort(winningTicket); // sort it in ascending order
        System.out.print("Winning ticket numbers: "); // display the winning ticket
        for (int i = 0; i < winningTicket.length; i++) {
            System.out.print(winningTicket[i] + " ");
        }
        System.out.println();
        int totalDraw = 1; // initialize the draw count

        int[] drawnTicket = generateTicket(); // generate the first ticket
        Arrays.sort(drawnTicket); // sort the ticket numbers in ascending order
        while(!Arrays.equals(winningTicket,drawnTicket)) // loop here
        {
            drawnTicket =  generateTicket(); // generate another ticket
            Arrays.sort(drawnTicket); // sort
            totalDraw++; // increase count
            System.out.print(totalDraw + " "); // print count
        }
        System.out.println("Jackpot!"); // say jackpot when loop ends
    }

    public int drawCount()
    {
        /** Extract to total draw numbers similar to method above but it return a value instead of printing*/
        int[] winningTicket = generateTicket();
        Arrays.sort(winningTicket);
        int totalDraw = 0;

        int[] drawnTicket = generateTicket();
        Arrays.sort(drawnTicket);
        while(!Arrays.equals(winningTicket,drawnTicket))
        {
            drawnTicket =  generateTicket();
            Arrays.sort(drawnTicket);
            totalDraw++;
        }
        return totalDraw;
    }

    public void printTicket()
    {
        System.out.println(toPrint()); // print the ticket
    }

    public void buyTicketMenu()
    {
        /***/
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            /** Ticket menu*/
            try{
                System.out.print("**************************\n" +
                    "Please choose an option: \n" +
                    "1. Buy normal ticket\n" +
                    "2. Buy special ticket\n" +
                    "3. Return to ticket menu\n" +
                    "4. Quit program\n" +
                    "**************************\n" +
                    "Your choice: ");
                int choice = scanner.nextInt();
                if (choice == 1)
                {
                    printTicket();
                    System.out.println("Your ticket costs " + getCost());
                    Thread.sleep(2000);
                }
                else if (choice == 2)
                {
                    Ticket ticket = new TicketDecorator(new TicketGenerator());
                    System.out.println(ticket.toPrint());
                    System.out.println("Your ticket costs " + ticket.getCost());
                    Thread.sleep(2000);
                }
                else if (choice == 3)
                {
                    break;
                }
                else if (choice == 4)
                {
                    System.out.println("Program exits. Have a good day!");
                    System.exit(0);
                }
                else
                {
                    System.out.println("No such option exits. Have a good day!");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Please try again!");
            }
            catch (InterruptedException a)
            {
                a.printStackTrace();
            }
        }
    }

}
