package by.samsolutions.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import by.samsolutions.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "user_profile")
public class UserProfileEntity implements BaseEntity
{

	private final static Long serialVersionUID = 2L;

	@Id
	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "secondname")
	private String secondName;

	@Column(name = "thirdname")
	private String thirdName;

	@Column(name = "email")
	private String email;

	@Column(name = "image_url")
	private String imageUrl;
}
