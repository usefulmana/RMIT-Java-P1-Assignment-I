package rmit.p1;

import java.io.*;
import java.util.Random;

public class ShopInfo
{
    // ShopInfo class and method
    // Setters
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

    // Var declaration
    private String shopCode;
    private String shopAddress;
    private String shopOwner;
    private String shopPhone;
    private String shopEmail;
    private String shopBalance;
    private String shopFileName;
    private String formatStr = "%-7s %-7s %-45s %-22s %-15s %-15s %-15s%n"; // use this to format the text file

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
