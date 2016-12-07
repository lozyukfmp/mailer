package by.samsolutions.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.samsolutions.dao.PostDao;
import by.samsolutions.entity.Post;

@Repository
public class PostDaoImpl extends GenericDaoImpl<Post, Integer> implements PostDao
{

	public PostDaoImpl()
	{
		super(Post.class);
	}

	@Override
	public List<Post> all(final String username, final Integer messageCount)
	{
		List<Post> postList = entityManager.createNamedQuery("Post.findAllByUsername", Post.class)
		                                   .setParameter("username", username)
		                                   .setFirstResult(0)
		                                   .setMaxResults(messageCount)
		                                   .getResultList();

		postList.forEach(post -> post.setComments(Collections.EMPTY_SET));

		return postList;
	}

	@Override
	public Post findWithComments(final Integer postId)
	{
		return entityManager.createNamedQuery("Post.findByIdWithComments", Post.class).setParameter("id", postId).getSingleResult();
	}

}
