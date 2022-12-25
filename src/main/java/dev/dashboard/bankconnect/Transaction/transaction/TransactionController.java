package dev.dashboard.bankconnect.Transaction.transaction;

import dev.dashboard.bankconnect.Transaction.Diposit.DepositService;
import dev.dashboard.bankconnect.Transaction.transfer.TransferService;
import dev.dashboard.bankconnect.Transaction.withdraw.WithdrawService;
import dev.dashboard.bankconnect.account.Account;
import dev.dashboard.bankconnect.account.AccountService;
import dev.dashboard.bankconnect.client.ClientService;
import dev.dashboard.bankconnect.dto.Response;
import dev.dashboard.bankconnect.dto.TransactionRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private ClientService clientService;
    private DepositService depositService;
    private WithdrawService withdrawService;
    private AccountService accountService;
    private TransferService transferService;

    public TransactionController(ClientService clientService, DepositService depositService, WithdrawService withdrawService, AccountService accountService, TransferService transferService) {
        this.clientService = clientService;
        this.depositService = depositService;
        this.withdrawService = withdrawService;
        this.accountService = accountService;
        this.transferService = transferService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Response> deposit(Principal principal, @Valid @RequestBody TransactionRequest request) {
        Account account = clientService.clientAccountByEmail(principal.getName());
        Response response = depositService.makeTransaction(account, null, request.getAmount());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Response> withdraw(Principal principal, @Valid @RequestBody TransactionRequest request) {
        Account account = clientService.clientAccountByEmail(principal.getName());
        Response response = withdrawService.makeTransaction(account, null, request.getAmount());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Response> transfer(Principal principal, @Valid @RequestBody TransactionRequest request) {
        Account senderAccount = clientService.clientAccountByEmail(principal.getName());
        Account receiverAccount = accountService.getAccountByAccountNumber(request.getReceiverNumber());
        Double amount = request.getAmount();

        Response response = transferService.makeTransaction(senderAccount, receiverAccount, amount);

        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
