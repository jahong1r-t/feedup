package repository;

import entity.User;
import entity.enums.Language;
import entity.enums.Role;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static db.DataSource.*;

public class UserRepository {

    @SneakyThrows
    public Optional<User> getUser(Long id) {
        ResultSet resultSet = statement().executeQuery("SELECT * FROM users WHERE id = " + id);
        if (resultSet.next()) {
            User build = User.builder()
                    .id(resultSet.getLong("id"))
                    .username(resultSet.getString("username"))
                    .fullName(resultSet.getString("full_name"))
                    .phoneNumber(resultSet.getString("phone_number"))
                    .language(Enum.valueOf(Language.class, resultSet.getString("language")))
                    .role(Enum.valueOf(Role.class, resultSet.getString("role")))
                    .isRegister(resultSet.getBoolean("is_register"))
                    .build();

            return Optional.of(build);
        }
        return Optional.empty();
    }



    @SneakyThrows
    public void addUser(User user) {
        String sql = """
                    INSERT INTO users(id, username, full_name, phone_number, language, role, is_register)
                    VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')
                """.formatted(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getPhoneNumber(),
                user.getLanguage() != null ? user.getLanguage().getCode() : null,
                user.getRole() != null ? user.getRole().name() : null,
                user.getIsRegister()
        );
        statement().execute(sql);
    }

    @SneakyThrows
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = statement().executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setFullName(resultSet.getString("full_name"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
            user.setLanguage(Enum.valueOf(Language.class, resultSet.getString("language")));
            user.setRole(Enum.valueOf(Role.class, resultSet.getString("role")));
            user.setIsRegister(resultSet.getBoolean("is_register"));
        }
        return users;
    }
}
