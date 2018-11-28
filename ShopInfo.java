package rmit.p1;

import java.io.*;
import java.util.Random;

public class ShopInfo
{
    // ShopInfo class and method
    // Setters


    // Var declaration
    private String shopCode;
    private String shopAddress;

    // Constructor
    public ShopInfo(String shopCode, String shopAddress, String shopOwner, String shopPhone, String shopEmail, String shopBalance) {
        this.shopCode = shopCode;
        this.shopAddress = shopAddress;
        this.shopOwner = shopOwner;
        this.shopPhone = shopPhone;
        this.shopEmail = shopEmail;
        this.shopBalance = shopBalance;
    }
    public ShopInfo()
    {}

    private String shopOwner;
    private String shopPhone;
    private String shopEmail;
    private String shopBalance;
    private String shopFileName;
    private String formatStr = "%-7s %-7s %-45s %-22s %-15s %-20s %-15s%n"; // use this to format the text file

    // Use these variables below to generate random hexadecimal numbers for identification purposes
    private Random rand = new Random();
    private String zeros = "000000";
    private String s = Integer.toString(rand.nextInt(0x1000000),16);

    public void addShopInfo(String shopFileName)
    {   // This method is used to add info to a text file
        try
        {   s = zeros.substring(s.length()) + s.toUpperCase(); // number generated and formatted
            // Turn on true to avoid overwriting
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(shopFileName, true));
            bufferedWriter.write(String.format(formatStr, s, shopCode, shopAddress, shopOwner, shopPhone, shopEmail, shopBalance));
            bufferedWriter.close(); // Close writer
        }
        // Error handling
        catch (IOException e)
        {
            System.out.println("Error writing to file '" + shopFileName + "'");
        }
    }

}
