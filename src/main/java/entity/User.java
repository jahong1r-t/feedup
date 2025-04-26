package entity;

import entity.enums.Language;
import entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private Long id;
    private String username;
    private String fullName;
    private String phoneNumber;
    private Language language;
    private Role role;
    private Boolean isRegister;
}
