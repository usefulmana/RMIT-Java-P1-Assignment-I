package rmit.p1;
import java.io.*;
import java.util.*;
public class CustomerInfo
{
    // Variable Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    private String name;
    private String birthday;
    private String address;
    private String phone;
    private String email;
    private String formatStr = "%-7s %-10s %-12s %-45s %-15s%n";
    private String customerHexaNum;
    private Random rand = new Random();
    private String zeros = "000000";
    private String s = Integer.toString(rand.nextInt(0x1000000),16);
    public void addCustomerInfo(String customerFileName)
    {
        try
        {   s = zeros.substring(s.length()) + s.toUpperCase();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(customerFileName, true));
            bufferedWriter.write(String.format(formatStr, s, name, birthday, address, phone, email));
            bufferedWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Error writing to file '" + customerFileName + "'");
        }
    }
    public void viewCustomerInfo(String customerFileName)
    {
        String line = null;
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(customerFileName));

            while ((line = bufferedReader.readLine()) != null)
            {
                System.out.println(line);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File does not exist.");

        }
        catch(IOException e)
        {
            System.out.println("Error reading customer file.");
        }
    }
    public void deleteCustomerInfo(String customerFileName, String customerHexaNum)
    {
        String line = null;
        try
        {
            File inputFile = new File(customerFileName);
            File tempFile = new File("tempFile.csv");

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile, true));

            BufferedReader bufferedReader = new BufferedReader(new FileReader(customerFileName));

            while ((line = bufferedReader.readLine()) != null)
            {
                String trimmedLine = line.trim();
                if (!trimmedLine.startsWith(customerHexaNum))
                {
                    bufferedWriter.write(trimmedLine + "\n");
                }
            }

            bufferedWriter.close();
            bufferedReader.close();
            // Use garbage collector because for some reasons closing the reader and writer is not enough
            System.gc();
            inputFile.delete();
            tempFile.renameTo(inputFile);

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File does not exist.");
        }
        catch(IOException e)
        {
            System.out.println("Error reading file.");
        }
    }

    public void deleteAllInfo(String customerFileName)
    {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(customerFileName, false),false);
            printWriter.flush();
            printWriter.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File does not exist.");
        }
        catch(IOException e)
        {
            System.out.println("Error reading file.");
        }
    }
    public boolean checkHexadecimalIndex(String customerFileName, String customerHexaNum)
    {
        String line = null;
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(customerFileName));
            // check hexadecimal existence
            int count = 0;
            while ((line = bufferedReader.readLine()) != null)
            {
                String trimmedLine = line.trim();
                List<String> strList = new ArrayList<>();                String[] tempArr = trimmedLine.split(" ");
                for (int i = 0; i < tempArr.length; i++)
                {
                    strList.add(tempArr[i]);

                }
                if (strList.contains(customerHexaNum))
                {
                    count += 1;
                }
                else
                {
                    count = count;
                }
                if (count >= 1)
                {
                    return true;
                }
            }
            bufferedReader.close();

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File does not exist.");
        }
        catch(IOException e)
        {
            System.out.println("Error reading file.");
        }
        return false;
    }
    public boolean checkEmptyFile(String customerFileName)
    {   String line = null;
        try
        {
            BufferedReader readFile = new BufferedReader(new FileReader(customerFileName));
            System.gc();
            if (readFile.readLine() == null)
            {
                return true;
            }

            readFile.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File does not exist.");
        }
        catch(IOException e)
        {
            System.out.println("Error reading file.");
        }
        return false;
    }
    public ArrayList filetoArrayList(String customerFileName)
    {
        ArrayList<String> tempArrayList = new ArrayList<>();
        try{
            String line = null;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(customerFileName));
            while ((line = bufferedReader.readLine()) != null)
            {
                tempArrayList.add(line);
            }
            bufferedReader.close();



        }
        catch(FileNotFoundException e)
        {
            System.out.println("File does not exist.");
        }
        catch(IOException e)
        {
            System.out.println("Error reading file.");
        }
        return tempArrayList;

    }
}
