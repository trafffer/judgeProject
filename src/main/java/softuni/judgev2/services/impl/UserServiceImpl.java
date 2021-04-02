package softuni.judgev2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.judgev2.model.entities.Role;
import softuni.judgev2.model.entities.RoleName;
import softuni.judgev2.model.entities.User;
import softuni.judgev2.model.service.UserServiceModel;
import softuni.judgev2.model.view.UserProfileViewModel;
import softuni.judgev2.repositories.UserRepository;
import softuni.judgev2.security.CurrentUser;
import softuni.judgev2.services.RoleService;
import softuni.judgev2.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final ModelMapper mapper;
    private final RoleService roleService;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository repository, ModelMapper mapper, RoleService roleService, CurrentUser currentUser) {
        this.repository = repository;
        this.mapper = mapper;
        this.roleService = roleService;
        this.currentUser = currentUser;
    }

    public void initAdmin(Role role){
        Optional<User> user = repository.findByUsernameAndPassword("admin","topSecret");
        if(user.isEmpty()) {
            User admin = new User();
            admin.setRole(role);
            admin.setUsername("admin");
            admin.setEmail("nova@mail.bg");
            admin.setGit("https://github.com/admin");
            admin.setPassword("topSecret");
            repository.save(admin);
        }
    }


    @Override
    public void registerUser(UserServiceModel userServiceModel) {
         User user = mapper.map(userServiceModel, User.class);
         user.setRole(roleService.findRole(RoleName.USER));
         repository.save(user);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
       return repository.findByUsernameAndPassword(username, password)
                .map(user -> mapper.map(user,UserServiceModel.class))
               .orElse(null);
    }

    @Override
    public void login(UserServiceModel userServiceModel) {
        currentUser.setId(userServiceModel.getId())
                .setUsername(userServiceModel.getUsername())
                .setRoleName(userServiceModel.getRole().getName());
    }

    @Override
    public void logout() {
        currentUser.setId(null).setUsername(null).setRoleName(null);
    }

    @Override
    public List<String> findAllUsernames() {
        return repository.findAllUsernames();
    }

    @Override
    public void changeRole(String username, RoleName roleName) {
        User user = repository.findByUsername(username).orElse(null);
        if (user.getRole().getName() != roleName) {
                user.setRole(roleService.findRole(roleName));
                repository.save(user);
        }

        
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public UserProfileViewModel findViewModel(Long id) {
        User user = repository.findById(id).orElse(null);
        Long id1 = currentUser.getId();
        UserProfileViewModel viewModel = mapper.map(user,UserProfileViewModel.class)
                .setHomeworks(user.getHomeworkSet()
                        .stream()
                        .map(p->p.getExercise().getName())
                        .collect(Collectors.toSet()));
        return viewModel;
    }

    @Override
    public Long totalCount() {
        return repository.count();
    }
}
