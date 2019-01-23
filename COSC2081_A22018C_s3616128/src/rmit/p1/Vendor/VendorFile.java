package rmit.p1.Vendor;


import java.util.InputMismatchException;
import java.util.Scanner;

public class VendorFile extends Vendor
{
    /** This class contains all the necessary method for CRUD operations in the vendor's text file
     * This class inherits all methods from Vendor class*/
    private String vendorFileName;
    private static VendorFile vendorFile; // create a default VendorFile object

    private VendorFile() {} // private Constructor to prevent additional object creation

    public static VendorFile getInstance() // obtain the current instance of this object
    {
        if(vendorFile == null)
        {
            vendorFile = new VendorFile(); // if no object exists, create a new one
        }
        return vendorFile;
    }


    public void addVendor()
    {  /** This method will ask user for information and write to a text file*/
        boolean loopBreaker = true;
        while(loopBreaker)
        {
            try
            {
                // Gathering user inputs
                Scanner inAddVendor = new Scanner(System.in);
                System.out.print("Shop's code: ");
                String shopCode = inAddVendor.nextLine().trim();
                System.out.print("Shop's address: ");
                String shopAddress = inAddVendor.nextLine().trim();
                System.out.print("Shop owner: ");
                String shopOwner = inAddVendor.nextLine().trim();
                System.out.print("Shop's phone (10 digits, ex:0123456789): ");
                String shopPhone = inAddVendor.nextLine().trim();
                if (!isValidPhoneNumber(shopPhone))
                {
                    System.out.println("Invalid phone number format! Please enter a 10-digit phone number");
                    System.out.println("Returning to vendor menu...");
                    Thread.sleep(2000);
                    break;
                }
                System.out.print("Shop's email (Ex: vendor1@vietlot.com): ");
                String shopEmail = inAddVendor.nextLine().trim();
                if (!isValidEmail(shopEmail))
                {
                    System.out.println("Invalid email format! x@x.x or x@x.x.x");
                    System.out.println("Returning to vendor menu...");
                    Thread.sleep(2000);
                    break;
                }
                System.out.print("Shop's deposit: ");
                double shopDeposit = inAddVendor.nextDouble();
                System.out.print("Shop's money issuing lottery: ");
                double shopMoneyIssuingLot = inAddVendor.nextDouble();

                // Account balance = Deposit - money issuing lottery. Round up to third decimal place
                String shopBalance = String.format("%.3f",shopDeposit - shopMoneyIssuingLot);


                // Import the VendorFile class
                VendorFile vendor = VendorFile.getInstance();

                setShopCode(shopCode);
                setShopAddress(shopAddress);
                setShopOwner(shopOwner);
                setShopPhone(shopPhone);
                setShopEmail(shopEmail);
                setShopBalance(shopBalance);

                // Using the Shopinfo class' addShopInfo method to add info to a text file
                vendor.add(vendorFileName);
                System.out.println("Added!");

                // Return to main menu or keep adding prompt
                loopBreaker = confirmationPrompt("Continue adding (Y/N)? ");
            }
            catch (InputMismatchException|InterruptedException e)
            {
                System.out.println("Invalid input! Please enter a numbered value (ex: 12.5) for monetary values");
                System.out.println("Please try again!");
            }
        }
    }

    public void viewVendorFile()
    {
        /** This method will display the Vendor's text file*/
        VendorFile vendor = VendorFile.getInstance();
        try
        {
            if (vendor.isEmpty(vendorFileName)) // check if file is empty
            {
                System.out.println("File is empty. Returning to main menu...");
                Thread.sleep(3000);
            }
            else
            {
                view(vendorFileName);
                while (true) // Loop here to handle wrong input
                {
                    if(confirmationPrompt("Return to vendor menu (Y/N)? "))
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

    public void deleteVendor()
    {
        /** This method will offer options to delete a line or delete all*/
        while(true)
        {
            try
            {
                VendorFile vendor = VendorFile.getInstance();
                if (vendor.isEmpty(vendorFileName))
                {
                    System.out.println("File is empty. Returning to vendor menu...");
                    Thread.sleep(2000);
                    break;
                }
                else
                {
                    System.out.print("**************************\n" +
                            "Please choose an otion\n" +
                            "1. Delete all vendor information\n" +
                            "2. Delete a specific vendor\n" +
                            "3. Return to vendor menu\n" +
                            "4. Quit program\n" +
                            "Your choice: ");
                    Scanner inDeleteVendor = new Scanner(System.in);
                    int inDeleteVendorChoice = inDeleteVendor.nextInt();
                    if (inDeleteVendorChoice == 1)
                    {
                        inDeleteVendor.nextLine();
                        System.out.print("Are your sure you want to delete everything (Y/N)? ");
                        String confirmation = inDeleteVendor.nextLine();
                        if(confirmation.equalsIgnoreCase("y"))
                        { vendor.deleteAll(vendorFileName);}
                        else
                        {
                            System.out.println("Returning to vendor menu...");
                            break;
                        }
                    }
                    else if (inDeleteVendorChoice == 2)
                    {
                        vendor.delete(vendorFileName);
                    }
                    else if (inDeleteVendorChoice == 3)
                    {
                        break;
                    }
                    else if (inDeleteVendorChoice == 4)
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

    public void editVendorInfo() {

        /**
         * This method will find replace a specific information in the text file or replace a line with a new line*/
        boolean loopBreaker = true;
        while(loopBreaker)
        {
            try
            {
                if (isEmpty(vendorFileName))
                {
                    System.out.println("File is empty. Returning to main menu...");
                    Thread.sleep(2000);
                    break;
                }
                else
                {
                    view(vendorFileName);
                    System.out.print("Enter the left-most 5-digit hexadecimal number associated with your target: ");
                    Scanner inVendorEdit = new Scanner(System.in);
                    String vendorHexaIndex = inVendorEdit.nextLine().toUpperCase().trim();
                    if (exist(vendorFileName,vendorHexaIndex)) {
                        boolean innerLoopBreaker = true;
                        while (innerLoopBreaker) {
                            System.out.print("Please choose an option to edit\n" +
                                    "1. Edit vendor's code\n" +
                                    "2. Edit vendor's address\n" +
                                    "3. Edit vendor owner's name\n" +
                                    "4. Edit vendor's phone number\n" +
                                    "5. Edit vendor's email address\n" +
                                    "6. Edit vendor's account balance\n" +
                                    "7. Replace all\n" +
                                    "8. Return to vendor menu\n" +
                                    "9. Quit program\n" +
                                    "Your choice: ");
                            int editVendorChoice = inVendorEdit.nextInt();
                            if (editVendorChoice == 1) {
                                Scanner inVendorEdit1 = new Scanner(System.in);
                                System.out.print("Enter old code: ");
                                String oldVendorCode = inVendorEdit1.nextLine();
                                System.out.println();
                                if (exist(vendorFileName, oldVendorCode)) {
                                    System.out.print("Enter new code: ");
                                    String newVendorCode = inVendorEdit1.nextLine();
                                    edit(vendorFileName, vendorHexaIndex, oldVendorCode, newVendorCode);
                                    view(vendorFileName);
                                    System.out.println("Changes saved!");
                                    innerLoopBreaker = confirmationPrompt("Continue editing (Y/N)? ");
                                    loopBreaker = innerLoopBreaker;
                                } else {
                                    innerLoopBreaker = confirmationPrompt("Input does not exist! Please check the table again\n"
                                            + "Continue (Y/N)? ");
                                    loopBreaker = innerLoopBreaker;
                                }
                            } else if (editVendorChoice == 2) {
                                Scanner inVendorEdit2 = new Scanner(System.in);
                                System.out.print("Enter old address: ");
                                String oldVendorAddress = inVendorEdit2.nextLine();
                                if(exist(vendorFileName,oldVendorAddress))
                                {
                                    System.out.print("Enter new address: ");
                                    String newVendorAddress = inVendorEdit2.nextLine();
                                    edit(vendorFileName, vendorHexaIndex, oldVendorAddress, newVendorAddress);
                                    view(vendorFileName);
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

                            } else if (editVendorChoice == 3) {
                                Scanner inVendorEdit3 = new Scanner(System.in);
                                System.out.print("Enter old owner's name: ");
                                String oldOwnerName = inVendorEdit3.nextLine();
                                if(exist(vendorFileName,oldOwnerName))
                                {
                                    System.out.print("Enter new owner's name: ");
                                    String newOwnerName = inVendorEdit3.nextLine();
                                    edit(vendorFileName, vendorHexaIndex, oldOwnerName, newOwnerName);
                                    view(vendorFileName);
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
                            } else if (editVendorChoice == 4) {
                                while (true)
                                {
                                    Scanner inVendorEdit4 = new Scanner(System.in);
                                    System.out.print("Enter old vendor phone number: ");
                                    String oldVendorPhone = inVendorEdit4.nextLine().trim();
                                    if (!isValidPhoneNumber(oldVendorPhone))
                                    {
                                        System.out.println("Invalid phone number format. 10-digit number only");
                                        System.out.println("Please try again!");
                                    }
                                    else
                                    {
                                        if(exist(vendorFileName,oldVendorPhone))
                                        {
                                            while (true)
                                            {
                                                System.out.print("Enter new vendor phone number: ");
                                                String newVendorPhone = inVendorEdit4.nextLine().trim();
                                                if(isValidPhoneNumber(newVendorPhone))
                                                {
                                                    edit(vendorFileName,vendorHexaIndex,oldVendorPhone,newVendorPhone);
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
                                view(vendorFileName);
                                System.out.println("Changes saved!");
                                innerLoopBreaker = confirmationPrompt("Continue editing (Y/N)? ");
                                loopBreaker = innerLoopBreaker;
                            }
                            else if (editVendorChoice == 5) {
                                while (true)
                                {
                                    Scanner inVendorEdit5 = new Scanner(System.in);
                                    System.out.print("Enter old vendor email: ");
                                    String oldVendorEmail = inVendorEdit5.nextLine().trim();
                                    if (!isValidEmail(oldVendorEmail))
                                    {
                                        System.out.println("Invalid email format. x@x.x or x@x.x.x");
                                        System.out.println("Please try again!");
                                    }
                                    else
                                    {
                                        if(exist(vendorFileName,oldVendorEmail))
                                        {
                                            while (true)
                                            {
                                                System.out.print("Enter new vendor email: ");
                                                String newVendorEmail = inVendorEdit5.nextLine().trim();
                                                if(isValidEmail(newVendorEmail))
                                                {
                                                    edit(vendorFileName,vendorHexaIndex,oldVendorEmail,newVendorEmail);
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
                                view(vendorFileName);
                                System.out.println("Changes saved!");
                                innerLoopBreaker = confirmationPrompt("Continue editing (Y/N)? ");
                                loopBreaker = innerLoopBreaker;
                            } else if (editVendorChoice == 6) {
                                Scanner inVendorEdit6 = new Scanner(System.in);
                                System.out.print("Enter old balance: ");
                                String oldVendorBalance = inVendorEdit6.nextLine();
                                if (exist(vendorFileName, oldVendorBalance)) {
                                    System.out.print("Enter new balance: ");
                                    String newVendorBalance = inVendorEdit6.nextLine();
                                    edit(vendorFileName, vendorHexaIndex, oldVendorBalance, newVendorBalance);
                                    view(vendorFileName);
                                    System.out.println("Changes saved!");
                                    innerLoopBreaker = confirmationPrompt("Continue editing (Y/N)? ");
                                    loopBreaker = innerLoopBreaker;
                                } else {
                                    innerLoopBreaker = confirmationPrompt("Input does not exist! Please check the table again\n"
                                            + "Continue (Y/N)? ");
                                    loopBreaker = innerLoopBreaker;
                                }
                            }
                            else if (editVendorChoice == 7)
                            {
                                System.out.println("Delete old information");
                                deleteVendor();
                                System.out.println("Replace");
                                addVendor();
                                view(vendorFileName);
                                System.out.println("Changes saved!");
                                innerLoopBreaker = confirmationPrompt("Continue editing (Y/N)? ");
                                loopBreaker = innerLoopBreaker;
                            }
                            else if (editVendorChoice == 8)
                            {
                                innerLoopBreaker = false;
                                loopBreaker = false;
                            }
                            else if (editVendorChoice == 9)
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
            catch (InputMismatchException|InterruptedException ex)
            {
                System.out.println("Invalid value. Please try again!");
            }
        }
    }

    public String getVendorFileName() {
        return vendorFileName;
    }

    public void setVendorFileName(String vendorFileName) {
        this.vendorFileName = vendorFileName;
    }

    public VendorFileMemento saveVendorFileName()
    {
        return new VendorFileMemento(vendorFileName); // saving the current filename
    }

    public void getSavedVendorFileName(VendorFileMemento vendorFileMemento)
    {
        vendorFileName = vendorFileMemento.getVendorFileName(); // retrieve previous saved state
    }
}
