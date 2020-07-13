package com.codegym.login_logout.api;

import com.codegym.login_logout.model.EnumRole;
import com.codegym.login_logout.model.entity.Role;
import com.codegym.login_logout.model.entity.User;
import com.codegym.login_logout.model.response.JwtResponse;
import com.codegym.login_logout.security.jwt.JwtUtils;
import com.codegym.login_logout.services.User.UserService;
import com.codegym.login_logout.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthAPI {

    public static final String ERROR_USERNAME_IS_ALREADY_TAKEN = "Username is already taken !!!";
    public static final String ERROR_EMAIL_IS_ALREADY_IN_USE = "Email is already in use !!!";
    public static final String ERROR_ROLE_IS_NOT_FOUND = "Role is not found.";
    public static final String USER_REGISTERED_SUCCESSFULLY = "User registered successfully !!!";
    private AuthenticationManager authenticationManager;

    private UserService userService;

    private RoleService roleRepository;

    private PasswordEncoder encoder;

    private JwtUtils jwtUtils;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleRepository(RoleService roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JwtResponse authenticateUser(@RequestBody User userLogin) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userLogin.getUsername(),
                userLogin.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(authToken);
        String jwtToken = jwtUtils.generateJwtToken(authentication);
        User userLogon = (User) authentication.getPrincipal();

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(jwtToken);
        jwtResponse.setUser(userLogon);

        return jwtResponse;
    }

    @PostMapping("/register")
    @ResponseBody
    public User registerUser(@Valid @RequestBody User userRegister) throws ExistUserNameException, ExistEmailException {
        if (userService.isUsernameExist(userRegister.getUsername())) {
            throw new ExistUserNameException();
        }
        if (userService.isEmailExist(userRegister.getEmail())) {
            throw new ExistEmailException();
        }

        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setEmail(userRegister.getEmail());
        user.setPassword(encoder.encode(userRegister.getPassword()));
        Set<Role> roles = new HashSet<>();
        if (userRegister.getRoles().size() != 0) {
            roles = userRegister.getRoles();
        } else {
            roles.add(roleRepository.getOneByRoleName(EnumRole.ROLE_USER));
        }
        user.setRoles(roles);
        userService.addOne(user);

        return user;
    }

    @ExceptionHandler(ExistUserNameException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public String handleDuplicateUsernameException() {
        return "{\"message\":\"" + ERROR_USERNAME_IS_ALREADY_TAKEN + "\"}";
    }

    @ExceptionHandler(ExistEmailException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public String handleDuplicateEmailException() {
        return "{\"message\":\"" + ERROR_EMAIL_IS_ALREADY_IN_USE + "\"}";
    }
}
