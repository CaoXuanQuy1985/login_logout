package com.codegym.login_logout.services.User;

import com.codegym.login_logout.model.entity.User;
import com.codegym.login_logout.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;

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
    public User addOne(User model) {
        return null;
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
}
