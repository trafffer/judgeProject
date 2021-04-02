package softuni.judgev2.model.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
      private String username;
      private String password;
      private String email;
      private String git;
      private Role role;
      private Set<Homework> homeworkSet;

    public User() {
    }

    @Column(name = "username",unique = true,nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password",nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email",nullable = false,unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "git",nullable = false)
    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }

    @ManyToOne
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "author",fetch = FetchType.EAGER)
    public Set<Homework> getHomeworkSet() {
        return homeworkSet;
    }

    public User setHomeworkSet(Set<Homework> homeworkSet) {
        this.homeworkSet = homeworkSet;
        return this;
    }
}
