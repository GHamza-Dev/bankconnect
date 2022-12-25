package dev.dashboard.bankconnect.account;

import dev.dashboard.bankconnect.client.Client;
import dev.dashboard.bankconnect.client.ClientRepository;
import dev.dashboard.bankconnect.Transaction.transfer.Transfer;
import dev.dashboard.bankconnect.Transaction.transfer.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    private AccountRepository accountRepository;
    private ClientRepository clientRepository;
    private TransferRepository transferRepository;

    public AccountService(AccountRepository accountRepository, ClientRepository clientRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.transferRepository = transferRepository;
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }


    @Transactional
    public Account createAccount(Long clientId, String accountType) {

        Client client = clientRepository.findById(clientId).orElse(null);

        if (client == null)
            throw new IllegalStateException("Client with id " + clientId + " does not exist");

        if (client.getAccount() != null)
            throw new IllegalStateException("Client with id " + clientId + " already has an account");

        if (client.isActive() != true)
            throw new IllegalStateException("Client with id " + clientId + " is not active");

        Account account = new Account();
        account.setClient(client);
        account.setAccountType(accountType);
        account.setAccountNumber();
        account.setBalance(0);


        accountRepository.save(account);
        client.setAccount(account);
        return account;
//    }

//        return accountRepository.save(account);
    }

    public void transfer(Long fromAccountId, Long toAccountId, Double amount) {
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(() -> new IllegalStateException("Account with id " + fromAccountId + " does not exist"));
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow(() -> new IllegalStateException("Account with id " + toAccountId + " does not exist"));

        if (fromAccount.getBalance() < amount)
            throw new IllegalStateException("Insufficient funds in account " + fromAccountId);

        Transfer transfer = new Transfer();

        transfer.setAmount(amount);
        transfer.setSenderAccountId(fromAccountId);
        transfer.setReceiverAccountId(toAccountId);
        transfer.setStatus("pending");

        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append((int) (Math.random() * 10));
        }
        transfer.setVerificationCode(code.toString());

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepository.save(fromAccount);

        transferRepository.save(transfer);
    }
}