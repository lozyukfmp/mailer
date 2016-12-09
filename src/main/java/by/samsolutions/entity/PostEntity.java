package by.samsolutions.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "post_text")
	private String text;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_date")
	private Date date;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", updatable = false)
	private Collection<CommentEntity> comments;

}
