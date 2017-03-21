package by.samsolutions.imgcloud.nodeentity.user;

import by.samsolutions.imgcloud.nodeentity.BaseEntity;
import by.samsolutions.imgcloud.nodeentity.PostNodeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class UserNodeEntity extends BaseEntity {

    private final static Long serialVersionUID = 1L;

    @GraphId
    private Long id;

    @Property
    private String username;
    private String password;
    private boolean enabled;

    @Relationship(type = "PROFILE", direction = Relationship.UNDIRECTED)
    private UserProfileNodeEntity profile;

    @Relationship(type = "ROLE", direction = Relationship.UNDIRECTED)
    private Collection<UserRoleNodeEntity> userRole;

    @Relationship(type = "POSTS", direction = Relationship.UNDIRECTED)
    private Collection<PostNodeEntity> posts;

}
