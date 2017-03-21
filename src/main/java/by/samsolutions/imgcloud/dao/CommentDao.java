package by.samsolutions.imgcloud.dao;

import by.samsolutions.imgcloud.nodeentity.CommentNodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentDao extends BaseDao<CommentNodeEntity, Long> {
	Page<CommentNodeEntity> findByPostId(Long postId, Pageable pageable);
    CommentNodeEntity findByUuid(Long uuid);
    Long removeByUuid(Long uuid);
}
