package by.samsolutions.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.samsolutions.dao.CommentDao;
import by.samsolutions.entity.Comment;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment, Integer>
				implements CommentDao
{
	@Override
	public List<Comment> all() {

		return entityManager.createNamedQuery("Comment.findAll", Comment.class)
		                                           .getResultList();
	}
}
