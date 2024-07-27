package by.st.effectivebankingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotDeletePhoneException extends RuntimeException {
    public CannotDeletePhoneException() {
        super();
    }

    public CannotDeletePhoneException(String message) {
        super(message);
    }

    public CannotDeletePhoneException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotDeletePhoneException(Throwable cause) {
        super(cause);
    }

    protected CannotDeletePhoneException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
