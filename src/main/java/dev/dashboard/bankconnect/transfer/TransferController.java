package dev.dashboard.bankconnect.transfer;

import dev.dashboard.bankconnect.dto.TransferRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/transfer")
public class TransferController {

    TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping(path = "verify")
    public void verifyTransfer(@RequestBody Transfer transfer) {
        transferService.verifyTransfer(transfer.getId(), transfer.getVerificationCode());
    }
}
