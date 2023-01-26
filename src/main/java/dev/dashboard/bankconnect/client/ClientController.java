package dev.dashboard.bankconnect.client;


import dev.dashboard.bankconnect.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/client")
public class ClientController {

    private ClientService clientService;
    private final ClientRepository clientRepository;

    public ClientController(ClientService clientService,
                            ClientRepository clientRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    @PostMapping(path = "/register")
    public void register(@RequestBody Client client) {
        clientService.register(client);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getClients(
            @RequestParam(value = "email", defaultValue = "*") String email,
            @RequestParam(value = "status", defaultValue = "*") String status
    ) {
        return ResponseEntity.ok(
                new Response("List of clients", 200, clientService.getClients(email, status))
        );
    }

    @PostMapping("/accept")
    public ResponseEntity<Response> accept(@RequestParam("clientId") Long clientId) {
        boolean status = clientService.acceptClient(clientId);
        if (status) {
            return ResponseEntity.ok(new Response("Account created successfully", 200));
        }
        return ResponseEntity.ok(new Response("Ops something went wrong!", 400));
    }

    @PostMapping("/reject")
    public ResponseEntity<Response> reject(@RequestParam("clientId") Long clientId) {
        boolean status = clientService.rejectClient(clientId);
        if (status) {
            return ResponseEntity.ok(new Response("Account created successfully", 200));
        }
        return ResponseEntity.ok(new Response("Ops something went wrong!", 400));
    }
}
