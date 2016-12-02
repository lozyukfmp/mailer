package by.samsolutions.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_profile")
public class UserProfile implements Serializable{

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
