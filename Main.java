/*
 * RMIT University Vietnam - Saigon South Campus
 * Course: COSC2081 - Programming I
 * Semester: 2018C
 * Assignment: I
 * Author: Nguyen Le Bao Anh (s3616128)
 * Created on: 11/10/2018
 * Last updated on: 19/10/2018
 * Have not included the print statement in LotteryDraw() for sake of testing speed.
 */

package rmit.p1;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        // Main Menu
        String returnToMainMenu = "N";
        while(!returnToMainMenu.equals("Y"))
        {
            try
            {
                System.out.print("***********MAIN MENU***************\n" +
                        "Please choose an option: \n" +
                        "1. Add/Edit/Delete/View the customer list \n" +
                        "2. Add/Edit/Delete/View the Vietlot shop list \n" +
                        "3. Draw 5 times and display the average chance to win \n" +
                        "4. Exit \n" +
                        "**********************************\n" + "Your choice:");

                Scanner inMainMenu = new Scanner(System.in);
                int choiceMainMenu = inMainMenu.nextInt();

                switch (choiceMainMenu)
                {
                    case 1:
                        try {
                            System.out.print("**************************\n" +
                                    "Please choose an option: \n" +
                                    "1. Add customer info\n" +
                                    "2. Edit customer info\n" +
                                    "3. Delete customer info\n" +
                                    "4. View customer info\n " +
                                    "Enter any other key to return to main menu\n" +
                                    "**************************\n" +
                                    "Your choice: ");

                            Scanner inCase1 = new Scanner(System.in);
                            int case1Choice = inCase1.nextInt();
                            switch(case1Choice)
                            {
                                case 1:
                                    addCustomerInfo();
                                    break;
                                case 2:
                                    editInfo("customer.csv",1);
                                    break;
                                case 3:
                                    deleteInfo("customer.csv");
                                    break;
                                case 4:
                                    viewInfo("customer.csv");
                                    break;
                            }

                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("Invalid input! Enter a number from 1-4 only.");
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("**************************\n" +
                                    "Please choose an option: \n" +
                                    "1. Add shop info\n" +
                                    "2. Edit shop info\n" +
                                    "3. Delete shop info\n" +
                                    "4. View shop info\n " +
                                    "Enter any other key to return to main menu\n " +
                                    "**************************\n" +
                                    "Your choice: ");

                            Scanner inCase2 = new Scanner(System.in);
                            int case2Choice = inCase2.nextInt();
                            switch(case2Choice)
                            {
                                case 1:
                                    addShopInfo();
                                    break;
                                case 2:
                                    editInfo("shop.csv",2);
                                    break;
                                case 3:
                                    deleteInfo("shop.csv");
                                    break;
                                case 4:
                                    viewInfo("shop.csv");
                                    break;
                            }
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("Invalid input! Enter a number from 1-4 only.");
                        }
                        break;
                    case 3:
                        // Lottery drawing

                        drawTicketWinningChance(5);
                        System.out.println("Enter Y to quit or enter other any key to return to main menu: ");
                        Scanner inCase3 = new Scanner(System.in);
                        String case3Choice = inCase3.nextLine();
                        returnToMainMenu = case3Choice.toUpperCase();
                        break;
                    case 4:
                        // Exit program here
                        System.out.println("Program exits. Have a good day!");
                        System.exit(0);
                        break;
                    default:
                        // Default case
                        System.out.println("Invalid input! Enter a number from 1-4 only.");
                        System.out.print("Enter Y to quit or enter other any key to return to main menu: ");
                        Scanner inDefault = new Scanner(System.in);
                        String defaultChoice = inDefault.nextLine();
                        returnToMainMenu = defaultChoice.toUpperCase();
                        break;


                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input! Enter a number from 1 to 4 only. Please try again");
                System.out.println(" ");
            }
        }
    }
    public static void drawLotteryWeekly()
    {   final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        int[] weeklyTicket = new int[6];
        TicketNumberGenerator weeklyWinningTicket = new TicketNumberGenerator(weeklyTicket);
        weeklyWinningTicket.generateNumbers();
        System.out.print("The weekly winning ticket's numbers are: ");
        for (int i = 0; i < weeklyTicket.length; i++)
        {
              System.out.print(weeklyTicket[i] + " ");
        }
    }
    public static void drawTicketWinningChance(int tests)
    {
        // TODO INCLUDE THE PRINT STATEMENT
        int totalCount = 0;
        int numberOfTests = tests; // Number of tests run
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
    public static void addCustomerInfo()
    {   // TODO DONE. BUG TESTING PHASE. COMMENT
        String passCustomerInfo = "N";
        while(!passCustomerInfo.equals("Y"))
        {
            try
            {
                CustomerInfo customer = new CustomerInfo();
                Scanner inAddCusInfo = new Scanner(System.in);
                System.out.print("Customer's name: ");
                String name = inAddCusInfo.nextLine();
                System.out.print("Customer's birthday: ");
                String birthday = inAddCusInfo.nextLine();
                System.out.print("Customer's address: ");
                String address = inAddCusInfo.nextLine();
                System.out.print("Customer's phone: ");
                String phone = inAddCusInfo.nextLine();
                System.out.print("Customer's email: ");
                String email = inAddCusInfo.nextLine();
                customer.setName(name);
                customer.setBirthday(birthday);
                customer.setAddress(address);
                customer.setPhone(phone);
                customer.setEmail(email);


                customer.addCustomerInfo("customer.csv");

                Scanner inAddAnother = new Scanner(System.in);
                System.out.print("Enter Y to return to main menu or enter any key to continue adding: ");
                String addAnother = inAddAnother.nextLine();
                passCustomerInfo = addAnother.toUpperCase();

            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input!");
            }
        }

    }
    public static void addShopInfo()
    {   // TODO DONE. BUG TESTING PHASE. COMMENT
        String passShopInfo = "N";
        while(!passShopInfo.equals("Y"))
        {
            try
            {
                ShopInfo shop = new ShopInfo();
                Scanner inAddCusInfo = new Scanner(System.in);
                System.out.print("Shop's code: ");
                String shopCode = inAddCusInfo.nextLine();
                System.out.print("Shop's address: ");
                String shopAddress = inAddCusInfo.nextLine();
                System.out.print("Shop owner: ");
                String shopOwner = inAddCusInfo.nextLine();
                System.out.print("Shop's phone: ");
                String shopPhone = inAddCusInfo.nextLine();
                System.out.print("Shop's email: ");
                String shopEmail = inAddCusInfo.nextLine();
                System.out.print("Shop's account balance: ");
                String shopBalance = inAddCusInfo.nextLine();
                shop.setShopCode(shopCode);
                shop.setShopAddress(shopAddress);
                shop.setShopOwner(shopOwner);
                shop.setShopPhone(shopPhone);
                shop.setShopEmail(shopEmail);
                shop.setShopBalance(shopBalance);

                shop.addShopInfo("shop.csv");

                Scanner inAddAnother = new Scanner(System.in);
                System.out.print("Enter Y to return to main menu or enter any key to continue adding: ");
                String addAnother = inAddAnother.nextLine();
                passShopInfo = addAnother.toUpperCase();

            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input");
            }
        }

    }
    public static void viewInfo(String FileName) throws InterruptedException
    {   // TODO DONE. BUG TESTING PHASE. COMMENT
        // Usable for both customer and shop list
        CustomerInfo customer = new CustomerInfo();
        if (customer.checkEmptyFile(FileName))
        {
            System.out.println("File is empty. Returning to main menu...");
            Thread.sleep(4000);
        }
        else {
            customer.viewCustomerInfo(FileName);
            boolean passViewCusInfo = false;
            while(!passViewCusInfo)
            {
                Scanner inViewCusInfo = new Scanner(System.in);
                System.out.print("Enter Y to return to main menu: ");
                String viewCusInfoChoice = inViewCusInfo.nextLine().toUpperCase();
                Thread.sleep(10);
                if (viewCusInfoChoice.equals("Y"))
                {
                    Thread.interrupted();
                    passViewCusInfo = true;
                }
            }
        }
    }
    public static void deleteInfo(String FileName) throws InterruptedException
    {
        // TODO DONE. BUGS TESTING PHASE. COMMENT
        // Usable for both customer and shop list
        CustomerInfo customer = new CustomerInfo();

        if (customer.checkEmptyFile(FileName)) {
            System.out.println("File is empty! Returning to main menu.");
        }
        else
            {
            boolean passMenuDeleteInfo = false;
            while (!passMenuDeleteInfo)
            {
            try
            {   customer.viewCustomerInfo(FileName);
                System.out.print("Please choose an option\n" +
                        "1. Delete a single piece of information\n" +
                        "2. Delete all\n" +
                        "Your choice: ");
                Scanner optionDeleteInfo = new Scanner(System.in);
                int choiceDeleteInfo = optionDeleteInfo.nextInt();
                if(choiceDeleteInfo == 1) {
                    boolean passDeleteCustomerInfo = false;
                    while (!passDeleteCustomerInfo) {
                        Scanner inDeleteCusInfo = new Scanner(System.in);
                        System.out.print("Enter the left-most 6 digit hexadecimal number associated with the customer you want to delete: ");
                        String deleteCusInfoChoice = inDeleteCusInfo.nextLine().toUpperCase();

                        if (customer.checkHexadecimalIndex(FileName, deleteCusInfoChoice)) {
                            System.out.print("Are you sure you want to proceed (Y/N)? ");
                            String deleteCusInfoChoice2 = inDeleteCusInfo.nextLine().toUpperCase();
                            if (deleteCusInfoChoice2.equals("Y")) {
                                customer.deleteCustomerInfo(FileName, deleteCusInfoChoice);
                                System.out.println("Deleted!");}

                            if (customer.checkEmptyFile(FileName)) {
                                System.out.println("File is empty! Returning to main menu.");
                                Thread.sleep(4000); // Delay for 4 seconds for user to read the message
                                passDeleteCustomerInfo = true;
                            } else {
                                Scanner inDeleteCusInfo2 = new Scanner(System.in);
                                System.out.print("Enter to Y to return main menu or enter any key to keep deleting");
                                String deleteCusInfoChoice3 = inDeleteCusInfo2.nextLine().toUpperCase();
                                if (deleteCusInfoChoice3.equals("Y")) {
                                    passDeleteCustomerInfo = true;
                                }
                            }

                        } else {
                            System.out.println(deleteCusInfoChoice + " does not exist! Try again");
                        }


                        passMenuDeleteInfo = true;}
                }
                else if (choiceDeleteInfo == 2)
                {
                        customer.deleteAllInfo(FileName);
                        System.out.println("All information is deleted. Returning to main menu");
                        }

                else{ System.out.println("Invalid Input. Enter number from 1 to 2 only.");}
                passMenuDeleteInfo = true;

             }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid Input!");
            }
            }
        }

    }
    public static void editInfo(String FileName, int shoporcustomer) throws InterruptedException
    {   //TODO LAZY WAY
        CustomerInfo customer = new CustomerInfo();
        System.out.println("Deleting old information");
        Thread.sleep(2000);
        deleteInfo(FileName);
        System.out.println("Adding new information");
        Thread.sleep(2000);
        if (shoporcustomer == 1)
        {addCustomerInfo();}
        else
        {addShopInfo();}
        System.out.println("Changes saved! Returning to main menu");
        Thread.sleep(4000);
    }
}




