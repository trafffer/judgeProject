package softuni.judgev2.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterBindingModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String git;

    public UserRegisterBindingModel() {
    }

    @Length(min = 2,message = "Username length must be minimum 2 characters!")
    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Length(min = 3,message = "Password length must be minimum 3 characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotNull
    @Email(message = "Enter valid email address")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+",message = "Enter valid git address")
    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }
}
