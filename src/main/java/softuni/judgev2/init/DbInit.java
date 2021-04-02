package softuni.judgev2.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import softuni.judgev2.model.entities.Role;
import softuni.judgev2.model.entities.RoleName;
import softuni.judgev2.repositories.RoleRepository;
import softuni.judgev2.repositories.UserRepository;
import softuni.judgev2.services.RoleService;
import softuni.judgev2.services.UserService;

@Component
public class DbInit implements CommandLineRunner {
    private final RoleService roleService;
    private final UserService userService;

    public DbInit(RoleService roleService, UserRepository repository, RoleRepository roleRepository, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
           roleService.initRoles();
           Role role = roleService.findRole(RoleName.ADMIN);
           userService.initAdmin(role);
    }
}
