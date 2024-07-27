package by.st.effectivebankingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PhoneExistException extends RuntimeException {
    public PhoneExistException() {
        super();
    }

    public PhoneExistException(String message) {
        super(message);
    }

    public PhoneExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneExistException(Throwable cause) {
        super(cause);
    }

    protected PhoneExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
