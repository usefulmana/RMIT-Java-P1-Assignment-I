package rmit.p1.Customer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerFile extends Customer
{
    /** This class contains all the necessary method for CRUD operations in the customer's text file
     * This class inherits all methods from Customer class*/
    private String customerFileName;
    private static CustomerFile customerFile; // create a default CustomerFile object

    private CustomerFile(){} // private Constructor to prevent additional object creation

    public static CustomerFile getInstance() // obtain the current instance of this object
    {
        if (customerFile ==  null)
        {
            customerFile = new CustomerFile(); // if no object exists, create a new one
        }
        return customerFile;
    }

    public void addCustomer()
    {/** This method will ask user for information and write to a text file*/
        boolean loopBreaker =  true;
        while(loopBreaker) // loop to prompt user if they want to add more
        {
            try
            {
                // Gathering user inputs
                Scanner inAddCusInfo = new Scanner(System.in);
                System.out.print("Customer's name: ");
                String customerName = inAddCusInfo.nextLine();
                System.out.print("Customer's birthday (DD/MM/YY or DD/MM/YYYY): ");
                String customerBirthday = inAddCusInfo.nextLine();
                if (!isValidBirthday(customerBirthday)) // check if birthday format is valid
                {
                    System.out.println("Invalid birthday format! Either (DD/MM/YY) or (DD/MM/YYYY)");
                    System.out.println("Returning to customer menu...");
                    Thread.sleep(2000);
                    break;
                }
                System.out.print("Customer's address: ");
                String customerAddress = inAddCusInfo.nextLine();
                System.out.print("Customer's phone (10 digits, ex: 0123456789): ");
                String customerPhone = inAddCusInfo.nextLine();
                if(!isValidPhoneNumber(customerPhone)) // check if phone number format is valid
                {
                    System.out.println("Invalid phone number format! Please enter a 10-digit phone number");
                    System.out.println("Returning to customer menu...");
                    Thread.sleep(2000);
                    break;
                }
                System.out.print("Customer's email (Ex: vietlot@gmail.com): ");
                String customerEmail = inAddCusInfo.nextLine();
                if (!isValidEmail(customerEmail)) // check if an email is valid
                {
                    System.out.println("Invalid email format! x@x.x or x@x.x.x");
                    System.out.println("Returning to customer menu...");
                    Thread.sleep(2000);
                    break;
                }

                // Importing the CustomerFile class
                CustomerFile customer = CustomerFile.getInstance(); // get the current instance of CustomerFile

                // Set all properties of a customer
                setCustomerName(customerName);
                setCustomerAddress(customerAddress);
                setCustomerBirthday(customerBirthday);
                setCustomerPhone(customerPhone);
                setCustomerEmail(customerEmail);

                customer.add(customerFileName); // add info the text file
                System.out.println("Added!");

                // Return to main menu or keep adding prompt
                loopBreaker = confirmationPrompt("Continue adding (Y/N)? "); // Confirmation prompt
            }

            // Error Handling
            catch (InputMismatchException | InterruptedException e)
            {
                System.out.println(e.getStackTrace());
            }
        }
    }

    public void viewCustomerFile()
    {
        /** This method will display the customer's text file*/
        CustomerFile customer = CustomerFile.getInstance(); // get current instance of CustomerFile
        try
        {
            if (customer.isEmpty(customerFileName)) // Check if file is empty
            {
                System.out.println("File is empty. Returning to main menu...");
                Thread.sleep(3000);
            }
            else
            {
                customer.view(customerFileName); // display the contents of the file
                while (true) // Loop here to handle wrong input
                {
                    if(confirmationPrompt("Return to customer menu (Y/N)? "))
                    {
                        break;
                    }
                }
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteCustomer()
    {   /** This method will offer options to delete a line or delete all*/
        while(true)
        {
            try
            {
                CustomerFile customer = CustomerFile.getInstance(); // get current instance of CustomerFile;
                if (customer.isEmpty(customerFileName)) // check is file empty
                {
                    System.out.println("File is empty. Returning to customer menu...");
                    Thread.sleep(2000);
                    break;
                }
                else
                {
                    // Display delete menu
                    System.out.print("**************************\n" +
                            "Please choose an option\n" +
                            "1. Delete all customer information\n" +
                            "2. Delete a specific customer\n" +
                            "3. Return to customer menu\n" +
                            "4. Quit program\n" +
                            "Your choice: ");
                    Scanner inDeleteCus = new Scanner(System.in);
                    int inDeleteCusChoice = inDeleteCus.nextInt();
                    if (inDeleteCusChoice == 1)
                    {
                        if(confirmationPrompt("Are your sure you want to delete everything (Y/N)? "))
                        {
                        customer.deleteAll(customerFileName); // call the deleteAll method in CRUD class
                        }
                        else
                        {
                            System.out.println("Returning to customer menu...");
                            break;
                        }
                    }
                    else if (inDeleteCusChoice == 2)
                    {
                        customer.delete(customerFileName);
                    }
                    else if (inDeleteCusChoice == 3)
                    {
                        break;
                    }
                    else if (inDeleteCusChoice == 4)
                    {
                        exit();
                    }
                    else
                    {
                        System.out.println("Invalid input. Please try again.");
                    }
                }

            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Please try again.");
            }
            catch (InterruptedException a)
            {
                System.out.println("Error: " + a.getMessage());
            }
        }
    }

    public void editCustomerInfo() {
        /**
         * This method will find replace a specific information in the text file or replace a line with a new line*/

        boolean loopBreaker = true;
        while (loopBreaker) // Loop here to return to customer menu
        {
            try
            {
                if(isEmpty(customerFileName)) // Check if file is empty
                {
                    System.out.println("File is empty. Returning to customer menu...");
                    Thread.sleep(2000);
                    break;
                }
                else
                {
                    view(customerFileName); // display the contents of the text file
                    System.out.print("Enter the left-most 5-digit hexadecimal number associated with your target: ");
                    Scanner inEditCustomer = new Scanner(System.in);
                    String customerHexaIndex = inEditCustomer.nextLine().toUpperCase();
                    if (exist(customerFileName,customerHexaIndex)) // Check if the line exist
                    {
                        boolean innerLoopBreaker = true;
                        while(innerLoopBreaker) // loop here to return edit menu
                        {
                            System.out.print("Please choose an option to edit\n" +
                                    "1. Edit customer's name\n" +
                                    "2. Edit customer's birthday\n" +
                                    "3. Edit customer's address\n" +
                                    "4. Edit customer's phone number\n" +
                                    "5. Edit customer's email address\n" +
                                    "6. Replace all \n" +
                                    "7. Return to customer menu\n" +
                                    "8. Quit program\n" +
                                    "Your choice: ");

                            int editCustomerChoice = inEditCustomer.nextInt();
                            if (editCustomerChoice == 1)
                            {
                                Scanner inEditCustomer1 = new Scanner(System.in);
                                System.out.print("Enter old name: ");
                                String oldCustomerName = inEditCustomer1.nextLine(); // prompt user to enter old name
                                if (exist(customerFileName, toTitleCase(oldCustomerName))) // check if old name exist
                                {
                                    System.out.print("Enter new name: ");
                                    String newCustomerName = inEditCustomer1.nextLine().trim(); // enter new name
                                    edit(customerFileName, customerHexaIndex, oldCustomerName, toTitleCase(newCustomerName)); // replace
                                    view(customerFileName); // display again
                                    System.out.println("Changes saved!");
                                    innerLoopBreaker = confirmationPrompt("Continue editing (Y/N)? ");
                                    loopBreaker = innerLoopBreaker; // break loop if true;
                                }
                                else
                                {
                                    innerLoopBreaker = confirmationPrompt("Input does not exist! Please check the table again\n"
                                            + "Continue (Y/N)? ");
                                    loopBreaker = innerLoopBreaker;
                                }
                            }
                            else if (editCustomerChoice == 2)
                            {
                                while(true)
                                {
                                    Scanner inEditCustomer2 = new Scanner(System.in);
                                    System.out.print("Enter old birthday (DD/MM/YYYY or DD/MM/YY): ");
                                    String oldCustomerBirthday = inEditCustomer2.nextLine().trim();
                                    if (!isValidBirthday(oldCustomerBirthday))
                                    {
                                        System.out.println("Invalid format. DD/MM/YYYY or DD/MM/YY ");
                                        System.out.println("Please try again!");
                                    }
                                    else {
                                        if (exist(customerFileName, oldCustomerBirthday)) {
                                            while (true) {
                                                System.out.print("Enter new birthday(DD/MM/YYYY or DD/MM/YY) ");
                                                String newCustomerBirthday = inEditCustomer2.nextLine();
                                                if (isValidBirthday(newCustomerBirthday)) {
                                                    edit(customerFileName, customerHexaIndex, oldCustomerBirthday, newCustomerBirthday);
                                                    break;
                                                } else {
                                                    System.out.println("Invalid birthday format. DD/MM/YYYY or DD/MM/YY");
                                                }
                                            }
                                            break;

                                        } else {
                                            innerLoopBreaker = confirmationPrompt("Input does not exist! Please check the table again\n"
                                                    + "Continue (Y/N)? ");
                                            loopBreaker = innerLoopBreaker;
                                        }
                                    }
                                }
                                view(customerFileName);
                                System.out.println("Changes saved!");
                                innerLoopBreaker = confirmationPrompt("Continue editing (Y/N)? ");
                                loopBreaker = innerLoopBreaker;
                            }
                            else if (editCustomerChoice == 3)
                            {
                                Scanner inEditCustomer3 = new Scanner(System.in);
                                System.out.print("Enter old address: ");
                                String oldCustomerAddress = inEditCustomer3.nextLine().trim();
                                if(exist(customerFileName,oldCustomerAddress))
                                {
                                    System.out.print("Enter new address: ");
                                    String newCustomerAddress = inEditCustomer3.nextLine().trim();
                                    edit(customerFileName,customerHexaIndex,oldCustomerAddress,newCustomerAddress);
                                    view(customerFileName);
                                    System.out.println("Changes saved!");
                                    innerLoopBreaker = confirmationPrompt("Continue editing (Y/N)? ");
                                    loopBreaker = innerLoopBreaker;
                                }
                                else
                                {
                                    innerLoopBreaker = confirmationPrompt("Input does not exist! Please check the table again\n"
                                            + "Continue (Y/N)? ");
                                    loopBreaker = innerLoopBreaker;
                                }
                            }
                            else if (editCustomerChoice == 4)
                            {
                                while (true)
                                {
                                    Scanner inEditCustomer4 = new Scanner(System.in);
                                    System.out.print("Enter old phone number: ");
                                    String oldCustomerPhone = inEditCustomer4.nextLine().trim();
                                    if (!isValidPhoneNumber(oldCustomerPhone))
                                    {
                                        System.out.println("Invalid phone number format. 10-digit number only");
                                        System.out.println("Please try again!");
                                    }
                                    else
                                    {
                                        if(exist(customerFileName,oldCustomerPhone))
                                        {
                                            while (true)
                                            {
                                                System.out.print("Enter new phone number: ");
                                                String newCustomerPhone = inEditCustomer4.nextLine().trim();
                                                if(isValidPhoneNumber(newCustomerPhone))
                                                {
                                                    edit(customerFileName,customerHexaIndex,oldCustomerPhone,newCustomerPhone);
                                                    break;
                                                }
                                                else
                                                {
                                                    System.out.println("Invalid phone number format. 10 integer digits only");
                                                }
                                            }
                                            break;

                                        }
                                        else
                                        {
                                            innerLoopBreaker = confirmationPrompt("Input does not exist! Please check the table again\n"
                                                    + "Continue (Y/N)? ");
                                            loopBreaker = innerLoopBreaker;
                                        }
                                    }
                                }
                                view(customerFileName);
                                System.out.println("Changes saved!");
                                innerLoopBreaker = confirmationPrompt("Continue editing (Y/N)? ");
                                loopBreaker = innerLoopBreaker;
                            }
                            else if (editCustomerChoice == 5)
                            {
                                while (true)
                                {
                                    Scanner inEditCustomer5 = new Scanner(System.in);
                                    System.out.print("Enter old email address: ");
                                    String oldCustomerEmail = inEditCustomer5.nextLine().trim();
                                    if(!isValidEmail(oldCustomerEmail))
                                    {
                                        System.out.println("Invalid email format. x@x.x or x@x.x.x");
                                        System.out.println("Please try again!");
                                    }
                                    else
                                    {
                                    if(exist(customerFileName,oldCustomerEmail))
                                        {
                                            while (true) {
                                                System.out.print("Enter new email address: ");
                                                String newCustomerEmailAddress = inEditCustomer5.nextLine().trim();
                                                if(isValidEmail(newCustomerEmailAddress))
                                                {
                                                    edit(customerFileName, customerHexaIndex, oldCustomerEmail, newCustomerEmailAddress);
                                                    break;
                                                }
                                                else
                                                {
                                                    System.out.println("Invalid email format. x@x.x or x@x.x.x");
                                                }
                                            }
                                            break;

                                        }
                                        else
                                        {
                                            innerLoopBreaker = confirmationPrompt("Input does not exist! Please check the table again\n"
                                                    + "Continue (Y/N)? ");
                                            loopBreaker = innerLoopBreaker;
                                        }
                                    }
                                }
                                view(customerFileName);
                                System.out.println("Changes saved!");
                                innerLoopBreaker = confirmationPrompt("Continue editing (Y/N)? ");
                                loopBreaker = innerLoopBreaker;
                            }
                            else if (editCustomerChoice == 6)
                            {
                                System.out.println("Delete old information");
                                deleteCustomer();
                                System.out.println("Replace");
                                addCustomer();
                                view(customerFileName);
                                System.out.println("Changes saved!");
                                innerLoopBreaker = confirmationPrompt("Continue editing (Y/N)? ");
                                loopBreaker = innerLoopBreaker;
                            }
                            else if (editCustomerChoice == 7)
                            {
                                innerLoopBreaker = false;
                                loopBreaker = false;
                            }
                            else if (editCustomerChoice == 8)
                            {
                                exit();
                            }
                            else
                            {
                                System.out.println("Invalid input. Please try again!");
                            }
                        }
                    }
                    else
                    {
                        System.out.println("No such number exist!");
                        loopBreaker = confirmationPrompt("Continue (Y/N)? ");
                    }

                }
            }
            catch (InputMismatchException| InterruptedException e)
            {
                System.out.println("Invalid input. Please try again!");
            }
        }
    }

    public String getCustomerFileName() {
        return customerFileName;
    }

    public void setCustomerFileName(String customerFileName) {
        this.customerFileName = customerFileName;
    }

    public CustomerFileMemento saveCustomerFileName()
    {
        return new CustomerFileMemento(customerFileName); // saving the current filename
    }

    public void getSavedCustomerFileName(CustomerFileMemento customerFileMemento)
    {
        customerFileName = customerFileMemento.getSavedCustomerFileName(); // retrieve previous saved state
    }


}
