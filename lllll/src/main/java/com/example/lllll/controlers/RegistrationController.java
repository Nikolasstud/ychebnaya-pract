package com.example.lllll.controlers;
import com.example.lllll.Models.Role;
import com.example.lllll.Models.Tovar;
import com.example.lllll.Repositories.TovarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private TovarRepository tovarRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String reg(Tovar tovar) {
        return ("registration");
    }

    @PostMapping("/registration")
    public String reg(Tovar tovar,
                      Model model) {

        if(tovarRepository.findByusername(tovar.getUsername()) != null) {
            model.addAttribute("error", "Логин занят!");
            return ("registration");
        }

        tovar.setPassword(passwordEncoder.encode(tovar.getPassword()));
        tovar.setActive(true);
        tovar.setRoles(Collections.singleton(Role.USER));

        tovarRepository.save(tovar);

        return ("redirect:/login");
    }
}
