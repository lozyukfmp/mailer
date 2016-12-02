package by.samsolutions.entity.user;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import by.samsolutions.entity.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
@NamedQueries({
				@NamedQuery(name="User.findAll", query = "select u from User u")
})
public class User implements Serializable {
    @Id
    @Column(name = "username", nullable = false, length = 45, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "username")
    private UserProfile profile;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "username")
    private Collection<UserRole> userRole;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "username")
    private Collection<Post> posts;

    @Column(name = "enabled")
    private boolean enabled;

}
