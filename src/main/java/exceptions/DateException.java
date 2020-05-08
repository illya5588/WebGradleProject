package exceptions;

import java.time.LocalDate;

public class DateException extends RuntimeException {
    private LocalDate date;

    public DateException(String message) {
        super(message);

    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
