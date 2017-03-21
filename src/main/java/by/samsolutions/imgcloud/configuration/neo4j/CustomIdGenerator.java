package by.samsolutions.imgcloud.configuration.neo4j;

import by.samsolutions.imgcloud.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class CustomIdGenerator implements IdGenerator {

    @Autowired
    private UserDao userDao;

    @Override
    public Long generateId() {
        return ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
    }

    @Override
    public boolean exists(Long id) {
        return userDao.fetchAllUUIDs().contains(id);
    }
}
