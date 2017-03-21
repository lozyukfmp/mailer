package by.samsolutions.imgcloud.nodeentity.user;

import by.samsolutions.imgcloud.nodeentity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class UserProfileNodeEntity extends BaseEntity {

	private final static Long serialVersionUID = 2L;

	@GraphId
	private Long id;

	@Property
	private String username;
	private String firstName;
	private String secondName;
	private String thirdName;
	private String email;
	private String imageUrl;

}
