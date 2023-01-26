package dev.dashboard.bankconnect.client;

import dev.dashboard.bankconnect.user.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends UserRepository<Client> {
    Client findClientByEmail(String email);

    Client findClientByEmailOrCinOrPhone(String email, String cin, String phone);

    @Query("SELECT c FROM Client c WHERE (?1 IS NULL OR LOWER(c.email) LIKE %?1%) AND (?2 IS NULL OR LOWER(c.status) = ?2)")
    List<Client> searchClients(String email, String status);

}
