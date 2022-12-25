package dev.dashboard.bankconnect.Transaction.withdraw;

import dev.dashboard.bankconnect.Transaction.transaction.TransactionService;
import dev.dashboard.bankconnect.account.Account;
import dev.dashboard.bankconnect.dto.Response;
import org.springframework.stereotype.Service;

@Service
public class WithdrawService implements TransactionService {

    private WithdrawRepository withdrawRepository;

    public WithdrawService(WithdrawRepository withdrawRepository) {
        this.withdrawRepository = withdrawRepository;
    }

    @Override
    public boolean canMakeTransaction(Account account, Double amount) {
        return account.getBalance() >= amount;
    }

    @Override
    public Response makeTransaction(Account senderAccount, Account receiverAccount, Double amount) {
        if (senderAccount == null) return null;

        if (!canMakeTransaction(senderAccount, amount)) {
            return new Response("This account does not have enough money!", 400);
        }

        Withdraw withdraw = new Withdraw();
        withdraw.setAccount(senderAccount);
        withdraw.setAmount(amount);

        withdrawRepository.save(withdraw);

        if (withdraw.getId() != null) {
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
}
