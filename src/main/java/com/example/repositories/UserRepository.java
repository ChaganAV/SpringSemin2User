package com.example.repositories;

import com.example.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * выборка всех пользователей
     * @return список пользователей
     */
    public List<User> findAll(){
        String sql = "SELECT * FROM userTable";
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowUser = new User();
            rowUser.setId(r.getInt("id"));
            rowUser.setFirstName(r.getString("firstName"));
            rowUser.setLastName(r.getString("lastName"));
            return rowUser;
        };
        return jdbcTemplate.query(sql,userRowMapper);
    }

    /**
     * поиск пользователя по id
     * @param id
     * @return пользователь
     */
    public User findById(int id){
        String sql = "SELECT * FROM userTable WHERE id=?";
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowUser = new User();
            rowUser.setId(r.getInt("id"));
            rowUser.setFirstName(r.getString("firstName"));
            rowUser.setLastName(r.getString("lastName"));
            return rowUser;
        };
        return jdbcTemplate.query(sql,new Object[]{id},userRowMapper).stream().findFirst().orElse(null);
    }

    /**
     * добавление нового пользователя
     * @param user пользователь
     * @return возвращает созданного пользователя
     */
    public User save(User user){
        String sql = "INSERT INTO userTable VALUES (DEFAULT,?,?)";
        jdbcTemplate.update(sql,user.getFirstName(),user.getLastName());
        return user;
    }

    /**
     * Удаление пользователя по id
     * @param id
     */
    public void deleteById(int id){
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbcTemplate.update(sql,id);
    }

    /**
     * Изменение пользователя
     * @param user
     * @return возвращает измененного пользователя
     */
    public User update(User user){
        String sql = "UPDATE userTable SET firstName=?, lastName=? WHERE id=?";
        jdbcTemplate.update(sql,user.getFirstName(),user.getLastName(),user.getId());
        return user;
    }
}
