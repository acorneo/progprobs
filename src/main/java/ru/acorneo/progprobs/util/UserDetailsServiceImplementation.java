package ru.acorneo.progprobs.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.acorneo.progprobs.data.model.User;
import ru.acorneo.progprobs.data.model.UserRoles;
import ru.acorneo.progprobs.repositories.UserRepository;
import ru.acorneo.progprobs.repositories.UserRolesRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserDetailsServiceImplementation implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;

    public UserDetailsServiceImplementation(UserRepository userRepository, UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<UserRoles> userRolesList = userRolesRepository.findAllById(userRepository.findByUsername(username).getFirst().getId());
                ArrayList<GrantedAuthority> roles = new ArrayList<>();
                for (UserRoles userRole : userRolesList) {
                    roles.add(new SimpleGrantedAuthority(userRole.getRole()));
                }
                return roles;
            }

            @Override
            public String getPassword() {
                List<User> userList = Collections.singletonList(userRepository.findByUsername(username).getFirst());
                return userList.getFirst().getHashedPassword();
            }

            @Override
            public String getUsername() {
                return username;
            }
        };
    }
}
