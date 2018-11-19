package rmit.p1;
import java.io.*;
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
    private String customerFileName;
    private String formatStr = "%-5s %-12s %-15s %-45s %-15s %-15s%n";
    public void addCustomerInfo(String customerFileName)
    {
        try
        {
            FileWriter fileWriter = new FileWriter(customerFileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.format(formatStr, name, birthday, address, phone, email, "\n"));;
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
            FileReader fileReader = new FileReader(customerFileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null)
            {
                System.out.println(line);
            }
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
