package softuni.judgev2.model.service;

import softuni.judgev2.model.entities.Role;


public class UserServiceModel {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String git;
    private Role role;

    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }
    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGit() {
        return git;
    }
    public void setGit(String git) {
        this.git = git;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
