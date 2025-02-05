package com.cgigueira.universalpetcare.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cgigueira.universalpetcare.model.User;
import com.cgigueira.universalpetcare.repository.UserRepository;

@Service
public class PetCareUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userRepository.findByEmail(username);
    
    if (user == null)
      throw new UsernameNotFoundException("User not found❌⛔");
      
    return PetCareUserDetails.builder()
      .user(user)
      .build();
  }

}
