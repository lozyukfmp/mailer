package by.samsolutions.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import by.samsolutions.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "user")
@Builder
@Entity
@Table(name = "user_role")
public class UserRoleEntity implements BaseEntity
{

	private final static Long serialVersionUID = 3L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_role_id", unique = true, nullable = false)
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "role", nullable = false, length = 45)
	private String role;
}
