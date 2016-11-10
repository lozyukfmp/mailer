package by.samsolutions.dao.impl;

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

		return  entityManager.createNamedQuery("Post.findAll", Post.class)
		                     .getResultList();
	}
}
