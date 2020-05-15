package exceptions;

public class NameException extends RuntimeException {
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
