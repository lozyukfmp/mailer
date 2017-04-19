package by.samsolutions.imgcloud.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "posts")
@NamedQueries({
				@NamedQuery(name = "Post.findAllByUsername", query = "select p from PostEntity p " +
								"where p.username = :username order by p.date desc"),
				@NamedQuery(name = "Post.findByIdWithComments", query = "select p from PostEntity p " +
								"left join fetch p.comments c where p.id = :id"),
})
public class PostEntity implements BaseEntity
{
	private final static Long serialVersionUID = 5L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "post_text")
	private String text;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_date")
	private Date date;

	@Lob
	@Column(name = "image")
	private byte[] image;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", updatable = false)
	private Collection<CommentEntity> comments;

}