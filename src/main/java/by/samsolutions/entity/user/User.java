package by.samsolutions.entity.user;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_login", nullable = false, length = 45, unique = true)
    private String username;

    @Column(name = "user_password", nullable = false, length = 45)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<UserRole> userRole;

}
