package dev.dashboard.bankconnect.account;

import dev.dashboard.bankconnect.client.Client;
import dev.dashboard.bankconnect.client.ClientRepository;
import dev.dashboard.bankconnect.transfer.TransferRepository;
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

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
