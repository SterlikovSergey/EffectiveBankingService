package by.st.effectivebankingservice.service;

import by.st.effectivebankingservice.exception.CannotDeleteEmailException;
import by.st.effectivebankingservice.exception.EmailExistException;
import by.st.effectivebankingservice.model.Email;
import by.st.effectivebankingservice.model.User;
import by.st.effectivebankingservice.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailRepository emailRepository;

    public Email save(User user, String email) {
        if (!isEmailUnique(email)) {
            throw new EmailExistException("User with email " + email + " already exists");
        }
        Email newEmail = new Email();
        newEmail.setEmail(email);
        newEmail.setUser(user);
        return emailRepository.save(newEmail);
    }

    public void delete(Long emailId, User user) {
        if(emailRepository.countEmailByUserId(user.getId()) <= 1) {
           throw new CannotDeleteEmailException("Must have at least one email address");
        }
        emailRepository.deleteById(emailId);
    }
    private boolean isEmailUnique(String email) {
        return !emailRepository.existsByEmail(email);
    }
}
