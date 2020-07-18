package com.codegym.login_logout.services.role;

import com.codegym.login_logout.model.EnumRole;
import com.codegym.login_logout.model.entity.Role;
import com.codegym.login_logout.services.GenericService;

public interface RoleService extends GenericService<Role> {
    Role getOneByRoleName(EnumRole roleName);
}
