package softuni.judgev2.services;

import softuni.judgev2.model.entities.Role;
import softuni.judgev2.model.entities.RoleName;
import softuni.judgev2.model.entities.User;
import softuni.judgev2.model.service.UserServiceModel;
import softuni.judgev2.model.view.UserProfileViewModel;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);
    void initAdmin(Role role);
    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void login(UserServiceModel userServiceModel);

    void logout();

    List<String> findAllUsernames();


    void changeRole(String username, RoleName roleName);

    User findById(Long id);

    UserProfileViewModel findViewModel(Long id);

    Long totalCount();
}
