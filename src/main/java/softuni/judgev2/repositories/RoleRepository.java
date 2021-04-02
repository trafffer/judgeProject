package softuni.judgev2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.judgev2.model.entities.Role;
import softuni.judgev2.model.entities.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role>findByName(RoleName roleName);
}
