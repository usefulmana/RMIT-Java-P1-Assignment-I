package rmit.p1.Customer;

import rmit.p1.CRUD;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Customer extends CRUD {
/**
 * This class store all information related to a customer. This class inherits all methods from CRUD class
 */
    private String customerName;
    private String customerBirthday;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;


    Customer(){} // empty constructor

    Customer(String customerName, String customerBirthday, String customerAddress, String customerPhone, String customerEmail) {
        this.customerName = customerName;
        this.customerBirthday = customerBirthday;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }

    @Override
    protected void add(String fileName) {
        /**
         * This method will add a customer information to a text file
         * @param: fileName - target file name*/
        String formatStr = "%-7s %-25s %-12s %-60s %-13s %-15s%n"; // Use this to format text file
        try
        {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true)); // append is true to prevent overwritting old information
            bufferedWriter.write(String.format(formatStr, generateID(), customerName, customerBirthday,
                    customerAddress, customerPhone, customerEmail)); // write the formatted String
            bufferedWriter.close(); // close writer
        }
        // Error handling
        catch (IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(String customerBirthday) {
        this.customerBirthday = customerBirthday;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

}
