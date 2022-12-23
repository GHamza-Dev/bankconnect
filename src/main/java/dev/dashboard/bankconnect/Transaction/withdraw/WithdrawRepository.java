package dev.dashboard.bankconnect.Transaction.withdraw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
}