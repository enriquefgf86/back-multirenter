package com.example.demo.services;

import com.example.demo.entiities.Renter;
import com.example.demo.jwt.JwtUtils;
import com.example.demo.repositories.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    RenterRepository renterRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String renterName) throws UsernameNotFoundException {
       Renter renter = renterRepository.findByRenterName(renterName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + renterName));

        return UserDetailsImpl.build(renter);
    }
    //accediendlse al objeto que traeria toda la data del usuario loggeado
}
