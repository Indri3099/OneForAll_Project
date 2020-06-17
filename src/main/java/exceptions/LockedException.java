package exceptions;

public class LockedException extends Exception{
    @Override
    public String getMessage() {
        return "Mi spiace, ma pare proprio che non sia possibile entrare qui dentro";
    }
}
