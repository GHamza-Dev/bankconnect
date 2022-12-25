package dev.dashboard.bankconnect.Transaction.Diposit;

import dev.dashboard.bankconnect.Transaction.transaction.TransactionService;
import dev.dashboard.bankconnect.account.Account;
import dev.dashboard.bankconnect.dto.Response;
import org.springframework.stereotype.Service;

@Service
public class DepositService implements TransactionService {

    private DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
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
}
