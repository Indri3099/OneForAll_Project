package exceptions;

public class OutOfMapException extends Exception {

    @Override
    public String getMessage() {
        return "E stai un po' attento! Se continui così quel poco che hai studiato te lo dimenticherai!";
    }
}
