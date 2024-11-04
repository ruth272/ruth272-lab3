public class EmptyFileException extends Exception {
    
    public EmptyFileException(StringBuffer path) {
        super(path + " was empty.");
    }

}
