package ru.job4j.accident.controller;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;

import java.sql.SQLException;

@Controller
public class RegControl {

    private static final Logger LOG = LoggerFactory.getLogger(RegControl.class.getName());

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    public RegControl(PasswordEncoder encoder, UserRepository users, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user,
                          Model model) {
        try {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthority(authorities.findByAuthority("ROLE_USER"));
            users.save(user);
            return "redirect:/login";
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            model.addAttribute("errorMessage", "This Username already exist!");
            return "reg";
        }
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
