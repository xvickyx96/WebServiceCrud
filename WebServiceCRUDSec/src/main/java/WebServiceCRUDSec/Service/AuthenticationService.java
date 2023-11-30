package WebServiceCRUDSec.Service;


import java.util.HashSet;
import java.util.Set;

import WebServiceCRUDSec.Models.ApplicationUser;
import WebServiceCRUDSec.Models.LoginResponseDTO;
import WebServiceCRUDSec.Models.Role;
import WebServiceCRUDSec.Repository.RoleRepository;
import WebServiceCRUDSec.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

import WebServiceCRUDSec.Models.ApplicationUser;
import WebServiceCRUDSec.Models.LoginResponseDTO;
import WebServiceCRUDSec.Models.Role;
import WebServiceCRUDSec.Repository.RoleRepository;
import WebServiceCRUDSec.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


        public ApplicationUser registerUser(String username, String password) {
            String encodedPassword = passwordEncoder.encode(password);

            Role userRole = roleRepository.findByAuthority("USER")
                    .orElseThrow(() -> new IllegalStateException("Role not found: USER"));

            Set<Role> authorities = new HashSet<>();
            authorities.add(userRole);

            ApplicationUser newUser = new ApplicationUser();
            newUser.setUsername(username);
            newUser.setPassword(encodedPassword);
            newUser.setAuthorities(authorities);

            return userRepository.save(newUser);
        }


    public LoginResponseDTO loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }

}