import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.mb.exception.InsufficientBalanceException;
import com.example.mb.exception.InvalidAccountException;
import com.example.mb.model.Account;
import com.example.mb.model.BankTransfer;
import com.example.mb.model.Transaction;
import com.example.mb.model.UPITransaction;
import com.example.mb.repository.AccountRepository;
import com.example.mb.repository.BankTransferRepository;
import com.example.mb.repository.TransactionRepository;
import com.example.mb.repository.UPITransactionRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private BankTransferRepository bankTransferRepository;

    @Mock
    private UPITransactionRepository upiTransactionRepository;

    private Account fromAccount;
    private BankTransfer bankTransfer;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
       
        Branch branch = mock(Branch.class); // Mocking Branch
        Customer customer = mock(Customer.class); // Mocking Customer
        
        // Initialize Account with mocked Branch and Customer
        fromAccount = new Account("FROM123", "IFSC123", "Saving", BigDecimal.valueOf(10000), "Active", branch, customer);
        
        // Initialize the Transaction object
        transaction = new Transaction(, 5000.0, "BANK", "TRANSFER", "COMPLETED", "Payment for services", "Transfer to beneficiary account", "Online", LocalDateTime.now());
        
        // Initialize the BankTransfer with the transaction
        bankTransfer = new BankTransfer("Beneficiary", "BENEF123", "IFSC123", "Bank XYZ", "Saving", BigDecimal.valueOf(5000), transaction);
    }

    @Test
    public void testMakeBankTransferSuccess() throws InvalidAccountException, InsufficientBalanceException {
        // Arrange
        when(accountRepository.findByAccountNumber("FROM123")).thenReturn(fromAccount);
        when(bankTransferRepository.save(any(BankTransfer.class))).thenReturn(bankTransfer);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        // Act
        Transaction result = transactionService.makeBankTransfer(bankTransfer, "FROM123");

        // Assert
        assertEquals("COMPLETED", result.getStatus());
        assertEquals(BigDecimal.valueOf(5000), fromAccount.getBalance()); // Ensure the balance is updated
        verify(accountRepository, times(1)).save(fromAccount); // Verify that the account is saved with updated balance
    }

    @Test
    public void testMakeUPITransferSuccess() throws InvalidAccountException, InsufficientBalanceException {
        // Arrange
        when(accountRepository.findByAccountNumber("FROM123")).thenReturn(fromAccount);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        
        UPITransaction upiTransaction = new UPITransaction(transaction, "5000", "dummy@upi");
        when(upiTransactionRepository.save(any(UPITransaction.class))).thenReturn(upiTransaction);

        // Act
        Transaction result = transactionService.makeUPITransfer(transaction, "FROM123");

        // Assert
        assertEquals("COMPLETED", result.getStatus());
        assertEquals(BigDecimal.valueOf(5000), fromAccount.getBalance());
        verify(accountRepository, times(1)).save(fromAccount); // Verify the balance update
    }

    @Test
    public void testMakeBankTransferInsufficientBalance() {
        // Arrange
        fromAccount.setBalance(BigDecimal.valueOf(1000)); // Less than transfer amount
        when(accountRepository.findByAccountNumber("FROM123")).thenReturn(fromAccount);

        // Act & Assert
        assertThrows(InsufficientBalanceException.class, () -> {
            transactionService.makeBankTransfer(bankTransfer, "FROM123");
        });
    }

    @Test
    public void testMakeUPITransferInvalidAccount() {
        // Arrange
        when(accountRepository.findByAccountNumber("FROM123")).thenReturn(null); // Invalid account

        // Act & Assert
        assertThrows(InvalidAccountException.class, () -> {
            transactionService.makeUPITransfer(transaction, "FROM123");
        });
    }
}
