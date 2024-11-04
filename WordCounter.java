import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;


public class WordCounter {

    public static int processText(StringBuffer text, String stopword) throws InvalidStopwordException, TooSmallText {
        //counts the number of words in that text through that stopword. If the stopword is not found the text, the method will raise an InvalidStopwordException
        int count = 0;
        boolean found = false;
        Pattern regex = Pattern.compile("[a-zA-Z0-9']+");
        Matcher regexMatcher = regex.matcher(text);


        
        while (regexMatcher.find()) {
            count++;        //increments word count
            System.out.println("Word found: " + regexMatcher.group());
            
            if (stopword != null && regexMatcher.group().equalsIgnoreCase(stopword)) {          //if the stopword is found, break and change found to true
                found = true;
                break;  
            }

        }

        if (stopword == null) {         //counts all words if stopword is null
            return count;
        }

        if(!found) {            //if the stopword is not found, the InvalidStopwordException is raised
            throw new InvalidStopwordException(stopword);
            
        }

        if(count < 5) {     //raises toosmallexception if less than 5
            throw new TooSmallText(count);
        }

        return count;

    }

    public static StringBuffer processFile(String path) throws EmptyFileException {
        StringBuffer buffer = new StringBuffer(path);         //string changes pointer to new memory address w/ new string while stringbuffer changes the string in the original memoery address
        boolean file = false; 

        while(!file ){          //if file cannot be opened, reenter file name

        }

        if(path == null) { //raise meptyfileexeption if file is empty
            throw new EmptyFileException(buffer);
        }

        return buffer;
    }

    public static void main(String[] args) {
        //asks the user to choose to process a file with option 1, or text with option 
        Scanner scanner = new Scanner(System.in);
        WordCounter counter = new WordCounter();

        int choice = 0;
        while (choice != 1 && choice != 2) {             //asnwer again if not one of the options
            System.out.println("Choose an option: \n1. Process a file \n2. Process text");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice, please enter 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }

        //geting a file path or text from the command line arguments
        String textOrPath = args.length > 0 ? args[0] : "";
        String stopword = args.length > 1 ? args[1] : null;
        int wordCount = 0;

        
        if (choice == 1) {  //depends on users choice 
            try {
                StringBuffer fileContent = counter.processFile(textOrPath);
                //StringBuffer fileBuffer = new StringBuffer(fileContent);
                wordCount = counter.processText(fileContent, stopword);
            } catch (EmptyFileException e) {
                System.out.println(e.getMessage());
                try {
                    wordCount = counter.processText(new StringBuffer(""), stopword);
                } catch (InvalidStopwordException b) {
                    System.out.println(b.getMessage());
                } catch (TooSmallText tooSmallEx) {
                    System.out.println(tooSmallEx.getMessage());
                }
            } catch (InvalidStopwordException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter a valid stopword:");
                stopword = scanner.nextLine();
                try {
                    wordCount = counter.processText(new StringBuffer(textOrPath), stopword);
                } catch (InvalidStopwordException retryException) {
                    System.out.println("Stopword not found after retry.");
                } catch (TooSmallText tooSmallEx) {
                    System.out.println(tooSmallEx.getMessage());
                }
            } catch (TooSmallText e) {
                System.out.println(e.getMessage());
            }
        } else {  
            try {
                wordCount = counter.processText(new StringBuffer(textOrPath), stopword);
            } catch (InvalidStopwordException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter a valid stopword:");
                stopword = scanner.nextLine();
                try {
                    wordCount = counter.processText(new StringBuffer(textOrPath), stopword);
                } catch (InvalidStopwordException retryException) {
                    System.out.println("Stopword not found after retry.");
                } catch (TooSmallText tooSmallEx) {
                    System.out.println(tooSmallEx.getMessage());
                }
            } catch (TooSmallText e) {
                System.out.println(e.getMessage());
            }
        }

        //prints the final word count if valid
        if (wordCount >= 5) {
            System.out.println("Word count: " + wordCount);
        }

        scanner.close();
    }
}
