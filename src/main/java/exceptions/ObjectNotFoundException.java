package exceptions;

public class ObjectNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Non credo che l'oggetto che cerchi si trovi qui";
    }
}
