package by.samsolutions.entity;

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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "posts")
@NamedQueries({
				@NamedQuery(name="Post.findAll", query = "select p from Post p order by p.date desc"),
				@NamedQuery(name="Post.findWithComments", query = "select p from Post p left join fetch p.comments c where p.id = :id"),
})
public class Post
{
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

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "post_id", updatable = false)
	private Collection<Comment> comments;

}
