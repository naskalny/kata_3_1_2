package ru.gagiev.springboot.kata_3_1_2.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gagiev.springboot.kata_3_1_2.model.User;
import ru.gagiev.springboot.kata_3_1_2.service.UserService;


@Controller
@RequestMapping("/user")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "index";
    }

    @GetMapping(value = "/getUser")
    public String getUser(@RequestParam(name = "id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "details";
    }

    @GetMapping(value = "/create")
    public String addUser(@ModelAttribute("user") User user) {
        return "create";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "create";
        userService.addUser(user);
        return "redirect:/user";
    }

    @GetMapping(value = "/edit")
    public String edit(@RequestParam(name = "id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "edit";
        userService.editUser(user);
        return "redirect:/user";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam(name = "id") int id) {
        userService.removeUser(id);
        return "redirect:/user";
    }
}
