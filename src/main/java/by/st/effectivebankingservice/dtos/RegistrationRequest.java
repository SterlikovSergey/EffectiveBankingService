package by.st.effectivebankingservice.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RegistrationRequest {

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private BigDecimal initialBalance;

    private String phone;

    private String email;

}
