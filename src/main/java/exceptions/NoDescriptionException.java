package exceptions;

public class NoDescriptionException extends Exception{
    @Override
    public String getMessage() {
        return "Mmmh non sembra nulla di che";
    }
}
