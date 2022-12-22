package dev.dashboard.bankconnect.user;

import dev.dashboard.bankconnect.agent.Agent;
import dev.dashboard.bankconnect.agent.AgentRepository;
import dev.dashboard.bankconnect.client.Client;
import dev.dashboard.bankconnect.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository<Client> clientRepository;
    private UserRepository<Agent> agentRepository;


    @Autowired
    public void setUserRepository(UserRepository<Client> clientRepository){
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setAgentRepository(UserRepository<Agent> agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        String _email = StringUtil.extractPrefix(email,':');
        String _role = StringUtil.extractSuffix(email,':');

        User user = _role.equals("AGENT") ?
                agentRepository.findByEmail(_email) :
                clientRepository.findByEmail(_email);

        if(user == null){
            throw new UsernameNotFoundException("No user found with the email: "+email);
        }
        return new CustomUserDetails(user);
    }
}
