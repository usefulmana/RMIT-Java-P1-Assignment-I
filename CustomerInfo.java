package rmit.p1;
import java.io.*;
import java.util.*;

public class CustomerInfo
{
    //CustomerInfo Class and methods

    //Setters
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

    //Var declaration
    private String name;
    private String birthday;
    private String address;
    private String phone;
    private String email;
    private String formatStr = "%-7s %-22s %-12s %-45s %-15s %-15s%n"; // Use this to format text file
    private String customerHexaNum;

    // Use these variables below to generate random hexadecimal numbers for identification purposes
    private Random rand = new Random();
    private String zeros = "000000";
    private String s = Integer.toString(rand.nextInt(0x1000000),16);

    public void addCustomerInfo(String customerFileName)
    {   // This method is used to add info to a text file
        try
        {   s = zeros.substring(s.length()) + s.toUpperCase(); // number generated and formatted
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(customerFileName, true));
            bufferedWriter.write(String.format(formatStr, s, name, birthday, address, phone, email));
            bufferedWriter.close(); // close writer
        }
        // Error handling
        catch (IOException e)
        {
            System.out.println("Error writing to file '" + customerFileName + "'");
        }
    }
    public void viewCustomerInfo(String customerFileName)
    {   // This method is used to view info in a text file
        String line = null;
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(customerFileName));

            while ((line = bufferedReader.readLine()) != null)
            {
                System.out.println(line); // Read line by line
            }
            bufferedReader.close(); // close reader
        }

        // Error handling
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
    {   // This method is used to delete a single line of info in a text file using a hexadecimal number to identify target
        String line = null;
        try
        {   // Open current file and create a temp file
            File inputFile = new File(customerFileName);
            File tempFile = new File("tempFile.csv");

            // Import reader and writer
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile, true));

            BufferedReader bufferedReader = new BufferedReader(new FileReader(customerFileName));

            while ((line = bufferedReader.readLine()) != null)
            {
                String trimmedLine = line.trim(); // trim whitespaces
                if (!trimmedLine.startsWith(customerHexaNum))
                {// add line without the target hexadecimal number to the temp text file
                    bufferedWriter.write(trimmedLine + "\n");
                }
            }
            //Close reader and writer
            bufferedWriter.close();
            bufferedReader.close();
            // Use garbage collector because for some reasons closing the reader and writer is not enough
            System.gc();
            // Delete inputFile
            inputFile.delete();
            // Rename tempFile to inputFile
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
    {// This method is used to delete all info in a text file
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(customerFileName, false),false);
            printWriter.flush(); // flushing on content
            printWriter.close(); // close writer
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
    {   // This method is used to check if a file is empty
        // Created this method to avoid infinite loop when a user deletes all info line by line
        String line = null;
        try
        {
            BufferedReader readFile = new BufferedReader(new FileReader(customerFileName));
            System.gc(); // flushing the systen
            if (readFile.readLine() == null) // if first line is empty text file is empty
            {
                return true; // file is empty
            }
            // close reader
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
        return false; // file is not empty
    }
}
