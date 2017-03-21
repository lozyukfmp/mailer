package by.samsolutions.imgcloud.configuration.neo4j;

public interface IdGenerator {
    Long generateId();
    boolean exists(Long id);
}
