/**
 * RMIT University Vietnam - Saigon South Campus
 * Course: COSC2081 - Programming I
 * Semester: 2018C
 * Assignment: II
 * Author: Nguyen Le Bao Anh (s3616128)
 * Created on: 23/12/2018
 * Last updated on: 12/01/2019
 * Version control: */

package rmit.p1;

import rmit.p1.Customer.CustomerFile;
import rmit.p1.Customer.CustomerFileCareTaker;
import rmit.p1.Ticket.Lottery;
import rmit.p1.Vendor.VendorFile;
import rmit.p1.Vendor.VendorFileCareTaker;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    /**This the main class which contains all of the main menu items:*/
    public static void main(String[] args)
    {
	    while(true)
        {
            try {
                System.out.print("***********MAIN MENU***************\n" +
                        "Please choose an option: \n" +
                        "1. Add/Edit/Delete/View the customer list \n" +
                        "2. Add/Edit/Delete/View the Vietlot vendor list \n" +
                        "3. Buy a ticket & view weekly result \n" +
                        "4. Quit\n" +
                        "**********************************\n" + "Your choice:");

                Scanner inMainMenu = new Scanner(System.in);
                int mainMenuChoice = inMainMenu.nextInt();
                if (mainMenuChoice == 1)
                {
                    customerMenu("customer.csv");
                }
                else if (mainMenuChoice == 2)
                {
                    vendorMenu("vendor.csv");
                }
                else if (mainMenuChoice == 3)
                {
                    ticketMenu();
                }
                else if (mainMenuChoice == 4)
                {
                    System.out.println("Program exits. Have a good day!");
                    System.exit(0);
                }
                else
                {
                    System.out.println("Invalid input. Please try again.");
                }

            }
            catch (InputMismatchException ex)
            {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    public static void customerMenu(String customerFile)
    {
        /**This method contains the customer menu*/
        CustomerFile customer = CustomerFile.getInstance(); // Calling the CustomerFile instance
        CustomerFileCareTaker customerFileCareTaker = new CustomerFileCareTaker(); // Creating a CustomerFileCareTaker obj
        customer.setCustomerFileName("customer.csv"); // Assigning a name to the customer's file
        customerFileCareTaker.add(customer.saveCustomerFileName()); // Saving the file name

        while(true)
        {
            try{
                System.out.print("**************************\n" +
                        "Please choose an option: \n" +
                        "1. Add customer info\n" +
                        "2. Edit customer info\n" +
                        "3. Delete customer info\n" +
                        "4. View customer info\n" +
                        "5. Return to main menu\n" +
                        "6. Quit program\n" +
                        "**************************\n" +
                        "Your choice: ");
                Scanner inCustomer = new Scanner(System.in);
                int customerMenuChoice = inCustomer.nextInt();
                if (customerMenuChoice == 1)
                {
                    customer.addCustomer();
                }
                else if (customerMenuChoice == 2)
                {
                    customer.editCustomerInfo();
                }
                else if (customerMenuChoice == 3)
                {
                    customer.deleteCustomer();
                }
                else if (customerMenuChoice == 4)
                {
                    customer.viewCustomerFile();
                }
                else if (customerMenuChoice == 5)
                {
                    break;
                }
                else if (customerMenuChoice == 6)
                {
                    customer.exit();
                }
                else
                {
                    System.out.println("Invalid input. Please try again.");
                }
            }
            catch(InputMismatchException ex)
            {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    public static void vendorMenu(String vendorFile)
    {
        /** This method contains the vendor menu*/
        VendorFile vendor = VendorFile.getInstance(); // getting VendorFile instance
        VendorFileCareTaker vendorFileCareTaker = new VendorFileCareTaker(); // Creating a vendorFileCareTaker object
        vendor.setVendorFileName("vendor.csv"); // Assign name to the vendor's file
        vendorFileCareTaker.add(vendor.saveVendorFileName()); // Saving the file name;

        while(true)
        {
            try{
                System.out.print("**************************\n" +
                        "Please choose an option: \n" +
                        "1. Add vendor info\n" +
                        "2. Edit vendor info\n" +
                        "3. Delete vendor info\n" +
                        "4. View vendor info\n" +
                        "5. Return to main menu\n" +
                        "6. Quit program\n" +
                        "**************************\n" +
                        "Your choice: ");
                Scanner inVendor = new Scanner(System.in);
                int vendorMenuChoice = inVendor.nextInt();
                if (vendorMenuChoice == 1)
                {
                    vendor.addVendor();
                }
                else if (vendorMenuChoice == 2)
                {
                    vendor.editVendorInfo();
                }
                else if (vendorMenuChoice == 3)
                {
                    vendor.deleteVendor();
                }
                else if (vendorMenuChoice == 4)
                {
                    vendor.viewVendorFile();
                }
                else if (vendorMenuChoice == 5)
                {
                    break;
                }
                else if (vendorMenuChoice == 6)
                {
                    vendor.exit();
                }
                else
                {
                    System.out.println("Invalid input. Please try again.");
                }
            }
            catch(InputMismatchException ex)
            {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    public static void ticketMenu()
    {
        /** This method contains the lottery menu*/
        Lottery lottery = Lottery.getInstance(); // Getting the Lottery instance
        while (true)
        {
            try{
                System.out.print("**************************\n" +
                        "Please choose an option: \n" +
                        "1. View draw numbers until jackpot\n" +
                        "2. Display the average chance of winning in 5 draws\n" +
                        "3. Buy a ticket\n" +
                        "4. Subscribe for weekly result notification\n" +
                        "5. Unsubscribe for weekly result notification\n" +
                        "6. Return to ticket menu\n" +
                        "7. Quit program\n" +
                        "**************************\n" +
                        "Your choice: ");
                Scanner inTicketMenu = new Scanner(System.in);
                int ticketMenuChoice = inTicketMenu.nextInt();
                if (ticketMenuChoice == 1)
                {
                    lottery.printDrawCount(); // Printing the draw number until jackpot
                    continue;
                }
                else if(ticketMenuChoice == 2)
                {
                    System.out.println("Please wait. This might take a while...");

                    int[] drawCountArray = new int[5]; // Creating an array to store drawCount

                    // Loop 5 times
                    for (int i = 0; i < 5; i++) {
                        drawCountArray[i] = lottery.drawCount();
                    }

                    // Summing the total draws
                    int sum = 0;
                    for (int i = 0; i < drawCountArray.length; i++) {
                        sum += drawCountArray[i];
                    }

                    System.out.println("Average chance of winning in 5 draws: " + sum/5);
                    continue;
                }
                else if(ticketMenuChoice == 3)
                {
                    lottery.buyTicketMenu();
                    continue;

                }
                else if(ticketMenuChoice == 4)
                {
                    Observer1 observer1 = Observer1.getInstance(); // Getting Observer1 instance
                    Watchdog watchdog = Watchdog.getInstance(); // Getting Watchdog instance
                    watchdog.addObserver(observer1); // Add new observer obj to the Watchdog obj
                    System.out.println("\nYou will be notified of the result in a week.");
                    watchdog.schedule(); // Execute scheduled notifications
                    continue;
                }
                else if(ticketMenuChoice == 5)
                {
                    if(Observer1.checkSubscription()) // Checking if user is a subscriber
                    {
                    Watchdog watchdog = Watchdog.getInstance(); // Calling the Watchdog instance
                    watchdog.stop(); // Tell the timer to to stop
                    System.out.println("You are unsubscribed to the weekly notification");
                    Thread.sleep(2000);
                    continue;}
                    else
                    {
                        System.out.println("You are not subscribed to the weekly notification");
                        Thread.sleep(2000);
                        continue;
                    }

                }
                else if(ticketMenuChoice == 6)
                {
                    break;
                }
                else if(ticketMenuChoice == 7);
                {
                    System.out.println("Program exits. Have a good day!");
                    System.exit(0);
                }

            }
            catch (InputMismatchException e)
            {
                System.out.println("Wrong input. Please try again!");
            }
            catch (InterruptedException a)
            {
                a.printStackTrace();
            }
        }
    }


}
