package rmit.p1.Vendor;

import rmit.p1.CRUD;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

abstract class Vendor extends CRUD {
    /** This class includes all information related to a Vendor object.
     * It also inherits all method from CRUD*/

    private String shopCode;
    private String shopAddress;
    private String shopOwner;
    private String shopPhone;
    private String shopEmail;
    private String shopBalance;



    Vendor(){}

    Vendor(String shopCode, String shopAddress, String shopOwner, String shopPhone, String shopEmail, String shopBalance) {
        this.shopCode = shopCode;
        this.shopAddress = shopAddress;
        this.shopOwner = shopOwner;
        this.shopPhone = shopPhone;
        this.shopEmail = shopEmail;
        this.shopBalance = shopBalance;
    }


    @Override
    public void add(String fileName) {
        /** Adding a vendor's information to a text file*/
        String formatStr = "%-7s %-7s %-60s %-25s %-13s %-25s %-15s%n"; // use this to format the text file
        // This method is used to add info to a text file
        try
        {
            // Turn on true to avoid overwriting
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
            bufferedWriter.write(String.format(formatStr, generateID(), getShopCode(), getShopAddress(), getShopOwner(),
                    getShopPhone(), getShopEmail(), getShopBalance()));
            bufferedWriter.close(); // Close writer
        }
        // Error handling
        catch (IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public String getShopCode() {
        return shopCode;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public String getShopOwner() {
        return shopOwner;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public String getShopEmail() {
        return shopEmail;
    }

    public String getShopBalance() {
        return shopBalance;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public void setShopOwner(String shopOwner) {
        this.shopOwner = shopOwner;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public void setShopEmail(String shopEmail) {
        this.shopEmail = shopEmail;
    }

    public void setShopBalance(String shopBalance) {
        this.shopBalance = shopBalance;
    }
}
