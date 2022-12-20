package dev.dashboard.bankconnect.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientByEmail(String email);

    Client findClientByEmailOrCinOrPhone(String email, String cin, String phone);
}
