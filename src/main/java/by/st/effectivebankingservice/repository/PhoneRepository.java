package by.st.effectivebankingservice.repository;

import by.st.effectivebankingservice.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    boolean existsByNumber(String number);

    int countPhoneByUserId(Long userId);
}
