package dev.dashboard.bankconnect.Transaction;

import dev.dashboard.bankconnect.account.Account;

public interface TransactionService {

    boolean canMakeTransaction(Account account, Double amount);

    Transaction makeTransaction(Account senderAccount,Account receiverAccount,Double amount);

}
