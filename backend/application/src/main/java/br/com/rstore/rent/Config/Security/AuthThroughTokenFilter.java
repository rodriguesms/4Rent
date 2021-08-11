package br.com.rstore.rent.Config.Security;

import br.com.rstore.rent.Models.Owner;
import br.com.rstore.rent.Repository.OwnerRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthThroughTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private OwnerRepository ownerRepository;

    public AuthThroughTokenFilter(TokenService tokenService, OwnerRepository ownerRepository) {
        this.tokenService = tokenService;
        this.ownerRepository = ownerRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);

        Boolean validation = tokenService.isTokenValid(token);

        if(validation){
            clientAuthenticate(token);
        }

        filterChain.doFilter(request, response);
    }

    private void clientAuthenticate(String token) {
        Long ownerId = tokenService.getOwnerId(token);
        Owner owner = ownerRepository.findById(ownerId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(owner, null, owner.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }else{
            return token.substring(7, token.length());
        }
    }
}
