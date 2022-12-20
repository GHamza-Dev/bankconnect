package dev.dashboard.bankconnect.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    User findByEmail(String email);
}