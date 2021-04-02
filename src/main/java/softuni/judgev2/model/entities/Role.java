package softuni.judgev2.model.entities;

import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{
    private RoleName name;

    public Role() {
    }

    public Role (RoleName name){
        this.setName(name);
    }

    @Enumerated(EnumType.STRING)
    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
