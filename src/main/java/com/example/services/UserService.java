package com.example.services;

import com.example.model.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * сервис с репозиторием
     * @param userRepository
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * выборка всех пользователей
     * @return список пользователей
     */
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * добавление нового пользователя
     * @param user новый пользователя
     * @return добавленный пользователь
     */
    public User save(User user){
        return userRepository.save(user);
    }

    /**
     * удаление пользователя по id
     * @param id
     */
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    /**
     * изменение пользователя
     * @param user новые данные пользователя
     * @return измененный пользователь
     */
    public User update(User user){
        return userRepository.update(user);
    }
}
