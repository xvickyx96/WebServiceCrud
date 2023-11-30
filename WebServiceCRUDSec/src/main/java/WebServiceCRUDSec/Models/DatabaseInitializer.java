package WebServiceCRUDSec.Models;

import WebServiceCRUDSec.Repository.RoleRepository;
import WebServiceCRUDSec.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Skapa en roll om den inte redan finns
        Role userRole = roleRepository.findByAuthority("USER")
                .orElseGet(() -> roleRepository.save(new Role("USER")));

        // Skapa en användare om den inte redan finns
        userRepository.findByUsername("user")
                .orElseGet(() -> {
                    ApplicationUser adminUser = new ApplicationUser();
                    adminUser.setUsername("user");
                    adminUser.setPassword(passwordEncoder.encode("user"));
                    adminUser.setAuthorities(new HashSet<>(Collections.singletonList(userRole)));
                    adminUser.setId(1);
                    return userRepository.save(adminUser);
                });
    }
}
