package by.samsolutions.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.samsolutions.dao.PostDao;
import by.samsolutions.entity.Post;

@Repository
public class PostDaoImpl extends GenericDaoImpl<Post, Integer>
				implements PostDao
{
	@Override
	public List<Post> all() {

		List<Post> postList = entityManager.createNamedQuery("Post.findAll", Post.class)
		                     .getResultList();

		postList.forEach(post -> post.setComments(Collections.EMPTY_SET));

		return postList;
	}

	@Override
	public Post findWithComments(final Integer postId)
	{
		return entityManager.createNamedQuery("Post.findWithComments", Post.class)
		                    .setParameter("id", postId)
		                    .getSingleResult();
	}

}