package nicolasps.com.github.exceptions;

public class EmptyListException extends RuntimeException {
    public EmptyListException() {
        super("These list is empty!");
    }
}
