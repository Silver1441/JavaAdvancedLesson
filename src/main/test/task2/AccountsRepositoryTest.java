package task2;

import com.kishkan.epam.task2.dto.Account;
import com.kishkan.epam.task2.repository.AccountsRepository;
import com.kishkan.epam.task2.repository.AccountsRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountsRepositoryTest {
    private AccountsRepository repository;

    @BeforeEach
    public void Init() {
        repository = new AccountsRepositoryImpl();
        String name = "John";
        String surname = "Doe";
        long id = 101L;
        long balance = 1000L;
        Account account = new Account(name, surname, id, balance);
        repository.addAccount(account);
    }

    @Test
    public void getAccountTestByBalance() {
        long id = 101L;
        long expectedBalance = 1000L;
        long resultBalance = repository.getAccountById(id).getBalance();
        assertThat(resultBalance, equalTo(expectedBalance));
    }

    @Test
    public void getAccountTestException() {
        long id = 102L;
        assertThrows(NoSuchElementException.class, () -> repository.getAccountById(id));
    }
}
