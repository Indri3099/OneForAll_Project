package exceptions;

public class NoLightException extends Exception {
    @Override
    public String getMessage() {
        return "è buio pesto , non si vede nulla";
    }
}
