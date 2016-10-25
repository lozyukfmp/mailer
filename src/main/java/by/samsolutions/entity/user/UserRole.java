package by.samsolutions.entity.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue
    private Integer id;

}
