package by.st.effectivebankingservice.exception.handler;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AppError {
    private int status;
    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timestamp;

    public AppError(int status, String message, Date timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
