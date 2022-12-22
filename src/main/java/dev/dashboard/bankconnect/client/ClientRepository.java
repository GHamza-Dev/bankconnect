package dev.dashboard.bankconnect.client;

import dev.dashboard.bankconnect.user.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends UserRepository<Client> {
    Client findClientByEmail(String email);

    Client findClientByEmailOrCinOrPhone(String email, String cin, String phone);

}
