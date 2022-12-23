package dev.dashboard.bankconnect.Transaction;

import dev.dashboard.bankconnect.Transaction.Diposit.DepositService;
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

    public TransactionController(ClientService clientService, DepositService depositService) {
        this.clientService = clientService;
        this.depositService = depositService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Response> deposit(Principal principal, @Valid @RequestBody TransactionRequest request){
        Account account = clientService.clientAccountByEmail(principal.getName());
        Transaction transaction = depositService.makeTransaction(account,null,request.getAmount());

        if (transaction != null) {
            return ResponseEntity.ok(
                    new Response(
                            "Transaction done successfully",
                            200
                    )
            );
        }

        return ResponseEntity.status(500).body(
                new Response(
                        "Ops something went wrong while making this transaction!",
                        500
                )
        );
    }
}
