package by.st.effectivebankingservice.exception.handler;

import by.st.effectivebankingservice.exception.*;
import by.st.effectivebankingservice.utils.DataTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {
    private final DataTimeUtil dataTimeUtil;
    private static final String ERROR_OCCURRED_IN_METHOD = "Error occurred in method: ";
    private static final String IN_FILE_NAME = " in file name: ";

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<Object> handleInsufficientFundsException(InsufficientFundsException e) {
        String errorDetails = "Error occurred in method: " + e.getStackTrace()[0].getMethodName()
                + " in file: " + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(), errorDetails);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CannotDeletePhoneException.class)
    public ResponseEntity<Object> handleCannotDeletePhoneException(CannotDeletePhoneException e) {
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(), errorDetails);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PhoneExistException.class)
    public ResponseEntity<Object> handlePhoneExistExceptionException(PhoneExistException e) {
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(), errorDetails);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PhoneNotFoundException.class)
    public ResponseEntity<Object> handlePhoneNotFoundException(PhoneNotFoundException e) {
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(), errorDetails);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CannotDeleteEmailException.class)
    public ResponseEntity<Object> handleCannotDeleteEmailException(CannotDeleteEmailException e) {
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(), errorDetails);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<Object> handleEmailNotFoundException(EmailNotFoundException e) {
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(), errorDetails);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<Object> handleEmailExistException(EmailExistException e) {
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(), errorDetails);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<Object> handleUserExistException(UserExistException e) {
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(), errorDetails);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(), errorDetails);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}