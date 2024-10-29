import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;


public class WordCounter {

    public int processText(StringBuffer text, String stopword) {
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

            try {
                //... regular old code
              } catch(Throwable e) {
                //... code to exectue if an exception was raised in executing the "regular old code"
              }
        }

        if(!found) {            //if the stopword is not found, the InvalidStopwordException is raised
            throw new InvalidStopwordException();
            
            try {
                //... regular old code
              } catch(Throwable e) {
                //... code to exectue if an exception was raised in executing the "regular old code"
              }
        }

        if(count < 5) {     //raises toosmallexception if less than 5
            throw new TooSmallText();
        }

        return count;

    }

    public String processFile(String path) {
        StringBuffer buffer = new StringBuffer();         //string changes pointer to new memory address w/ new string while stringbuffer changes the string in the original memoery address
        boolean file = false; 

        while(!file ){          //if file cannot be opened, reenter file name

        }

        if(path == null) { //raise meptyfileexeption if file is empty
            throw new EmptyFileException();
            
            try {
                //... regular old code
              } catch(TooSmallText e) {
                System.out.println(e); // will print whatever string you passed to the constructor
              }
        }

        return path;
    }

    public static void main(String[] args) {
        //asks the user to choose to process a file with option 1, or text with option 2

        if( answer != 1 || answer != 2) {       //asnwer again if not one of the options

        }

    }
}
