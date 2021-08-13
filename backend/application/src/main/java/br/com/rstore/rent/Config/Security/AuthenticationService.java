package br.com.rstore.rent.Config.Security;

import br.com.rstore.rent.Controller.Form.RegisterForm;
import br.com.rstore.rent.Models.Owner;
import br.com.rstore.rent.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Owner registerUser(RegisterForm registerForm) throws Exception {
        Optional<Owner> optionalOwner = ownerRepository.findByEmail(registerForm.getEmail());
        if(optionalOwner.isEmpty()){
            Owner newOwner = new Owner();
            newOwner.setEmail(registerForm.getEmail());
            newOwner.setName(registerForm.getUsername());
            newOwner.setPassword(passwordEncoder.encode(registerForm.getPassword()));
            return ownerRepository.save(newOwner);
        }else{
            throw new Exception("There is already an user with that email!");
        }
    }

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
