package dev.dashboard.bankconnect.transfer;

import dev.dashboard.bankconnect.account.Account;
import dev.dashboard.bankconnect.account.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private TransferRepository transferRepository;
    private AccountRepository accountRepository;

    public TransferService(TransferRepository transferRepository, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void verifyTransfer(Long transferId, String verificationCode) {
        Transfer transfer = transferRepository.findById(transferId).orElseThrow(() -> new IllegalStateException("Transfer with id " + transferId + " does not exist"));
        Account account = accountRepository.findById(transfer.getReceiverAccountId()).orElseThrow(() -> new IllegalStateException("Account with id " + transfer.getReceiverAccountId() + " does not exist"));

        if (!transfer.getVerificationCode().equals(verificationCode))
            throw new IllegalStateException("Verification code is incorrect");

        transfer.setVerificationCode(null);
        transfer.setStatus("verified");
        account.setBalance(account.getBalance() + transfer.getAmount());
//        transferRepository.save(transfer);

    }
}
