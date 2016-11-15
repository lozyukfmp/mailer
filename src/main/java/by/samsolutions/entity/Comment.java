package by.samsolutions.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import by.samsolutions.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
@NamedQueries({
				@NamedQuery(name="Comment.findAll", query = "select c from Comment c"),
})
public class Comment
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Integer id;

	@Column(name = "post_id")
	private Integer post_id;

	@Column(name = "username")
	private String username;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "comment_date")
	private Date date;

	@Column(name = "comment_text")
	private String text;

}