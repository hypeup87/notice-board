package com.board.notice.config;

import com.board.notice.constant.NoticeBoardConfigConst;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableJpaRepositories(
        basePackages = NoticeBoardConfigConst.EnvPath.REPOSITORY_PACKAGE,
        entityManagerFactoryRef = NoticeBoardConfigConst.JPA.ENTITY_MANAGER_FACTORY_REF,
        transactionManagerRef = NoticeBoardConfigConst.JPA.TRANSACTION_MANAGER_REF
)
@EnableTransactionManagement
@Configuration
public class DBConfig {
    @Primary
    @Bean(name = NoticeBoardConfigConst.DATASOURCE)
    @ConfigurationProperties(prefix = NoticeBoardConfigConst.ApplicationConf.DATASOURCE)
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = NoticeBoardConfigConst.JPA.ENTITY_MANAGER_FACTORY_REF)
    public LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier(NoticeBoardConfigConst.DATASOURCE) DataSource dataSource
    ) {
        return builder.dataSource(dataSource).packages(NoticeBoardConfigConst.EnvPath.ENTITY_PACKAGE).build();
    }

    @Primary
    @Bean(name = NoticeBoardConfigConst.JPA.TRANSACTION_MANAGER_REF)
    public JpaTransactionManager transactionManager(
            @Qualifier(NoticeBoardConfigConst.JPA.ENTITY_MANAGER_FACTORY_REF) LocalContainerEntityManagerFactoryBean mfBean
    ) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mfBean.getObject());
        return transactionManager;
    }

}