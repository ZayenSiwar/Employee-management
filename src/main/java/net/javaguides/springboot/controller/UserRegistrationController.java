package net.javaguides.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import net.javaguides.springboot.dto.UserRegistrationDto;
import net.javaguides.springboot.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto,
                                      @RequestParam("photo") MultipartFile photo) {
        try {
            byte[] photoBytes = photo.getBytes();
            userService.save(registrationDto, photoBytes);
            return "redirect:/registration?success";
        } catch (IOException e) {
            return "redirect:/registration?error";
        }
    }
}
