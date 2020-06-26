package exceptions;

public class NotAContainerException extends Exception{
    @Override
    public String getMessage() {
        return "Questo non è un oggetto contenitore!";
    }
}
