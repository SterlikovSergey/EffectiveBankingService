package by.st.effectivebankingservice.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegistrationRequest {

    private String username;

    private String password;

    private BigDecimal initialBalance;

    private String phone;

    private String email;

}
