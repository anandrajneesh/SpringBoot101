package org.gluecoders.learn.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Anand_Rajneesh on 2/17/2017.
 */
/*@Configuration
@EnableTransactionManagement
@PropertySource("classpath:hibernate.properties")*/
public class Database {

    @Value("${hibernate.connection.url}")
    private String jdbcUrl;
    @Value("${hibernate.connection.username}")
    private String username;
    @Value("${hibernate.connection.password}")
    private String password;

    //@Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource c3p0DataSource = new ComboPooledDataSource();
        c3p0DataSource.setPassword(password);
        c3p0DataSource.setDriverClass(org.postgresql.Driver.class.getName());
        c3p0DataSource.setUser(username);
        c3p0DataSource.setJdbcUrl(jdbcUrl);
        c3p0DataSource.setMinPoolSize(10);
        c3p0DataSource.setMaxPoolSize(200);
        return c3p0DataSource;
    }

   // @Bean
    public SessionFactory sessionFactory(DataSource dataSource) throws IOException {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("org.gluecoders.learn.models");
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", org.hibernate.dialect.PostgreSQL94Dialect.class.getName());
        hibernateProperties.put("hibernate.show_sql", true);
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        localSessionFactoryBean.afterPropertiesSet();
        return localSessionFactoryBean.getObject();
    }

   // @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager =
                new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    //@Bean
    public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory){
        return new HibernateTemplate(sessionFactory);
    }

}
