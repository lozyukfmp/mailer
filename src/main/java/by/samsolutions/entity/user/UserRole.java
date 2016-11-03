package by.samsolutions.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "user")
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

}
