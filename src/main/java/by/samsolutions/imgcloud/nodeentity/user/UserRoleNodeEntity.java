package by.samsolutions.imgcloud.nodeentity.user;

import by.samsolutions.imgcloud.nodeentity.BaseEntity;
import lombok.*;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class UserRoleNodeEntity extends BaseEntity {
	private final static Long serialVersionUID = 3L;

	@GraphId
	private Long id;

	@Property
	private String username;
	private String role;
}
