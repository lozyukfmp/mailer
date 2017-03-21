package by.samsolutions.imgcloud.nodeentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class PostNodeEntity extends BaseEntity {
	private final static Long serialVersionUID = 5L;

	@GraphId
	private Long id;

    @Property
    private Long uuid;
	private String username;
    private String text;
    private String imageUrl;
    @DateString
	private Date date;

	@Relationship(type = "COMMENTS", direction = Relationship.UNDIRECTED)
	private Set<CommentNodeEntity> comments = new HashSet<>();

}
