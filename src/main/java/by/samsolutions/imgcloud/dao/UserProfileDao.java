package by.samsolutions.imgcloud.dao;

import by.samsolutions.imgcloud.nodeentity.user.UserProfileNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserProfileDao extends BaseDao<UserProfileNodeEntity, Long> {
    UserProfileNodeEntity findByUsername(String username);
}
