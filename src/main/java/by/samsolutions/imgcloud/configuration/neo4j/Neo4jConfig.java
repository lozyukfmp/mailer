package by.samsolutions.imgcloud.configuration.neo4j;

import by.samsolutions.imgcloud.dao.UserDao;
import by.samsolutions.imgcloud.nodeentity.BaseEntity;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.session.event.Event;
import org.neo4j.ogm.session.event.EventListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "by.samsolutions.imgcloud.dao")
public class Neo4jConfig {
    public static final String URL = System.getenv("NEO4J_URL") != null ? System.getenv("NEO4J_URL") : "http://neo4j:mailer@localhost:7474";

    @Autowired
    private IdGenerator idGenerator;

    @Bean
    public IdGenerator idGenerator() {
        return new CustomIdGenerator();
    }

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setURI(URL).setCredentials("neo4j", "root");
        return config;
    }

    @Bean
    public SessionFactory sessionFactory() {
        SessionFactory sessionFactory = new SessionFactory(getConfiguration(),"by.samsolutions.imgcloud.nodeentity");
        sessionFactory.register(new EventListenerAdapter() {
            public void onPreSave(Event event) {
                if (event.getObject() instanceof BaseEntity) {
                    BaseEntity baseEntity = (BaseEntity) event.getObject();
                    if (baseEntity.getUuid() == null) {
                        long id;
                        do {
                            id = idGenerator.generateId();
                        } while (idGenerator.exists(id));
                        baseEntity.setUuid(idGenerator.generateId());
                    }
                }
            }
        });

        return sessionFactory;
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }
}