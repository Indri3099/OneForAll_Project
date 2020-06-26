package exceptions;

public class LockedException extends Exception{
    @Override
    public String getMessage() {
        return "Sembra sia bloccato";
    }
}
