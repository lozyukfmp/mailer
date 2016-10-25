package by.samsolutions.entity.user;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

    private Integer userRoleId;

    private User user;

    private String role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    public Integer getUserRoleId() {
        return userRoleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {
        return role;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userRoleId=" + userRoleId +
                ", role='" + role + '\'' +
                '}';
    }
}
