package dev.dashboard.bankconnect.Transaction.transfer;

import dev.dashboard.bankconnect.Transaction.transaction.TransactionService;
import dev.dashboard.bankconnect.account.Account;
import dev.dashboard.bankconnect.dto.Response;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransferService implements TransactionService {
    private final TransferRepository transferRepository;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public boolean canMakeTransaction(Account account, Double amount) {
        return account.getBalance() > amount;
    }


    @Override
    @Transactional
    public Response makeTransaction(Account senderAccount, Account receiverAccount, Double amount) {
        if (senderAccount == null || receiverAccount == null) return null;

        if (!canMakeTransaction(senderAccount, amount)) {
            return new Response("The account " + senderAccount.getAccountNumber() + " can not make this transaction!", 400);
        }

        Transfer transfer = new Transfer();
        transfer.setSenderAccountId(senderAccount.getId());
        transfer.setReceiverAccountId(receiverAccount.getId());
        transfer.setAmount(amount);

        transferRepository.save(transfer);

        Double balanceAfterTransfer = senderAccount.getBalance() - amount;
        senderAccount.setBalance(balanceAfterTransfer);

        return new Response("Transaction done successfully", 200);
    }
}
