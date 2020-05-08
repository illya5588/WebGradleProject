package exceptions;

public class NameException extends Exception {
    private String title;

    public NameException(String title, String message) {
        super(message);
        this.title = title;
    }

    @Override
    public String getMessage() {
        return title + super.getMessage();
    }


}
