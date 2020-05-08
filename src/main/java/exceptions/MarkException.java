package exceptions;

public class MarkException extends Exception {
    private int mark;


    public MarkException( int mark, String message) {
        super(message);
        this.mark = mark;

    }

    @Override
    public String getMessage() {
        return  mark + super.getMessage();
    }
}
