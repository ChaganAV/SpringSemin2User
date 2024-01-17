package com.example.controllers;

import com.example.model.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    /**
     * сервис обработки данных из репозитория
     */
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Направление на страницу со списком пользователей
     * @param model данные для представления
     * @return переходим на представление вывода списка пользователей
     */
    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "user-list";
    }

    /**
     * Направление на страницу создания пользователя
     * @param user пользователь
     * @return переходим на страницу для заполнения данными
     */
    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    /**
     * Получение данных из представления
     * @param user
     * @return перенаправление на страницу со списком пользователей
     */
    @PostMapping("/user-create")
    public String createUser(User user){
        userService.save(user);
        return "redirect:/users";
    }

    /**
     * Удаление сотрудника по id
     * @param id
     * @return перенаправляем на страницу со списком пользователей
     */
    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    /**
     * Направление на страницу для изменения данных
     * @param id параметр из пути маршрутизации
     * @param model модель для передачи в представление пользователя
     * @return переходим на представление для изменения
     */
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "user-update";
    }

    /**
     * Получение модели из представления и перенаправление на страницу списка
     * @param user модель из представления
     * @return перенаправление на страницу списка пользователей
     */
    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user){
        userService.update(user);
        return "redirect:/users";
    }
}
