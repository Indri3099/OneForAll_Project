package exceptions;

public class ObjectOwnedException extends Exception{
    @Override
    public String getMessage() {
        return "Hai già questo oggetto nell'inventario";
    }
}
