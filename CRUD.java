package rmit.p1;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class CRUD
{
    /**
     * This parent class contains all the common methods that are used in CRUD operation
     * exist(String fileName, String userinput): checking if an user input exists in the text file
     * view(String fileName): view text file
     * delete(String fileName): delete a specific line in a text file
     * deleteAll(String fileName): delete all information in a text file
     * edit(String fileName): edit a specific piece of information in a line in a text file
     * isEmpty(String fileName): check if the text file is empty
     * toTitleCase(String givenString): capitalize the first char of each word
     * confirmationPrompt(String message): displaying a confirmation prompt with custom message
     * exit(): exit program
     * isValidEmail(String email): checking if an email is valid
     * isValidPhone(String phone): checking if a phone number is valid
     * isValidBirthday(String birthDay): checking if a birthday is valid*/
    protected abstract void add(String fileName);

    protected String generateID()
    {   /** Generate a 5-digit hexadecimal number ID
     */
        Random rand = new Random();
        String zeros = "00000"; // Length limiter
        String id = Integer.toString(rand.nextInt(0x100000),16); // generate a random 5-digit hexa number
        id = zeros.substring(id.length()) + id.toUpperCase(); // Uppercase the ID
        return id;
    }

    public boolean exist(String fileName, String userinput)
    {    /** Checking if a user's input is in a line in a text file
     @param: fileName - target file name
     @param: userinput - target string*/
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName)); // Importing a file reader
            String line = null;
            while ((line = bufferedReader.readLine()) != null) // Read through text file line by line
            {
                String trimmedLine = line.trim(); // trimming excessive whitespaces
                if (trimmedLine.contains(userinput)) // check if a line contain the user's input
                {
                    return true;
                }
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
        return false;
    }

    public void view(String fileName)
    {
        /** Read through text file
         * @param: filename - target file name*/
        String line = null;
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName)); // Creating a file reader obf

            while ((line = bufferedReader.readLine()) != null)
            {
                System.out.println(line.trim()); // Print line by line
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

    public void delete(String fileName)
    {
        /**This method is going to fine a specific line and delete it
         * Step I: ask user for unique ID given to a specific line
         * Step II: check if ID exists. If not, users will be prompted to enter the ID again
         * Step III: Create a temp file.
         * Step IV: Write all lines but the target line to the temp file
         * Step V: delete the current file, rename the temp file to the current file
         * @param: filename - target file name*/
        boolean loopBreaker = true; // break loop condition
        while(loopBreaker) // Loop here to delete more lines
        {
            try
            {
                view(fileName); // display the text file
                Scanner inDelete = new Scanner(System.in);
                System.out.print("Enter the left-most 5-digit hexadecimal number associated with your target: ");
                String hexaNumber = inDelete.nextLine().toUpperCase(); // Asking for the unique ID given to a specific line
                if (exist(fileName, hexaNumber)) // Checking if the line with the corresponding ID exists
                {
                    // Open current file and create a temp file
                    File inputFile = new File(fileName);
                    File tempFile = new File("tempFile.csv"); // Create a temp file

                    // Import reader and writer
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile, true));
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
                    String line = null;

                    while ((line = bufferedReader.readLine()) != null) {
                        String trimmedLine = line.trim(); // trim whitespaces
                        if (!trimmedLine.startsWith(hexaNumber)) {// add line without the target hexadecimal number to the temp text file
                            bufferedWriter.write(trimmedLine + "\n");
                        }
                    }
                    System.out.println("Deleted!");
                    //Close reader and writer
                    bufferedWriter.close();
                    bufferedReader.close();
                    // Use garbage collector because for some reasons closing the reader and writer is not enough
                    System.gc();
                    // Delete inputFile
                    inputFile.delete();
                    // Rename tempFile to inputFile
                    tempFile.renameTo(inputFile);
                    if(isEmpty(fileName)) // If file is empty break loop
                    {
                        break;
                    }
                    else {
                        loopBreaker = confirmationPrompt("Continue deleting (Y/N)? "); // Prompt users if they want to delete more
                    }
                }
                else
                {
                    System.out.println("No such number exist. Please try again.");
                }

            }
            // Error Handling
            catch(FileNotFoundException e)
            {
                System.out.println("File does not exist.");
            }
            catch(IOException e)
            {
                System.out.println("Error reading file.");
            }
        }
    }

    public void deleteAll(String fileName)
    {
        /**This method will delete all text in a text file
         * @param: fileName - target file name*/
        try
        {
            PrintWriter printWriter = new PrintWriter(new FileWriter(fileName, false),false);
            printWriter.flush(); // flushing on content
            printWriter.close(); // close writer
            System.out.println("All information is deleted!");
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

    public void edit(String FileName, String lineHexa, String tobeReplaced, String toReplace)
    {   /**This method is used to edit individual information on each line in a text file
     * Find target line
     * Add non-target lines to a temp String
     * Replace a specific info with another*/
        try {
            // Importing reader
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FileName));
            // Declaring variables
            String line = null;
            StringBuilder nonTarget_Top = new StringBuilder();
            StringBuilder nonTarget_Btm = new StringBuilder();
            StringBuilder target = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                if (!line.contains(lineHexa)) // Use the Hexadecimal number to fine the line that is needed
                {
                    nonTarget_Top.append(line + "\n"); // Add non-target lines to a string up until it reaches the target-line to a string
                }
                else if(line.contains(lineHexa))
                {
                    target.append(line + "\n");
                    break;
                }
            }

            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(FileName));
            while ((line = bufferedReader1.readLine()) != null) {
                if (!line.contains(lineHexa) && !nonTarget_Top.toString().contains(line))
                {
                    nonTarget_Btm.append(line + "\n"); // add the rest of the non-target lines on a string
                }
            }

            // Modifying target line
            String newStr = target.toString().replace(tobeReplaced, toReplace); // replace

            // Add the modified target line to the other lines
            String finalStr = nonTarget_Top + newStr + nonTarget_Btm; // add strings back together

            // Create a dummy text file
            File inputFile = new File(FileName);
            File tempFile = new File("tempFile.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile, true));

            // Write
            bufferedWriter.write(finalStr);

            //Close reader and writer
            bufferedWriter.close();
            bufferedReader.close();
            bufferedReader1.close();
            // Use garbage collector because for some reasons closing the reader and writer is not enough
            System.gc();
            // Delete
            inputFile.delete();
            // Rename tempFile to inputFile
            tempFile.renameTo(inputFile);
        }
        catch(IOException e)
        {
            System.out.println("Wrong Input!");
        }

    }
    public boolean isEmpty(String fileName)
    {
        /** This method is going to check if a text file is empty
         * @param: fileName - target file name*/
        String line = null;
        try
        {
            BufferedReader readFile = new BufferedReader(new FileReader(fileName));
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

    protected String toTitleCase(String givenString)
    {/** This method is going to capitalize each word in a given String
     @param: givenString - target String*/
        // Capitalize first letter of each word
        String[] arr = givenString.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    public boolean confirmationPrompt(String message)
    {
        /** This method is going to display all a comfirmation prompt with custom message
         * @param: message - customer message*/
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        String addCusContinue = in.nextLine().trim();
        if (addCusContinue.equalsIgnoreCase("y"))
        {return true;}
        return false;
    }

    public void exit()
    {/** Exit program */
        System.out.println("Program exits. Have a good day!");
        System.exit(0);
    }


    public boolean isValidEmail(String email)
    {/**Check if an email is valid. x@x.x or x@x.x.x only
     @pagram: email - target email*/
        String emailRegEx = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
                "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]" +
                "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]" +
                "(?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]" +
                "|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]" +
                "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if(Pattern.matches(emailRegEx,email))
        {
            return true;
        }
        return false;
    }

    public boolean isValidPhoneNumber(String phoneNumber)
    { /** Check a phone number is valid. 10-digit phone number only
     @pagram: phoneNumber - target number*/
        String phoneRegEx = "^(1?(-?\\d{3})-?)?(\\d{3})(-?\\d{4})$";
        if(Pattern.matches(phoneRegEx,phoneNumber))
        {
            return true;
        }
        return false;
    }

    public boolean isValidBirthday(String birthday)
    {/** Check if a birthday is valid. dd/mm/yyyy or dd/mm/yy
     @pagram: birthday - target birthday*/
        String birthdayRegEx = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)" +
                "(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3" +
                "(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$" +
                "|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        if(Pattern.matches(birthdayRegEx,birthday))
        {
            return true;
        }
        return false;
    }
}
