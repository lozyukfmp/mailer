package by.samsolutions.entity.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_profile")
public class UserProfile implements Serializable{

    /*@GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user"))
    @GeneratedValue(generator = "generator")*/
    @Id
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    /*@OneToOne(fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    private User user;*/

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "secondname")
    private String secondName;

    @Column(name = "thirdname")
    private String thirdName;

    @Column(name = "email")
    private String email;

}
