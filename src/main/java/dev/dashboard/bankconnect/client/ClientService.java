package dev.dashboard.bankconnect.client;


import dev.dashboard.bankconnect.account.Account;
import dev.dashboard.bankconnect.account.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private AccountService accountService;

    public ClientService(ClientRepository clientRepository, AccountService accountService) {
        this.clientRepository = clientRepository;
        this.accountService = accountService;
    }

    public Client login(String email, String password) {
        Client client = clientRepository.findClientByEmail(email);
        if (client == null)
            throw new IllegalStateException("Client not found");

        if (!client.getPassword().equals(password))
            throw new IllegalStateException("Wrong password");

        return client;
    }

    public void register(Client client) {
        Client c = clientRepository.findClientByEmailOrCinOrPhone(client.getEmail(), client.getCin(), client.getPhone());
        if (c != null)
            throw new IllegalStateException("Email, cin or phone already exists");

        clientRepository.save(client);
    }

    public List<Client> getClients(String email, String status) {
        email = email.equals("*") ? null : email;
        status = status.equals("*") ? null : status;

        return clientRepository.searchClients(email, status);
    }

    public Account clientAccountByEmail(String email) {
        Client client = clientRepository.findClientByEmail(email);
        return client.getAccount();
    }

    public boolean acceptClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseGet(null);
        if (client == null) {
            return false;
        }
        client.setStatus("accepted");
        client.setActive(true);
        clientRepository.save(client);
        accountService.createAccount(clientId, client.getAccountType());
        return client.getStatus().equals("accepted");
    }

    public boolean rejectClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseGet(null);
        if (client == null) {
            return false;
        }
        client.setStatus("rejected");
        clientRepository.save(client);
        return client.getStatus().equals("rejected");
    }
}
