package vn.edu.ptit.sqa.dto.auth;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.entity.auth.User;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String displayName;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.displayName = user.getDisplayName();
    }
}
