package by.samsolutions.imgcloud.dao;

import by.samsolutions.imgcloud.nodeentity.PostNodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostDao extends BaseDao<PostNodeEntity, Long> {
	Page<PostNodeEntity> findByUsername(String username, Pageable pageable);
    PostNodeEntity findByUuid(Long uuid);
    Long removeByUuid(Long uuid);
}
