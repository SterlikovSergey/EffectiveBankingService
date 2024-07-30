package by.st.effectivebankingservice.service;

import by.st.effectivebankingservice.model.Account;
import by.st.effectivebankingservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;


    @Transactional
    public void withdraw(Long accountId, BigDecimal amount) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        BigDecimal newBalance = account.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    @Scheduled(fixedRate = 60000)
    public void updateBalances() {
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            BigDecimal initialDeposit = account.getInitialDeposit();
            BigDecimal maxBalance = initialDeposit.multiply(BigDecimal.valueOf(2.07));
            BigDecimal newBalance = account.getBalance().multiply(BigDecimal.valueOf(1.05));

            if (newBalance.compareTo(maxBalance) > 0) {
                newBalance = maxBalance;
            }

            account.setBalance(newBalance);
            accountRepository.save(account);
        }
    }
}
