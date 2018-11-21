package rmit.p1;

import java.io.*;
import java.util.Random;

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
    private String formatStr = "%-7s %-7s %-45s %-15s %-15s %-20s%n";
    private Random rand = new Random();
    private String zeros = "000000";
    private String s = Integer.toString(rand.nextInt(0x1000000),16);

    public void addShopInfo(String shopFileName)
    {
        try
        {   s = zeros.substring(s.length()) + s.toUpperCase();
            FileWriter fileWriter = new FileWriter(shopFileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.format(formatStr, s, shopCode, shopAddress, shopOwner, shopPhone, shopEmail, shopBalance));
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
            fileReader.close();
            bufferedReader.close();
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
    public void deleteShopInfo(String shopFileName, String shopFilename)
    {
        String line = null;
        try
        {
            File inputFile = new File(shopFileName);
            File tempFile = new File("tempShop.csv");

            FileWriter fileWriter = new FileWriter(tempFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            FileReader fileReader = new FileReader(shopFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null)
            {
                String trimmedLine = line.trim();

                if (!trimmedLine.startsWith(shopFilename))
                {
                    bufferedWriter.write(trimmedLine + "\n");
                }

            }

            bufferedWriter.close();
            bufferedReader.close();
            boolean success = tempFile.renameTo(inputFile);
            System.out.println(success);


        }
        catch(FileNotFoundException e)
        {
            System.out.println("Customer file does not exist.");
        }
        catch(IOException e)
        {
            System.out.println("Error reading customer file.");
        }
    }
}
