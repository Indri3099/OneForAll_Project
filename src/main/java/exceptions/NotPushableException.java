package exceptions;

public class NotPushableException extends Exception{
    @Override
    public String getMessage() {
        return "Non puoi premerlo";
    }
}
