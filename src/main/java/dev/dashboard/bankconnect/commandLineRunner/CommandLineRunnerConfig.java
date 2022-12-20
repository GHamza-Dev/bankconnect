package dev.dashboard.bankconnect.commandLineRunner;

import dev.dashboard.bankconnect.account.AccountRepository;
import dev.dashboard.bankconnect.client.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineRunnerConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepo, AccountRepository accountRepo) {
        return args -> {
//            Client client1 = new Client();
//            client1.setFirstName("abderrahmane");
//            client1.setLastName("imad");
//            client1.setCin("hh123456");
//            client1.setEmail("im.dachoucha@gmail.com");
//            client1.setPassword("client1test");
//            client1.setPhone("0123456789");
//            client1.setActive(false);
//
//            Client client2 = new Client();
//            client2.setFirstName("ali");
//            client2.setLastName("mohamed");
//            client2.setCin("jj654321");
//            client2.setEmail("test@gmail.com");
//            client2.setPassword("client2test");
//            client2.setPhone("0987654321");
//            client2.setActive(false);
//
//            clientRepo.saveAll(List.of(client1, client2));
////
////
//            Account account1 = new Account();
//            account1.setBalance(1000);
//            account1.setAccountNumber();
//
//            Account account2 = new Account();
//            account1.setBalance(50);
//            account1.setAccountNumber();
//
//            accountRepo.saveAll(List.of(account1, account2));
//
//            client1.setAccount(account1);
//            client2.setAccount(account2);
//            clientRepo.saveAll(List.of(client1, client2));
        };
    }
}
