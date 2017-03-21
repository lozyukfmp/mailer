package by.samsolutions.imgcloud.dao;

import by.samsolutions.imgcloud.nodeentity.user.UserNodeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao extends BaseDao<UserNodeEntity, Long> {
	List<UserNodeEntity> findByUsername(Pageable pageable);
    UserNodeEntity findByUsername(String username);
    List<UserNodeEntity> findByUsernameContaining(String username);
}
