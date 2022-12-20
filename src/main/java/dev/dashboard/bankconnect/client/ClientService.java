package dev.dashboard.bankconnect.client;


import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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
        if(c != null)
            throw new IllegalStateException("Email, cin or phone already exists");

        clientRepository.save(client);
    }
}
