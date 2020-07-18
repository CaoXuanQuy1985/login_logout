package com.codegym.login_logout.services.role;

import com.codegym.login_logout.model.EnumRole;
import com.codegym.login_logout.model.entity.Role;
import com.codegym.login_logout.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public List<Role> getAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Role> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Role getOne(long id) {
        return null;
    }

    @Override
    public Role addOne(Role model) {
        return null;
    }

    @Override
    public Role updateOne(Role model) {
        return null;
    }

    @Override
    public Role updateOne(long id, Role newModel) {
        return null;
    }

    @Override
    public void deleteOne(Long id) {

    }

    @Override
    public void deleteOne(Role model) {

    }

    @Override
    public Role getOneByRoleName(EnumRole roleName) {
        return roleRepository.findByName(roleName).orElse(null);
    }
}
