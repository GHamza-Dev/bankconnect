package dev.dashboard.bankconnect.client;


import dev.dashboard.bankconnect.dto.LoginDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/client")
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(path = "register")
    public void register(@RequestBody Client client) {
        clientService.register(client);
    }
}
