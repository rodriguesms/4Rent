package br.com.rstore.rent.Controller;

import br.com.rstore.rent.Config.Security.AuthenticationService;
import br.com.rstore.rent.Config.Security.TokenService;
import br.com.rstore.rent.Controller.DTO.OwnerDTO;
import br.com.rstore.rent.Controller.DTO.RetrievalDTO;
import br.com.rstore.rent.Controller.DTO.TokenDTO;
import br.com.rstore.rent.Controller.Form.LoginForm;
import br.com.rstore.rent.Controller.Form.RegisterForm;
import br.com.rstore.rent.Models.Owner;
import br.com.rstore.rent.Repository.OwnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OwnerRepository ownerRepository;

    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid LoginForm loginForm){
        UsernamePasswordAuthenticationToken loginData = loginForm.Convert();
        try{
            Authentication authentication = authManager.authenticate(loginData);
            String token = tokenService.tokenGenerate(authentication);
            OwnerDTO ownerInfo = new OwnerDTO(ownerRepository.findByEmail(loginForm.getEmail()).get());
            return ResponseEntity.ok(new TokenDTO(token, "Bearer", ownerInfo));
        }catch(AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<OwnerDTO> register(@RequestBody @Valid RegisterForm registerForm){
        try{
            Owner newOwner = authService.registerUser(registerForm);
            return ResponseEntity.ok(new OwnerDTO(newOwner));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/retrieve")
    public ResponseEntity<RetrievalDTO> currentUser(Authentication authentication) {
        OwnerDTO ownerDTO = new OwnerDTO(authentication);
        return ResponseEntity.ok(new RetrievalDTO(ownerDTO));
    }
}
