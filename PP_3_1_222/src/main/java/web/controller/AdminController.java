package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import web.model.MyUser;
import web.service.RoleServiceImpl;
import web.service.UserServiceImpl;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping
    public String adminHomePage(Model model) {
        model.addAttribute("allUsers", userServiceImpl.allUsers());
        return "home";
    }

    @PostMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new MyUser());
        model.addAttribute("roles", roleServiceImpl.getRoleList());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") MyUser myUser) {
        userServiceImpl.addUser(myUser);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImpl.getUserById(id));
        model.addAttribute("roles", roleServiceImpl.getRoleList());
        return "edit";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("user") MyUser myUser, @PathVariable("id") int id) {
        userServiceImpl.updateUser(myUser);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userServiceImpl.deleteUserById(id);
        return "redirect:/admin";
    }
}