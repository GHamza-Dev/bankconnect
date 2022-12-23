package dev.dashboard.bankconnect.Transaction.Diposit;

import dev.dashboard.bankconnect.Transaction.Transaction;
import dev.dashboard.bankconnect.Transaction.TransactionService;
import dev.dashboard.bankconnect.account.Account;
import org.springframework.stereotype.Service;

@Service
public class DepositService implements TransactionService {

    private DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public boolean canMakeTransaction(Account account,Double amount) {
        return true;
    }

    @Override
    public Transaction makeTransaction(Account senderAccount,Account receiverAccount, Double amount) {
        if(senderAccount == null) return null;

        Deposit deposit = new Deposit();
        deposit.setAmount(amount);
        deposit.setAccount(senderAccount);

        depositRepository.save(deposit);

        return deposit;
    }
}
