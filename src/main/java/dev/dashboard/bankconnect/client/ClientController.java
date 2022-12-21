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

    @PostMapping(path = "login")
    public Client login(@RequestBody LoginDto loginDto) {
        System.out.println("here");
        Client client = clientService.login(loginDto.getEmail(), loginDto.getPassword());
        return client;
    }

    @PostMapping(path = "register")
    public void register(@RequestBody Client client) {
        clientService.register(client);
    }
}
