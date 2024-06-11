package by.st.effectivebankingservice.service;

import by.st.effectivebankingservice.exception.InsufficientFundsException;
import by.st.effectivebankingservice.exception.UserExistException;
import by.st.effectivebankingservice.model.Account;
import by.st.effectivebankingservice.model.Email;
import by.st.effectivebankingservice.model.Phone;
import by.st.effectivebankingservice.model.User;
import by.st.effectivebankingservice.repository.AccountRepository;
import by.st.effectivebankingservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final EmailService emailService;

    private final PhoneService phoneService;

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User %s not found", username))
        );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }
    @Transactional(rollbackOn = Exception.class)
    public User createUser(User user) {
        if(!isUsernameUnique(user.getUsername())) {
            throw new UserExistException("User with username " + user.getUsername() + " already exists");
        }

        Account account = user.getAccount();
        account = accountRepository.save(account);
        account.setInitialDeposit(account.getBalance());
        user.setAccount(account);

        user = save(user);

        User finalUser = user;
        List<Email> emails = user.getEmails().stream()
                .map(email -> emailService.save(finalUser, email.getEmail()))
                .collect(Collectors.toList());
        user.setEmails(emails);

        List<Phone> phones = user.getPhones().stream()
                .map(phone -> phoneService.save(finalUser, phone.getNumber()))
                .collect(Collectors.toList());
        user.setPhones(phones);

        return user;
    }

    private User save(User user) {
        return userRepository.save(User.builder()
                .username(user.getUsername())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .account(user.getAccount())
                .emails(user.getEmails())
                .phones(user.getPhones())
                .build());
    }

    @Transactional(rollbackOn = Exception.class)
    public synchronized void transferMoney(Long targetUserId, BigDecimal amount) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User authenticatedUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Authenticated user not found"));

        User targetUser = userRepository.findById(targetUserId)
                .orElseThrow(() -> new UsernameNotFoundException("Target user not found"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero");
        }

        Account sourceAccount = authenticatedUser.getAccount();
        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient balance");
        }

        Account targetAccount = targetUser.getAccount();
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        targetAccount.setBalance(targetAccount.getBalance().add(amount));

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
    }

    @Transactional
    public Page<User> searchUsers(LocalDate birthday, String phone, String fullName, String email, Pageable pageable) {
        return userRepository.searchUsers(birthday, phone, fullName, email, pageable);
    }

    private boolean isUsernameUnique(String username){
        return !userRepository.existsByUsername(username);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
