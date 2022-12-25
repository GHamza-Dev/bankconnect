package dev.dashboard.bankconnect.Transaction.transfer;

import dev.dashboard.bankconnect.Transaction.transfer.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
