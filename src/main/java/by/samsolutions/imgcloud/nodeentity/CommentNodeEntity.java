package by.samsolutions.imgcloud.nodeentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class CommentNodeEntity extends BaseEntity {

	private final static Long serialVersionUID = 4L;

	@GraphId
	private Long id;

	@Property
    private Long uuid;
	private Long postId;
	private String username;
	private String text;
	@DateString
	private Date date;

}