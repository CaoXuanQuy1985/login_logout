package com.codegym.login_logout.services.User;

import com.codegym.login_logout.model.entity.User;
import com.codegym.login_logout.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getAll(Sort sort) {
        return null;
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public User getOne(long id) {
        return null;
    }

    @Override
    public User addOne(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateOne(User model) {
        return null;
    }

    @Override
    public User updateOne(long id, User newModel) {
        return null;
    }

    @Override
    public void deleteOne(Long id) {

    }

    @Override
    public void deleteOne(User model) {

    }

    @Override
    public boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
