/*
 * RMIT University Vietnam - Saigon South Campus
 * Course: COSC2081 - Programming I
 * Semester: 2018C
 * Assignment: I
 * Author: Nguyen Le Bao Anh (s3616128)
 * Created on: 11/10/2018
 * Last updated on: 28/10/2018
 */

package rmit.p1;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.*;
import java.net.MalformedURLException;



public class Main
{
    public static void  main(String[] args) throws InterruptedException
    {
        // Main Menu
        String returnToMainMenu = "Y";
        while(!returnToMainMenu.equals("N"))
        {
            try
            {
                System.out.print("***********MAIN MENU***************\n" +
                        "Please choose an option: \n" +
                        "1. Add/Edit/Delete/View the customer list \n" +
                        "2. Add/Edit/Delete/View the Vietlot shop list \n" +
                        "3. View weekly result \n" +
                        "4. Print the number of tickets needed to win in a line until jackpot (1 draw)\n" +
                        "5. Average number of tickets needed to win (5 draws)\n" +
                        "6. Exit \n" +
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
                                    "4. View customer info\n" +
                                    "5. Enter any other key to return to main menu\n" +
                                    "**************************\n" +
                                    "Your choice: ");

                            Scanner inCase1 = new Scanner(System.in);
                            int case1Choice = inCase1.nextInt();
                            switch(case1Choice)
                            {
                                case 1:
                                    addCustomerInfo("customer.csv");
                                    break;
                                case 2:
                                    editCustomerInfo("customer.csv");
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
                            System.out.println("Returning to main menu...");
                            Thread.sleep(2000);
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
                                    "4. View shop info\n" +
                                    "5. Enter any other key to return to main menu\n " +
                                    "**************************\n" +
                                    "Your choice: ");

                            Scanner inCase2 = new Scanner(System.in);
                            int case2Choice = inCase2.nextInt();
                            switch(case2Choice)
                            {
                                case 1:
                                    addShopInfo("shop.csv");
                                    break;
                                case 2:
                                    editShopInfo("shop.csv");
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
                            System.out.println("Returning to main menu...");
                            Thread.sleep(2000);
                        }
                        break;
                    case 3:
                        // Weekly lottery result

                        // Importing Timer class to schedule this task
                        Timer timer = new Timer();
                        TimerTask myTask = new TimerTask() {
                            @Override
                            public void run() {
                                try{
                                drawTicketWinningChance(1,3);
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                Date date = new Date();

                                // Get current date and time
                                System.out.println("Current date and time: " + dateFormat.format(date));

                                // Calculating the date of next draw
                                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); // Format time
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Format date
                                Calendar c = Calendar.getInstance();
                                c.setTime(date); // Now use today date.
                                c.add(Calendar.DATE, 7); // Adding 7 days
                                String output = sdf.format(c.getTime());
                                System.out.println("Next draw will happen on: " + output + " " + timeFormat.format(date.getTime()));
                                System.out.println("You will be notified when the next draw takes place...");
                                if (SystemTray.isSupported())
                                {
                                    if (SystemTray.isSupported()) {
                                        SystemNotif td = new SystemNotif();
                                        td.displayTray("New Weekly Lottery Result!","New ticket is drawn");
                                    } else {
                                        System.err.println("System tray not supported!");
                                    }
                                }
                                System.out.println("Returning to main menu...");}
                                catch (AWTException e)
                                {
                                    System.out.println("AWTException");
                                }
                                catch (MalformedURLException e)
                                {
                                    System.out.println("MalformedURLException");
                                }
                            }
                        };
                        // Scheduler

                        timer.schedule(myTask,50,604800000); // 1 week = 604800000 milliseconds
                        Thread.sleep(10000); // Waiting for the print statement to finish
                        break;
                    case 4:

                        System.out.println(" ");
                        System.out.println("Number of tickets needed to win: " + drawTicketWinningChance(1,4));
                        System.out.println("Jackpot!");
                        System.out.println("Returning to main menu...");
                        Thread.sleep(3000);
                        break;
                    case 5:
                        // Displaying average numbers tickets needed to win
                        System.out.println("Average number of tickets needed to win (5 draws): " + drawTicketWinningChance(5,5));
                        Thread.sleep(5000);
                        break;
                    case 6:
                        // Exit program here
                        System.out.println("Program exits. Have a good day!");
                        System.exit(0);
                        break;
                    default:
                        // Default case & errors handling
                        // Return to main menu if wrong input
                        System.out.println("Invalid input! Enter a number from 1-6 only.");
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
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File does not exist");
            }
        }
    }

    public static int drawTicketWinningChance(int numberOfTests, int caseNumber)
    {   // using numberOfTests and caseNumber variables to differentiate outputs for case 3,4 & 5
        int totalCount = 0;
        for (int i = 0; i < numberOfTests; i++)
        {   // Using a ticket number generator class to generate the winning ticket

            int[] winningNumbers = new int[6];

            // Importing the Customer class
            TicketNumberGenerator winningTicket = new TicketNumberGenerator(winningNumbers);
            winningTicket.generateNumbers();
            if (numberOfTests == 1 && caseNumber == 3)
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

            // Comparing the drawn numbers and the winning numbers
            while (!Arrays.equals(winningNumbers,drawnNumbers))
            {
                drawnTicket.generateNumbers(); // Second Draw
                Arrays.sort(drawnNumbers); // Sort again
                count++;
                if (numberOfTests == 1 && caseNumber == 4)
                {
                    System.out.print(count + " ");
                }


            }
            totalCount += count;
        }

        System.out.println(" ");

        // Return the average tickets needed to win
        return totalCount/numberOfTests;
    }
    public static void addCustomerInfo(String FileName)
    {
        // This method is used for adding customer information to a text file which is named customer.csv

        String passCustomerInfo = "Y"; // End loop condition
        while(!passCustomerInfo.equals("N")) // Loop here in case if a user wants to add more info after done adding initially
        {
            try
            {
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
                // Importing the CustomerInfo class
                CustomerInfo customer = new CustomerInfo(toTitleCase(name),birthday,address,phone,email);

                // Using the Customer class' addCustomerInfo method to add info to a text file
                customer.addCustomerInfo("customer.csv");
                System.out.println("Added!");

                // Return to main menu or keep adding prompt
                System.out.print("Continue adding (Y/N)? ");
                String addAnother = inAddCusInfo.nextLine();
                passCustomerInfo = addAnother.toUpperCase();

            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input!");
            }
        }

    }
    public static void addShopInfo(String FileName)
    {
        // This method is used for adding Vietlot shop information to a text file which is named shop.csv

        String passShopInfo = "Y"; // End loop condition
        while(!passShopInfo.equals("N")) // Loop here in case if a user wants to add more info after done adding initially
        {
            try
            {

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

                // Import the ShopInfo class
                ShopInfo shop = new ShopInfo(shopCode,shopAddress,toTitleCase(shopOwner),shopPhone,shopEmail,shopBalance);

                // Using the Customer class' addCustomerInfo method to add info to a text file
                shop.addShopInfo("shop.csv");
                System.out.println("Added!");

                // Return to main menu or keep adding prompt
                Scanner inAddAnother = new Scanner(System.in);
                System.out.print("Continue adding (Y/N)? ");
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
                System.out.print("Return to main menu (Y/N)? ");
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
                        "3. Enter any other key to return to main menu\n"+
                        "Your choice: ");
                Scanner optionDeleteInfo = new Scanner(System.in);
                int choiceDeleteInfo = optionDeleteInfo.nextInt();

                // Delete a single line
                if(choiceDeleteInfo == 1) {
                    boolean passDeleteCustomerInfo = false;
                    while (!passDeleteCustomerInfo) {
                        Scanner inDeleteCusInfo = new Scanner(System.in);
                        System.out.println("Enter the left-most 6 digits associated with the line that you want to delete");
                        System.out.print("Your choice: ");
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
                                System.out.print("Continue deleting (Y/N)?");
                                String deleteCusInfoChoice3 = inDeleteCusInfo2.nextLine().toUpperCase(); // handling lower-case user input
                                if (deleteCusInfoChoice3.equals("Y")) {
                                    passDeleteCustomerInfo = false;
                                }
                                else
                                {
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
                {       // Delete all options

                        Scanner inDeleteAll = new Scanner(System.in);
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
    public static void editAllInfo(String FileName, int shoporcustomer)
    {   // This method is used to replace an existing line
        // shoporcustomer variable is used to determine which addinfo method to use

        try
        {
            // Delete old info first
            System.out.println("Please delete old information, then add new information");
            Thread.sleep(2000);
            deleteInfo(FileName);
            // Replace
            System.out.println("Enter new information");
            Thread.sleep(2000);
            if (shoporcustomer == 1) {
                addCustomerInfo("customer.csv");
            } else {
                addShopInfo("shop.csv");
            }
            System.out.println("Changes saved! Returning to main menu");
        }
        catch (InterruptedException e)
        {
            System.out.println("InterruptedException");
            System.out.println(" ");
        }
    }
    public static void editCustomerInfo(String FileName) throws FileNotFoundException
    { // This method is used to edit each individual customer's information
            CustomerInfo customer = new CustomerInfo();
            if (customer.checkEmptyFile(FileName)) // Check if file is empty
            {
                System.out.println("File is empty! Returning to main menu.");
            }
            else {

                    try {
                        customer.viewCustomerInfo(FileName); // Displaying info
                        Scanner optionEditCusInfo = new Scanner(System.in);
                        System.out.println("Enter the left-most 6 digits associated with the line that you want to edit");
                        System.out.print("Your choice: ");
                        String choiceEditCusInfo = optionEditCusInfo.nextLine().toUpperCase();
                        boolean passMenuEditCusInfo = false;
                        while (!passMenuEditCusInfo) // Loop here to handle wrong input
                        {
                            if (customer.checkHexadecimalIndex(FileName, choiceEditCusInfo)) // checking if the user exists
                            {
                                // Menu
                                System.out.print("Please choose an option to edit\n" +
                                        "1. Edit customer's name\n" +
                                        "2. Edit customer's birthday\n" +
                                        "3. Edit customer's address\n" +
                                        "4. Edit customer's phone number\n" +
                                        "5. Edit customer's email address\n" +
                                        "6. Edit all information\n" +
                                        "7. Enter any other key to return to main menu\n" +
                                        "Your choice: ");
                                int choiceEditCusInfo2 = optionEditCusInfo.nextInt();
                                // Ask for old info and replace it with new info
                                if (choiceEditCusInfo2 == 1) {
                                    System.out.print("Old name: ");
                                    optionEditCusInfo.nextLine();
                                    String oldCusName = optionEditCusInfo.nextLine();
                                    if(customer.infoChecker(FileName,oldCusName)) {
                                        System.out.print("New name: ");
                                        String newCusName = optionEditCusInfo.nextLine();
                                        customer.editFile(FileName, choiceEditCusInfo, oldCusName, toTitleCase(newCusName));
                                        customer.viewCustomerInfo(FileName);
                                        System.out.print("Change saved! Continue editing (Y/N)? ");
                                        String answerEditCusName = optionEditCusInfo.nextLine().toUpperCase();
                                        if (answerEditCusName.equals("Y")) {
                                            continue;
                                        } else {
                                            passMenuEditCusInfo = true;
                                        }
                                    }
                                    else
                                    { // Wrong input handling
                                        System.out.println("The information entered does not exist! Please try again");
                                        Thread.sleep(2000);
                                    }

                                }
                                else if (choiceEditCusInfo2 == 2) {
                                    System.out.print("Old birthday: ");
                                    optionEditCusInfo.nextLine();
                                    String oldCusBirthday = optionEditCusInfo.nextLine();
                                    if(customer.infoChecker(FileName, oldCusBirthday)) {
                                        System.out.print("New birthday: ");
                                        String newCusBirthday = optionEditCusInfo.nextLine();
                                        customer.editFile(FileName, choiceEditCusInfo, oldCusBirthday, newCusBirthday);
                                        customer.viewCustomerInfo(FileName);
                                        System.out.print("Change saved! Continue editing (Y/N)? ");
                                        String answerEditCusName = optionEditCusInfo.nextLine().toUpperCase();
                                        if (answerEditCusName.equals("Y")) {
                                            continue;
                                        } else {
                                            passMenuEditCusInfo = true;
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("The information entered does not exist! Please try again");
                                        Thread.sleep(2000);
                                    }

                                }
                                else if (choiceEditCusInfo2 == 3) {
                                        System.out.print("Old Address: ");
                                        optionEditCusInfo.nextLine();
                                        String oldCusAddress = optionEditCusInfo.nextLine();
                                    if(customer.infoChecker(FileName, oldCusAddress)) {
                                        System.out.print("New Address: ");
                                        String newCusAddress = optionEditCusInfo.nextLine();
                                        customer.editFile(FileName, choiceEditCusInfo, oldCusAddress, newCusAddress);
                                        customer.viewCustomerInfo(FileName);
                                        System.out.print("Change saved! Continue editing (Y/N)? ");
                                        String answerEditCusName = optionEditCusInfo.nextLine().toUpperCase();
                                        if (answerEditCusName.equals("Y")) {
                                            continue;
                                        } else {
                                            passMenuEditCusInfo = true;
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("The information entered does not exist! Please try again");
                                        Thread.sleep(2000);
                                    }

                                }
                                else if (choiceEditCusInfo2 == 4) {
                                    System.out.print("Old Phone Number: ");
                                    optionEditCusInfo.nextLine();
                                    String oldCusPhone = optionEditCusInfo.nextLine();
                                    if(customer.infoChecker(FileName, oldCusPhone)) {
                                        System.out.print("New Phone Number: ");
                                        String newCusPhone = optionEditCusInfo.nextLine();
                                        customer.editFile(FileName, choiceEditCusInfo, oldCusPhone, newCusPhone);
                                        customer.viewCustomerInfo(FileName);
                                        System.out.print("Change saved! Continue editing (Y/N)? ");
                                        String answerEditCusName = optionEditCusInfo.nextLine().toUpperCase();
                                        if (answerEditCusName.equals("Y")) {
                                            continue;
                                        } else {
                                            passMenuEditCusInfo = true;
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("The information entered does not exist! Please try again");
                                        Thread.sleep(2000);
                                    }

                                }
                                else if (choiceEditCusInfo2 == 5) {
                                    System.out.print("Old Email: ");
                                    optionEditCusInfo.nextLine();
                                    String oldCusEmail = optionEditCusInfo.nextLine();
                                    if(customer.infoChecker(FileName, oldCusEmail)) {
                                        System.out.print("New Email: ");
                                        String newCusEmail = optionEditCusInfo.nextLine();
                                        customer.editFile(FileName, choiceEditCusInfo, oldCusEmail, newCusEmail);
                                        customer.viewCustomerInfo(FileName);
                                        System.out.print("Change saved! Continue editing (Y/N)? ");
                                        String answerEditCusName = optionEditCusInfo.nextLine().toUpperCase();
                                        if (answerEditCusName.equals("Y")) {
                                            continue;
                                        } else {
                                            passMenuEditCusInfo = true;
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("The information entered does not exist! Please try again");
                                        Thread.sleep(2000);
                                    }

                                }
                                else if (choiceEditCusInfo2 == 6) {
                                    editAllInfo(FileName, 1);

                                }
                                else
                                {
                                    System.out.println("Returning to main menu...");
                                    Thread.sleep(3000);
                                }
                            } else {
                                System.out.println(choiceEditCusInfo + " does not exist! Returning to main menu...");
                                Thread.sleep(3000);
                                break;
                            }


                        }
                    }catch (InputMismatchException e)
                    {
                        System.out.println("Wrong input");
                    }
                    catch (InterruptedException a)
                    {
                        System.out.println("InterruptedException");
                    }
                }
    }
    public static void editShopInfo(String FileName) throws FileNotFoundException
    { // Same as the editCustomerInfo method but modified for shop
        CustomerInfo customer = new CustomerInfo();
        if (customer.checkEmptyFile(FileName))
        {
            System.out.println("File is empty! Returning to main menu.");
        }
        else {

            try {
                customer.viewCustomerInfo(FileName); // Displaying info
                Scanner optionEditShopInfo = new Scanner(System.in);
                System.out.println("Enter the left-most 6 digits associated with the line that you want to edit");
                System.out.print("Your choice: ");
                String choiceEditShopInfo = optionEditShopInfo.nextLine().toUpperCase();
                boolean passMenuEditShopInfo = false;
                while (!passMenuEditShopInfo) // Loop here to handle wrong input
                {
                    if (customer.checkHexadecimalIndex(FileName, choiceEditShopInfo)) {
                        // Menu
                        System.out.print("Please choose an option to edit\n" +
                                "1. Edit shop's code\n" +
                                "2. Edit shop's address\n" +
                                "3. Edit shop owner's name\n" +
                                "4. Edit shop's phone number\n" +
                                "5. Edit shop's email address\n" +
                                "6. Edit shop's account balance\n" +
                                "7. Edit all information\n" +
                                "8. Enter any other key to return to main menu\n" +
                                "Your choice: ");
                        int choiceEditShopInfo2 = optionEditShopInfo.nextInt();
                        if (choiceEditShopInfo2 == 1) {
                            System.out.print("Old Code: ");
                            optionEditShopInfo.nextLine();
                            String oldShopCode = optionEditShopInfo.nextLine();
                            if(customer.infoChecker(FileName,oldShopCode)) {
                                System.out.print("New Code: ");
                                String newShopCOde = optionEditShopInfo.nextLine();
                                customer.editFile(FileName, choiceEditShopInfo, oldShopCode, newShopCOde);
                                customer.viewCustomerInfo(FileName);
                                System.out.print("Change saved! Continue editing (Y/N)? ");
                                String answerEditShopName = optionEditShopInfo.nextLine().toUpperCase();
                                if (answerEditShopName.equals("Y")) {
                                    continue;
                                } else {
                                    passMenuEditShopInfo = true;
                                }
                            }
                            else
                            {
                                System.out.println("The information entered does not exist! Please try again");
                                Thread.sleep(2000);
                            }

                        }
                        else if (choiceEditShopInfo2 == 2) {
                            System.out.print("Old Shop's Address: ");
                            optionEditShopInfo.nextLine();
                            String oldShopAddress = optionEditShopInfo.nextLine();
                            if(customer.infoChecker(FileName, oldShopAddress)) {
                                System.out.print("New Shop's Address: ");
                                String newShopAddress = optionEditShopInfo.nextLine();
                                customer.editFile(FileName, choiceEditShopInfo, oldShopAddress, newShopAddress);
                                customer.viewCustomerInfo(FileName);
                                System.out.print("Change saved! Continue editing (Y/N)? ");
                                String answerEditShopAddress = optionEditShopInfo.nextLine().toUpperCase();
                                if (answerEditShopAddress.equals("Y")) {
                                    continue;
                                } else {
                                    passMenuEditShopInfo = true;
                                }
                            }
                            else
                            {
                                System.out.println("The information entered does not exist! Please try again");
                                Thread.sleep(2000);
                            }

                        }
                        else if (choiceEditShopInfo2 == 3) {
                            System.out.print("Old Owner's Name: ");
                            optionEditShopInfo.nextLine();
                            String oldOwnerName = optionEditShopInfo.nextLine();
                            if(customer.infoChecker(FileName, oldOwnerName)) {
                                System.out.print("New Owner's Name: ");
                                String newOwnerName = optionEditShopInfo.nextLine();
                                customer.editFile(FileName, choiceEditShopInfo, oldOwnerName, toTitleCase(newOwnerName));
                                customer.viewCustomerInfo(FileName);
                                System.out.print("Change saved! Continue editing (Y/N)? ");
                                String answerEditShopOwnerName = optionEditShopInfo.nextLine().toUpperCase();
                                if (answerEditShopOwnerName.equals("Y")) {
                                    continue;
                                } else {
                                    passMenuEditShopInfo = true;
                                }
                            }
                            else
                            {
                                System.out.println("The information entered does not exist! Please try again");
                                Thread.sleep(2000);
                            }

                        }
                        else if (choiceEditShopInfo2 == 4) {
                            System.out.print("Old Shop's Phone Number: ");
                            optionEditShopInfo.nextLine();
                            String oldShopPhone = optionEditShopInfo.nextLine();
                            if(customer.infoChecker(FileName, oldShopPhone)) {
                                System.out.print("New Shop's Phone Number: ");
                                String newShopPhone = optionEditShopInfo.nextLine();
                                customer.editFile(FileName, choiceEditShopInfo, oldShopPhone, newShopPhone);
                                customer.viewCustomerInfo(FileName);
                                System.out.print("Change saved! Continue editing (Y/N)? ");
                                String answerEditShopPhone = optionEditShopInfo.nextLine().toUpperCase();
                                if (answerEditShopPhone.equals("Y")) {
                                    continue;
                                } else {
                                    passMenuEditShopInfo = true;
                                }
                            }
                            else
                            {
                                System.out.println("The information entered does not exist! Please try again");
                                Thread.sleep(2000);
                            }

                        }
                        else if (choiceEditShopInfo2 == 5) {
                            System.out.print("Old Shop's Email: ");
                            optionEditShopInfo.nextLine();
                            String oldShopEmail = optionEditShopInfo.nextLine();
                            if(customer.infoChecker(FileName, oldShopEmail)) {
                                System.out.print("New Shop's Email: ");
                                String newShopEmail = optionEditShopInfo.nextLine();
                                customer.editFile(FileName, choiceEditShopInfo, oldShopEmail, newShopEmail);
                                customer.viewCustomerInfo(FileName);
                                System.out.print("Change saved! Continue editing (Y/N)? ");
                                String answerEditShopEmail = optionEditShopInfo.nextLine().toUpperCase();
                                if (answerEditShopEmail.equals("Y")) {
                                    continue;
                                } else {
                                    passMenuEditShopInfo = true;
                                }
                            }
                            else
                            {
                                System.out.println("The information entered does not exist! Please try again");
                                Thread.sleep(2000);
                            }

                        }
                        else if (choiceEditShopInfo2 == 6) {
                            System.out.print("Old Shop's Account Balance: ");
                            optionEditShopInfo.nextLine();
                            String oldShopAccBalance = optionEditShopInfo.nextLine();
                            if(customer.infoChecker(FileName, oldShopAccBalance)) {
                                System.out.print("New Shop's Account Balance: ");
                                String newShopAccBalance = optionEditShopInfo.nextLine();
                                customer.editFile(FileName, choiceEditShopInfo, oldShopAccBalance, newShopAccBalance);
                                customer.viewCustomerInfo(FileName);
                                System.out.print("Change saved! Continue editing (Y/N)? ");
                                String answerEditShopAccBalance = optionEditShopInfo.nextLine().toUpperCase();
                                if (answerEditShopAccBalance.equals("Y")) {
                                    continue;
                                } else {
                                    passMenuEditShopInfo = true;
                                }
                            }
                            else
                            {
                                System.out.println("The information entered does not exist! Please try again");
                                Thread.sleep(2000);
                            }

                        }
                        else if (choiceEditShopInfo2 == 7) {
                            editAllInfo(FileName, 1);

                        }
                        else
                        {
                            System.out.println("Returning to main menu...");
                            Thread.sleep(3000);
                        }
                    } else {
                        System.out.println(choiceEditShopInfo + " does not exist! Returning to main menu...");
                        Thread.sleep(3000);
                        break;
                    }


                }
            }catch (InputMismatchException e)
            {
                System.out.println("Wrong input");
            }
            catch (InterruptedException a)
            {
                System.out.println("InterruptedException");
            }
        }
    }
    public static String toTitleCase(String givenString) {
        // Capitalize first letter of each word
        String[] arr = givenString.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        return sb.toString().trim();
    }


}
