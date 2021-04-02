package softuni.judgev2.services.impl;

import org.springframework.stereotype.Service;
import softuni.judgev2.model.entities.Role;
import softuni.judgev2.model.entities.RoleName;
import softuni.judgev2.repositories.RoleRepository;
import softuni.judgev2.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initRoles() {
         if (repository.count()==0){
             Role admin = new Role(RoleName.ADMIN);
             Role user = new Role(RoleName.USER);
             repository.save(admin);
             repository.save(user);
         }
    }

    @Override
    public Role findRole(RoleName roleName) {
        return repository.findByName(roleName).orElse(null);
    }
}
