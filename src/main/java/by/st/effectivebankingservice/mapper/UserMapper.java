package by.st.effectivebankingservice.mapper;

import by.st.effectivebankingservice.dtos.RegistrationRequest;
import by.st.effectivebankingservice.model.Account;
import by.st.effectivebankingservice.model.Email;
import by.st.effectivebankingservice.model.Phone;
import by.st.effectivebankingservice.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public User toUser(RegistrationRequest dto) {
        Account account = new Account();
        account.setBalance(dto.getInitialBalance());

        Email email = new Email();
        email.setEmail(dto.getEmail());
        List<Email> emails = new ArrayList<>();
        emails.add(email);

        Phone phone = new Phone();
        phone.setNumber(dto.getPhone());
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);

        return User
                .builder()
                .username(dto.getUsername())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthday(dto.getBirthday())
                .password(dto.getPassword())
                .emails(emails)
                .phones(phones)
                .account(account)
                .build();
    }
}
