public class InvalidStopwordException extends Exception {
    
    public InvalidStopwordException() {
        super("Stopword is not found in file.");
    }

}
