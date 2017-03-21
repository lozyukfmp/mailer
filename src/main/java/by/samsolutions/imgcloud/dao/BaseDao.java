package by.samsolutions.imgcloud.dao;

import by.samsolutions.imgcloud.nodeentity.BaseEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<Entity extends BaseEntity, PK extends Serializable> extends Neo4jRepository<Entity, PK> {
    @Query("MATCH (n) WHERE EXISTS(n.uuid)return n.uuid AS uuid")
    List<Long> fetchAllUUIDs();
    Entity findByUuid(PK uuid);
    PK removeByUuid(PK uuid);
}
