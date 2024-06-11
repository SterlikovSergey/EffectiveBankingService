package by.st.effectivebankingservice.repository;

import by.st.effectivebankingservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    @Query("SELECT u FROM User u " +
            "LEFT JOIN u.emails e " +
            "LEFT JOIN u.phones p " +
            "WHERE (:birthday IS NULL OR u.birthday > :birthday) " +
            "AND (:phone IS NULL OR p.number = :phone) " +
            "AND (:fullName IS NULL OR CONCAT(u.firstName, ' ', u.lastName) LIKE CONCAT(:fullName, '%')) " +
            "AND (:email IS NULL OR e.email = :email)")
    Page<User> searchUsers(@Param("birthday") LocalDate birthday,
                           @Param("phone") String phone,
                           @Param("fullName") String fullName,
                           @Param("email") String email,
                           Pageable pageable);
}
