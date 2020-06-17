package exceptions;

public class FullInventoryException extends Exception{
    @Override
    public String getMessage() {
        return "Il tuo inventario è pieno!";
    }
}
