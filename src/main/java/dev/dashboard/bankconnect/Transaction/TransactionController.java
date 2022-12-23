package dev.dashboard.bankconnect.Transaction;

import dev.dashboard.bankconnect.Transaction.Diposit.DepositService;
import dev.dashboard.bankconnect.Transaction.withdraw.WithdrawService;
import dev.dashboard.bankconnect.account.Account;
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

    public TransactionController(ClientService clientService, DepositService depositService,WithdrawService withdrawService) {
        this.clientService = clientService;
        this.depositService = depositService;
        this.withdrawService = withdrawService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Response> deposit(Principal principal, @Valid @RequestBody TransactionRequest request){
        Account account = clientService.clientAccountByEmail(principal.getName());
        Response response = depositService.makeTransaction(account,null,request.getAmount());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Response> withdraw(Principal principal, @Valid @RequestBody TransactionRequest request){
        Account account = clientService.clientAccountByEmail(principal.getName());
        Response response = withdrawService.makeTransaction(account,null,request.getAmount());

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
