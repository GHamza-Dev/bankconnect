package dev.dashboard.bankconnect.Transaction.Diposit;

import dev.dashboard.bankconnect.Transaction.transaction.TransactionService;
import dev.dashboard.bankconnect.Transaction.transfer.TransferRepository;
import dev.dashboard.bankconnect.account.Account;
import dev.dashboard.bankconnect.account.AccountService;
import dev.dashboard.bankconnect.dto.Response;
import org.springframework.stereotype.Service;

@Service
public class DepositService implements TransactionService {

    private DepositRepository depositRepository;
    private AccountService accountService;
    private final TransferRepository transferRepository;

    public DepositService(DepositRepository depositRepository, AccountService accountService,
                          TransferRepository transferRepository) {
        this.depositRepository = depositRepository;
        this.accountService = accountService;
        this.transferRepository = transferRepository;
    }

    @Override
    public boolean canMakeTransaction(Account account, Double amount) {
        return true;
    }

    @Override
    public Response makeTransaction(Account senderAccount, Account receiverAccount, Double amount) {
        if (senderAccount == null) return null;

        Deposit deposit = new Deposit();
        deposit.setAmount(amount);
        deposit.setAccount(senderAccount);

        depositRepository.save(deposit);

        if (deposit.getId() != null) {
            return new Response(
                    "Transaction done successfully",
                    200
            );
        }

        return new Response(
                "Ops something went wrong while making this transaction!",
                500
        );
    }

    public Response acceptDeposit(Long depositId) {

        Deposit deposit = depositRepository.findById(depositId).orElse(null);

        if (!this.canChangeTransactionStatus(deposit)) {
            return new Response("Status can not be changed in this stage!", 404);
        }

        if (deposit == null) {
            return new Response("No deposit found with the id: " + depositId + "!", 404);
        }

        if (!accountService.increaseBalance(deposit.getAccount(), deposit.getAmount())) {
            return new Response("Ops something went wrong while making this transaction!", 400);
        }

        deposit.setStatus("ACCEPTED");
        depositRepository.save(deposit);

        return new Response("success", 200);
    }

    public Response rejectDeposit(Long depositId) {
        Deposit deposit = depositRepository.findById(depositId).orElse(null);

        if (!this.canChangeTransactionStatus(deposit)) {
            return new Response("Status can not be changed in this stage!", 404);
        }

        if (deposit == null) {
            return new Response("No deposit found with the id: " + depositId + "!", 404);
        }

        deposit.setStatus("REJECTED");
        depositRepository.save(deposit);

        return new Response("success", 200);
    }
}
