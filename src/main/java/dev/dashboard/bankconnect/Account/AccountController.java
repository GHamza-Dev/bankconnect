package dev.dashboard.bankconnect.Account;

import dev.dashboard.bankconnect.Dto.TransferRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/account")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "create")
    public void createAccountForClient(@RequestParam Long clientId, @RequestParam String accountType) {
        accountService.createAccount(clientId, accountType);
    }

    @PostMapping(path = "transfer")
    public void transferMoney(@RequestBody TransferRequest transferRequest) {
        accountService.transfer(transferRequest.getFromAccountId(), transferRequest.getToAccountId(), transferRequest.getAmount());
    }
}
