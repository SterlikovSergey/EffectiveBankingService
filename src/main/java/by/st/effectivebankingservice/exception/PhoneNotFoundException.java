package by.st.effectivebankingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PhoneNotFoundException extends RuntimeException{
    public PhoneNotFoundException() {
        super();
    }

    public PhoneNotFoundException(String message) {
        super(message);
    }

    public PhoneNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PhoneNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
