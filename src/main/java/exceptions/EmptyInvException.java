package exceptions;

public class EmptyInvException extends Exception{
    @Override
    public String getMessage() {
        return "Questo inventario è proprio come il tuo portafoglio!";
    }
}
