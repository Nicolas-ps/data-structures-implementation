package nicolasps.com.github.exceptions;

public class FullListException extends RuntimeException {
    public FullListException() {
        super("These list is full!");
    }
}
