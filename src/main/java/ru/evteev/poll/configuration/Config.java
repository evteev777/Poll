package ru.evteev.poll.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Data;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan("ru.evteev.poll")
@PropertySource("classpath:application.properties")
@EnableWebMvc
@EnableTransactionManagement
@Data
public class Config {

    private final Environment env;

    private static final String JDBC_DRIVER = "jdbc.driverClass";
    private static final String JDBC_URL = "jdbc.url";
    private static final String JDBC_USER = "jdbc.user";
    private static final String JDBC_PASSWORD = "jdbc.password";
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String HIBERNATE_CHAR_SET = "hibernate.charSet";
    private static final String HIBERNATE_CHARACTER_ENCODING = "hibernate.characterEncoding";
    private static final String HIBERNATE_USE_UNICODE = "hibernate.useUnicode";

    @Bean
    public Flyway flyway() {

        Flyway flyway = Flyway.configure()
                .dataSource(
                        env.getProperty(JDBC_URL),
                        env.getProperty(JDBC_USER),
                        env.getProperty(JDBC_PASSWORD))
                .load();
        flyway.info();
        flyway.repair();
        flyway.migrate();
        return flyway;
    }

    @Bean
    @DependsOn("flyway")
    public DataSource dataSource() {
        final ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(env.getProperty(JDBC_DRIVER));
            dataSource.setJdbcUrl(env.getProperty(JDBC_URL));
            dataSource.setUser(env.getProperty(JDBC_USER));
            dataSource.setPassword(env.getProperty(JDBC_PASSWORD));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ru.evteev.poll.entity");

        Properties hibernateProps = new Properties();
        hibernateProps.setProperty(HIBERNATE_DIALECT, env.getProperty(HIBERNATE_DIALECT));
        hibernateProps.setProperty(HIBERNATE_HBM2DDL_AUTO, env.getProperty(HIBERNATE_HBM2DDL_AUTO));
        hibernateProps.setProperty(HIBERNATE_SHOW_SQL, env.getProperty(HIBERNATE_SHOW_SQL));
        hibernateProps.setProperty(HIBERNATE_CHAR_SET, env.getProperty(HIBERNATE_CHAR_SET));
        hibernateProps.setProperty(HIBERNATE_CHARACTER_ENCODING, env.getProperty(HIBERNATE_CHARACTER_ENCODING));
        hibernateProps.setProperty(HIBERNATE_USE_UNICODE, env.getProperty(HIBERNATE_USE_UNICODE));
        sessionFactory.setHibernateProperties(hibernateProps);

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
