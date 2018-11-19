package rmit.p1;

import java.io.*;

public class ShopInfo
{
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

    private String shopCode;
    private String shopAddress;
    private String shopOwner;
    private String shopPhone;
    private String shopEmail;
    private String shopBalance;
    private String shopFileName;
    private String formatStr = "%-7s %-45s %-12s %-15s %-15s %-20s%n";

    public void addShopInfo(String shopFileName)
    {
        try
        {
            FileWriter fileWriter = new FileWriter(shopFileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.format(formatStr, shopCode, shopAddress, shopOwner, shopPhone, shopEmail, shopBalance, "\n"));;
            bufferedWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Error writing to file '" + shopFileName + "'");
        }
    }

    public void viewShopInfo(String shopFileName)
    {
        String line = null;
        try
        {
            FileReader fileReader = new FileReader(shopFileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Shop file does not exist.");
        }
        catch(IOException e)
        {
            System.out.println("Error reading Shop file.");
        }
    }
}
