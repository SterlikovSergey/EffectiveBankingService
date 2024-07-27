package by.st.effectivebankingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotDeleteEmailException extends RuntimeException {
    public CannotDeleteEmailException() {
        super();
    }

    public CannotDeleteEmailException(String message) {
        super(message);
    }

    public CannotDeleteEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotDeleteEmailException(Throwable cause) {
        super(cause);
    }

    protected CannotDeleteEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
