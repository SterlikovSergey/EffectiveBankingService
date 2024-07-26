package by.st.effectivebankingservice.service;

import by.st.effectivebankingservice.exception.CannotDeleteEmailException;
import by.st.effectivebankingservice.exception.PhoneExistException;
import by.st.effectivebankingservice.model.Phone;
import by.st.effectivebankingservice.model.User;
import by.st.effectivebankingservice.repository.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneService {
    private final PhoneRepository phoneRepository;

    public Phone save(User user, String number) {
        if(!isPhoneUnique(number)){
            throw new PhoneExistException("User with phone number: " + number +  " already exists");
        }
        Phone phone = new Phone();
        phone.setNumber(number);
        phone.setUser(user);
        return phoneRepository.save(phone);
    }
    public void delete(Long phoneId) {
        if(phoneRepository.countPhonesById(phoneId) <= 1) {
            throw new CannotDeleteEmailException("Must have at least one email address");

        }
        phoneRepository.deleteById(phoneId);
    }

    private boolean isPhoneUnique(String phone) {
        return !phoneRepository.existsByNumber(phone);
    }
}
