package org.everyagile.everyagile.security;

import lombok.RequiredArgsConstructor;
import org.everyagile.everyagile.advice.CUsernameNotFoundException;
import org.everyagile.everyagile.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail){
        return userRepository.findByEmail(userEmail).orElseThrow(CUsernameNotFoundException::new);
    }
}
