package by.samsolutions.imgcloud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "comments")
@NamedQueries({
				@NamedQuery(name = "Comment.findAllByPostId", query = "select c from CommentEntity c " +
								"where c.postId = :id order by c.date desc")
})
public class CommentEntity implements BaseEntity
{

	private final static Long serialVersionUID = 4L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private int id;

	@Column(name = "post_id", updatable = false)
	private int postId;

	@Column(name = "username")
	private String username;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "comment_date")
	private Date date;

	@Column(name = "comment_text")
	private String text;

}