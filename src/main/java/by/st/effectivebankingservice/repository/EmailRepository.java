package by.st.effectivebankingservice.repository;

import by.st.effectivebankingservice.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    boolean existsByEmail(String email);
    int countEmailByUserId(Long userId);
}
