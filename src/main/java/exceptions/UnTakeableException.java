package exceptions;

public class UnTakeableException extends Exception{
    @Override
    public String getMessage() {
        return "Non penso tu possa raccoglierlo";
    }
}
