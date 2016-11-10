package by.samsolutions.entity.user;

import by.samsolutions.entity.Post;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name="User.findAll", query = "select u from User u"),
        @NamedQuery(name="User.findByUsername", query = "select u from User u where u.username = :username"),
        @NamedQuery(name="User.deleteByUsername", query = "delete from User u where u.username = :username")
})
public class User implements Serializable {
    @Id
    @Column(name = "username", nullable = false, length = 45, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserProfile profile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> userRole;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Post> posts;

    @Column(name = "enabled")
    private boolean enabled;

}
