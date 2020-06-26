package exceptions;

public class NoSoundException extends Exception{
    @Override
    public String getMessage() {
        return "Non penso si possa suonare";
    }
}
