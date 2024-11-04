import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class WordCounter {

    public static int processText(StringBuffer text, String stopword) throws InvalidStopwordException, TooSmallText {
        //counts the number of words in that text through that stopword. If the stopword is not found the text, the method will raise an InvalidStopwordException
        int count = 0;
        int wordCount = 0;
        boolean found = false;
        Pattern regex = Pattern.compile("[a-zA-Z0-9']+");
        Matcher regexMatcher = regex.matcher(text);

        //counts words until stopword is found
        while (regexMatcher.find()) {
            count++;        //increments word count
            System.out.println("Word found: " + regexMatcher.group());
            if (stopword != null && regexMatcher.group().equals(stopword)) {          //if the stopword is found, break and change found to true
                found = true;  
                break;
            }
        }

        Pattern r = Pattern.compile("[a-zA-Z0-9']+");
        Matcher rm = regex.matcher(text);

        //counts all words
        while(rm.find()) {
            wordCount++;
        }

        if (stopword == null && wordCount > 5) {         //counts all words if stopword is null
            return wordCount;
        }

        if(!found) {            //if the stopword is not found, the InvalidStopwordException is raised
            if(wordCount < 5) {     //raises toosmallexception if less than 5
                throw new TooSmallText(wordCount);
            }
            else{
                throw new InvalidStopwordException(stopword);
            }
            
        }

        if(wordCount < 5) {     //raises toosmallexception if less than 5
            throw new TooSmallText(wordCount);
        }
        if(count < 5) {     //raises toosmallexception if less than 5
            throw new TooSmallText(count);
        }

        return count;

    }

    public static StringBuffer processFile(String path) throws EmptyFileException {
        StringBuffer buffer = new StringBuffer(path);         //string changes pointer to new memory address w/ new string while stringbuffer changes the string in the original memoery address
        boolean file = true; 
        File nfile = new File(path);

        //if file cannot be opened, reenter file name

        try {
        BufferedReader reader = new BufferedReader(new FileReader(nfile));
        String line;
        file = false;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            //reader.close();
        }
        reader.close();
        } 
        
        //catch(EmptyFileException e){
         //   file = true;
         //   System.out.println(e);
        //}
        catch (IOException e) {
            System.out.println("error");
        }
        if (buffer.length() == 0) {
            throw new EmptyFileException(buffer);
        }

        return buffer;
    }

    public static void main(String[] args) {
        //asks the user to choose to process a file with option 1, or text with option 2
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 1 && choice != 2) {             //asnwer again if not one of the options
            System.out.println("Choose an option: \n1. Process a file \n2. Process text");
            choice = Integer.parseInt(scanner.nextLine());
            /*if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice, please enter 1 or 2.");
            }*/
        }

        //geting a file path or text from the command line arguments
        String textOrPath = null;     
        if(args.length > 1){
            textOrPath = args[0];
        }
        String stopword = null;
        if(args.length > 1){
            stopword = args[1];
        }

        int wordCount = 0;

        
        if (choice == 1) {  //depends on users choice 
            try {
                StringBuffer fileContent = processFile(textOrPath);
                wordCount = processText(fileContent, stopword);
            } catch (EmptyFileException e) {
                //System.out.println(e);
                StringBuffer fileContent = new StringBuffer();
                try {
                    //StringBuffer fileContent = processFile(textOrPath);
                    wordCount = processText(fileContent, stopword);
                } catch (InvalidStopwordException b) {
                    System.out.println(fileContent.toString());
                } catch (TooSmallText c) {
                    System.out.println(c.toString());
                }
            } catch (InvalidStopwordException e) {
                System.out.println(e.toString());
            } catch (TooSmallText e) {
                System.out.println(e.toString());
            }
        } else {  
            try {
                wordCount = processText(new StringBuffer(textOrPath), stopword);
            } catch (InvalidStopwordException e) {
                System.out.println(e.toString());
                //System.out.println("Please enter a valid stopword:");
                //stopword = scanner.nextLine();
            } catch (TooSmallText e) {
                System.out.println(e.toString());
            }
        }

        //prints the final word count if valid
        if (wordCount >= 5) {
            System.out.println("Word count: " + wordCount);
        }

        scanner.close();
    }
}
