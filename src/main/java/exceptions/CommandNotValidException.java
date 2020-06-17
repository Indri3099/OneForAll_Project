package exceptions;

public class CommandNotValidException extends Exception{
    @Override
    public String getMessage() {
        return "Mmmh...probabilmente hai bevuto un po' troppo, non capisco che dici";
    }
}
