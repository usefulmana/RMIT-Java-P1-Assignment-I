/*
 * RMIT University Vietnam - Saigon South Campus
 * Course: COSC2081 - Programming I
 * Semester: 2018C
 * Assignment: I
 * Author: Nguyen Le Bao Anh (s3616128)
 * Created on: 11/10/2018
 * Last updated on: 22/10/2018

 */

package rmit.p1;
import java.util.*;
import java.text.SimpleDateFormat;



public class Main
{
    public static void  main(String[] args) throws InterruptedException
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
                        "3. View weekly result \n" +
                        "4. Average number of tickets needed to win (5 draws)\n" +
                        "5. Exit \n" +
                        "**********************************\n" + "Your choice:");

                Scanner inMainMenu = new Scanner(System.in);
                int choiceMainMenu = inMainMenu.nextInt();

                switch (choiceMainMenu)
                {
                    case 1:
                        // Customer info manipulation
                        try {
                            // Menu
                            System.out.print("**************************\n" +
                                    "Please choose an option: \n" +
                                    "1. Add customer info\n" +
                                    "2. Edit customer info\n" +
                                    "3. Delete customer info\n" +
                                    "4. View customer info\n " +
                                    "   Enter any other key to return to main menu\n" +
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
                            System.out.println("");
                        }
                        break;

                    case 2:
                        // Shop info manipulation
                        try {
                            // Menu
                            System.out.print("**************************\n" +
                                    "Please choose an option: \n" +
                                    "1. Add shop info\n" +
                                    "2. Edit shop info\n" +
                                    "3. Delete shop info\n" +
                                    "4. View shop info\n " +
                                    "   Enter any other key to return to main menu\n " +
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
                            System.out.println("");
                        }
                        break;
                    case 3:
                        // Weekly lottery result

                        // Importing Timer class to schedule this task
                        Timer timer = new Timer();
                        TimerTask myTask = new TimerTask() {
                            @Override
                            public void run() {
                                System.out.println(" ");
                                System.out.println("Counting...");
                                System.out.println("Numbers of tickets needed to win: " + drawTicketWinningChance(1));
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                Date date = new Date();

                                // Get current date and time
                                System.out.println("Current date and time: " + dateFormat.format(date));

                                // Calculating the date of next draw
                                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                Calendar c = Calendar.getInstance();
                                c.setTime(date); // Now use today date.
                                c.add(Calendar.DATE, 7); // Adding 7 days
                                String output = sdf.format(c.getTime());
                                System.out.println("Next draw will happen on " + output + " " + timeFormat.format(date.getTime()));
                                System.out.println("Returning to main menu...");

                            }
                        };
                        // Scheduler
                        timer.schedule(myTask,50,604800000); // 1 week = 604800000 milliseconds
                        Thread.sleep(60000); // Waiting for the print statement to finish
                        break;
                    case 4:
                        // Displaying average numbers tickets needed to win
                        System.out.println("Average number of tickets needed to win (5 draws): " + drawTicketWinningChance(5));
                        break;
                    case 5:
                        // Exit program here
                        System.out.println("Program exits. Have a good day!");
                        System.exit(0);
                        break;
                    default:
                        // Default case & errors handling
                        // Return to main menu if wrong input
                        System.out.println("Invalid input! Enter a number from 1-5 only.");
                        Thread.sleep(3000);
                        break;


                }
            }
            catch (InputMismatchException e)
            {
                // Return to main menu if wrong input
                System.out.println("Invalid input! Enter a number from 1 to 5 only. Please try again");
                Thread.sleep(3000);
                System.out.println(" ");
            }
            catch (InterruptedException e)
            {
                System.out.println("InterruptedException");
                System.out.println(" ");
            }
        }
    }

    public static int drawTicketWinningChance(int numberOfTests)
    {   // using numberOfTests variable to differentiate outputs for case 3 & 4
        int totalCount = 0;
        for (int i = 0; i < numberOfTests; i++)
        {   // Using a ticket number generator class to generate the winning ticket

            int[] winningNumbers = new int[6];

            // Importing the Customer class
            TicketNumberGenerator winningTicket = new TicketNumberGenerator(winningNumbers);
            winningTicket.generateNumbers();
            if (numberOfTests == 1)
            {
                System.out.print("Winning ticket's numbers are: ");
                for (int j = 0; j < winningNumbers.length; j++)
                {
                    System.out.print(winningNumbers[j] + " ");
                }
            }

            // Sorting the winning numbers in ascending order in order to help with comparison
            Arrays.sort(winningNumbers);

            // Using a ticket number generator class again to generate a ticket

            int[] drawnNumbers = new int[6];
            TicketNumberGenerator drawnTicket = new TicketNumberGenerator(drawnNumbers);
            drawnTicket.generateNumbers(); // First Draw
            Arrays.sort(drawnNumbers); // Same as above

            int count = 0;
            System.out.println("Please wait. This may take awhile...");

            // Comparing the drawn numbers and the winning numbers
            while (!Arrays.equals(winningNumbers,drawnNumbers))
            {
                drawnTicket.generateNumbers(); // Second Draw
                Arrays.sort(drawnNumbers); // Sort again
                count++;
                if (numberOfTests == 1)
                {
                    System.out.print(count + " ");
                }


            }
            if (numberOfTests == 1)
            {
                // Print Jackpot when won
                System.out.print(" Jackpot!");
            }
            totalCount += count;
        }

        System.out.println(" ");

        // Return the average tickets needed to win
        return totalCount/numberOfTests;
    }
    public static void addCustomerInfo()
    {   // DONE. BUG TESTING PHASE. COMMENT
        // This method is used for adding customer information to a text file which is named customer.csv

        String passCustomerInfo = "N"; // End loop condition
        while(!passCustomerInfo.equals("Y")) // Loop here in case if a user wants to add more info after done adding initially
        {
            try
            {
                // Importing the CustomerInfo class
                CustomerInfo customer = new CustomerInfo();

                // Gathering user inputs
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

                // Setters
                customer.setName(name);
                customer.setBirthday(birthday);
                customer.setAddress(address);
                customer.setPhone(phone);
                customer.setEmail(email);

                // Using the Customer class' addCustomerInfo method to add info to a text file
                customer.addCustomerInfo("customer.csv");

                // Return to main menu or keep adding prompt
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
        // This method is used for adding Vietlot shop information to a text file which is named shop.csv

        String passShopInfo = "N"; // End loop condition
        while(!passShopInfo.equals("Y")) // Loop here in case if a user wants to add more info after done adding initially
        {
            try
            {   // Import the ShopInfo class
                ShopInfo shop = new ShopInfo();

                // Gathering user inputs
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
                System.out.print("Shop's deposit: ");
                int shopDeposit = inAddCusInfo.nextInt();
                System.out.print("Shop's money issuing lottery: ");
                int shopMoneyIssuingLot = inAddCusInfo.nextInt();

                // Account balance = Deposit - money issuing lottery. Convert int to String
                String shopBalance = Integer.toString(shopDeposit - shopMoneyIssuingLot);

                // Setters
                shop.setShopCode(shopCode);
                shop.setShopAddress(shopAddress);
                shop.setShopOwner(shopOwner);
                shop.setShopPhone(shopPhone);
                shop.setShopEmail(shopEmail);
                shop.setShopBalance(shopBalance);

                // Using the Customer class' addCustomerInfo method to add info to a text file
                shop.addShopInfo("shop.csv");

                // Return to main menu or keep adding prompt
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
    public static void viewInfo(String FileName)
    {
        // This method is usable for both customer and shop list

        // Importing CustomerInfo class
        CustomerInfo customer = new CustomerInfo();
        try{
            // Check if the file is empty. If so, return to main menu. Else, proceed.
        if (customer.checkEmptyFile(FileName))
        {
            System.out.println("File is empty. Returning to main menu...");
            Thread.sleep(4000);
        }
        else {
            customer.viewCustomerInfo(FileName);
            boolean passViewCusInfo = false;
            while (!passViewCusInfo) // Loop here to handle wrong input
            {
                Scanner inViewCusInfo = new Scanner(System.in);
                System.out.print("Enter Y to return to main menu: ");
                String viewCusInfoChoice = inViewCusInfo.nextLine().toUpperCase();
                Thread.sleep(10); // Sleep so method wont automatically return to main menu
                if (viewCusInfoChoice.equals("Y")) {
                    Thread.interrupted(); // Resume based on user input
                    passViewCusInfo = true; // Loop ends here
                }
            }
        }
        }
        catch (InterruptedException e)
        {
            System.out.println("InterruptedException");
            System.out.println(" ");
        }
    }
    public static void deleteInfo(String FileName)
    {
        // This method is usable for both customer and shop list

        // Importing the CustomerInfo class
        CustomerInfo customer = new CustomerInfo();

        // Check if the file is empty. If so, return to main menu. Else, proceed.
        if (customer.checkEmptyFile(FileName)) {
            System.out.println("File is empty! Returning to main menu.");
        }
        else
            {
            boolean passMenuDeleteInfo = false;
            while (!passMenuDeleteInfo) // Loop here to handle wrong input
            {
            try
            {   customer.viewCustomerInfo(FileName); // Displaying info

                // Menu
                System.out.print("Please choose an option\n" +
                        "1. Delete a single piece of information\n" +
                        "2. Delete all\n" +
                        "3. Return to main menu\n"+
                        "Your choice: ");
                Scanner optionDeleteInfo = new Scanner(System.in);
                int choiceDeleteInfo = optionDeleteInfo.nextInt();

                // Delete a single line
                if(choiceDeleteInfo == 1) {
                    boolean passDeleteCustomerInfo = false;
                    while (!passDeleteCustomerInfo) {
                        Scanner inDeleteCusInfo = new Scanner(System.in);
                        System.out.print("Enter the left-most 6 digit hexadecimal number associated with the customer you want to delete: ");
                        String deleteCusInfoChoice = inDeleteCusInfo.nextLine().toUpperCase(); // handling lower-case user input

                        if (customer.checkHexadecimalIndex(FileName, deleteCusInfoChoice)) // Checking if the numbers entered exists in the list
                        {
                            System.out.print("Are you sure you want to proceed (Y/N)? "); // Confirmation prompt
                            String deleteCusInfoChoice2 = inDeleteCusInfo.nextLine().toUpperCase(); // handling lower-case user input
                            if (deleteCusInfoChoice2.equals("Y")) {
                                customer.deleteCustomerInfo(FileName, deleteCusInfoChoice); // Using deleteCustomerInfo method
                                System.out.println("Deleted!");}

                            if (customer.checkEmptyFile(FileName)) // Check if the file is empty again to avoid infinite loop
                            {
                                System.out.println("File is empty! Returning to main menu...");
                                Thread.sleep(4000); // Delay to let user read the message
                                passDeleteCustomerInfo = true;
                            } else {

                                // Keep deleting or return to main menu prompt
                                Scanner inDeleteCusInfo2 = new Scanner(System.in);
                                System.out.print("Enter to Y to return main menu or enter any key to keep deleting");
                                String deleteCusInfoChoice3 = inDeleteCusInfo2.nextLine().toUpperCase(); // handling lower-case user input
                                if (deleteCusInfoChoice3.equals("Y")) {
                                    passDeleteCustomerInfo = true;
                                }
                            }

                        }

                        else // Numbers do not exist in the list
                        {
                            System.out.println(deleteCusInfoChoice + " does not exist! Try again");
                        }


                        passMenuDeleteInfo = true; // End loop here
                    }
                }
                else if (choiceDeleteInfo == 2)
                {       Scanner inDeleteAll = new Scanner(System.in);
                        System.out.print("Are you sure you want to delete all the information (Y/N)?: "); // Confirmation prompt
                        String deleteAllChoice = inDeleteAll.nextLine().toUpperCase(); // handling lower-case user input
                        if (deleteAllChoice.equals("Y"))
                        {
                        customer.deleteAllInfo(FileName); // deleteAllInfo from CustomerInfo class
                        System.out.println("All information is deleted. Returning to main menu...");
                        Thread.sleep(4000); // Delay to let user read the message
                        }
                        else
                        {
                            System.out.println("Returning to main menu...");
                            Thread.sleep(2000); // Delay to let user read the message
                            break;
                        }
                }
                else if(choiceDeleteInfo == 3)
                {
                    System.out.println("Returning to main menu...");
                    Thread.sleep(2000); // Delay to let user read the message
                    break;
                }

                else{ System.out.println("Invalid Input. Enter number from 1 to 3 only.");}
                passMenuDeleteInfo = true;

             }

             // Errors handling
            catch (InputMismatchException e)
            {
                System.out.println("Invalid Input!");
            }
            catch (InterruptedException e)
            {
                System.out.println("InterruptedException");
                System.out.println(" ");
            }
            }
        }

    }
    public static void editInfo(String FileName, int shoporcustomer)
    {   // shoporcustomer variable is used to determine which addinfo method to use
        try
        {
            CustomerInfo customer = new CustomerInfo();
            System.out.println("Please delete old information");
            Thread.sleep(2000);
            deleteInfo(FileName);
            System.out.println("Enter new information");
            Thread.sleep(2000);
            if (shoporcustomer == 1) {
                addCustomerInfo();
            } else {
                addShopInfo();
            }
            System.out.println("Changes saved! Returning to main menu");
        }
        catch (InterruptedException e)
        {
            System.out.println("InterruptedException");
            System.out.println(" ");
        }
    }
}






