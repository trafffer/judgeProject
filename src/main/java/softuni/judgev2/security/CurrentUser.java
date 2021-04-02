package softuni.judgev2.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import softuni.judgev2.model.entities.RoleName;

@Component
@SessionScope
public class CurrentUser {
    private Long id;
    private String username;
    private RoleName roleName;
    private boolean annonymous = true;

    public CurrentUser() {
    }

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public CurrentUser setRoleName(RoleName roleName) {
        this.roleName = roleName;
        return this;
    }

    public boolean isAnonymous(){
        return this.username==null;
    }

    public boolean isAdmin(){
        return this.roleName==RoleName.ADMIN;
    }
}
