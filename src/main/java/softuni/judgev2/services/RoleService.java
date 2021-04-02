package softuni.judgev2.services;

import softuni.judgev2.model.entities.Role;
import softuni.judgev2.model.entities.RoleName;

public interface RoleService {
    void initRoles();
    Role findRole(RoleName roleName);
}
