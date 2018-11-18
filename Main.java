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
            System.out.print("***********MAIN MENU***************\n" +
                    "Please choose an option: \n" +
                    "1. Add/Edit/Delete/View the customer list \n" +
                    "2. Add/Edit/Delete/View the Vietlot shop list \n" +
                    "3. Draw 5 times and display plays the average chance to win \n" +
                    "4. Exit \n" +
                    "**************************\n" + "Your choice:");

            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("**************************\n" +
                            "Please choose an option: \n" +
                            "1. Add customer info\n" +
                            "2. Edit customer info\n" +
                            "3. Delete customer info\n" +
                            "4. View customer info\n +" +
                            "**************************\n" +
                            "Your choice: ");
                    Scanner inCase1 = new Scanner(System.in);
                    int choiceCase1 = inCase1.nextInt();
                    switch (choiceCase1)
                    {
                        case 1: addCustomerInfo("customerInfo.csv");break;
                        case 2: ;break;
                        case 3: ;break;
                        case 4: viewInfo("customerInfo.csv");break;
                    }
                    break;

                case 2:
                    System.out.print("**************************\n" +
                            "Please choose an option: \n" +
                            "1. Add shop info\n" +
                            "2. Edit shop info\n" +
                            "3. Delete shop info\n" +
                            "4. View shop info\n +" +
                            "**************************\n" +
                            "Your choice: ");
                    Scanner inCase2 = new Scanner(System.in);
                    int choiceCase2 = inCase2.nextInt();
                    switch (choiceCase2)
                    {
                        case 1: addShopInfo("shopInfo.csv");break;
                        case 2: ;break;
                        case 3: ;break;
                        case 4: viewInfo("shopInfo.csv");break;
                    }
                    break;
                case 3:
                    drawTickets();
                    System.out.println("*******\n Enter: \n 1 to quit \n Any other integer number to return to main menu\n *******");
                    System.out.print("Your choice: ");
                    boolean passConditionCase3 = false;
                    while (!passConditionCase3) {
                        try // catching input error

                        {   // new Scanner object to ask the user to input gain after error
                            Scanner inCase3 = new Scanner(System.in);
                            int choiceNo3 = inCase3.nextInt();
                            choiceNo2 = choiceNo3;
                            passConditionCase3 = true;
                        }
                        catch (InputMismatchException a)
                        {
                            System.out.println("Invalid Input! Try again. \n**********\n Enter: \n 1 to quit \n Any other integer number to return to main menu \n**********");
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
                    System.out.println("Invalid input! Try again. \n**********\n Enter: \n 1 to quit \n Any other integer number to return to main menu \n**********");
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
                            System.out.print("Invalid Input! Try again.\n**********\n Enter:\n" + "***1 to quit \n" + "***Any integer number to return to main menu.\n**********");
                            System.out.print("Your choice: ");
                        }
                    }

            }
        }

    }
    public static void drawTickets()
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
    public static void addCustomerInfo(String customerFile)
    {
        ArrayList<ArrayList<String>> bigCustomerList = new ArrayList<ArrayList<String>>();
        int endLoopCase1 = 0;
        while (endLoopCase1 != 1)
        {
            Scanner inCase1 = new Scanner(System.in);
            System.out.print("Name");
            String name = inCase1.nextLine();
            System.out.print("Birthday");
            String birthday = inCase1.nextLine();
            System.out.print("Address");
            String address = inCase1.nextLine();
            System.out.print("Phone");
            String phone = inCase1.nextLine();
            System.out.print("email");
            String email = inCase1.nextLine();
            String[] customerInfo = {name, birthday, address, phone, email};
            writeInfo(customerFile, customerInfo);

            Arrays.fill(customerInfo,null);
            Scanner inCase1_2 = new Scanner(System.in);
            System.out.print("Press any integer number to continue adding, 1 to return to the main menu\n" + "Your choice:");
            int choiceCase1 = inCase1_2.nextInt();
            endLoopCase1 = choiceCase1;
        }
    }
    public static void addShopInfo(String shopFile)
    {
        ArrayList<ArrayList<String>> bigCustomerList = new ArrayList<ArrayList<String>>();
        int endLoopCase2 = 0;
        while (endLoopCase2 != 1)
        {
            Scanner inCase2 = new Scanner(System.in);
            System.out.print("Shop's Code: ");
            String shopCode = inCase2.nextLine();
            System.out.print("Shop's Address: ");
            String shopAddress = inCase2.nextLine();
            System.out.print("Owner's Name: ");
            String shopOwnerName = inCase2.nextLine();
            System.out.print("Shop's Phone: ");
            String shopPhone = inCase2.nextLine();
            System.out.print("Shop's Email: ");
            String shopEmail = inCase2.nextLine();
            System.out.print("Shop's Account Balance (inVND): ");
            String shopBalance = inCase2.nextLine();
            String[] shopInfo = {shopCode, shopAddress, shopOwnerName, shopPhone, shopEmail, shopBalance};
            writeInfo(shopFile, shopInfo);
            Arrays.fill(shopInfo,null);
            endLoopCase2 = 1;
        }
    }
    public static void viewInfo(String customerOrShopFiles)
    {
        // The name of the file to open.
        String fileName = customerOrShopFiles;

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
    public static void writeInfo(String customerOrShopFiles, String[] stringArray)
    {

        // The name of the file to open.
        String fileName = customerOrShopFiles;

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName, true);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);
            String formatStr = "%-22s";
            // Note that write() does not automatically
            // append a newline character.
            for (int i = 0; i < stringArray.length; i++) {
                bufferedWriter.write(String.format(formatStr,stringArray[i]));
            }

            bufferedWriter.write("\n");
            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
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