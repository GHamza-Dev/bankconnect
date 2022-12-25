package dev.dashboard.bankconnect.Transaction.transaction;

import dev.dashboard.bankconnect.account.Account;
import dev.dashboard.bankconnect.dto.Response;

public interface TransactionService {

    boolean canMakeTransaction(Account account, Double amount);

    Response makeTransaction(Account senderAccount, Account receiverAccount, Double amount);

}
