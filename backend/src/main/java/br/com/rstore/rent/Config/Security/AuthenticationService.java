package br.com.rstore.rent.Config.Security;

import br.com.rstore.rent.Models.Owner;
import br.com.rstore.rent.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Owner> owner = ownerRepository.findByEmail(username);
        if(owner.isPresent()){
            return owner.get();
        }else{
            throw new UsernameNotFoundException("Invalid data!");
        }
    }
}
