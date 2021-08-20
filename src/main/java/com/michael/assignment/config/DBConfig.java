package com.michael.assignment.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author michaelwang on 2021-08-18
 */

@Configuration
@ComponentScan
@EntityScan("com.michael.assignment")
@EnableJpaRepositories("com.michael.assignment.repository")
public class DBConfig {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public DataSource dataSource() {
        logger.info("dataSource() invoked");

        DataSource dataSource = (new EmbeddedDatabaseBuilder())
                .addScript("classpath:db/schema.sql")
                .build();

        logger.info("dataSource = " + dataSource);

        // Sanity check
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> dJIndex = jdbcTemplate.queryForList("SELECT * FROM DJIndex");
        logger.info("System has " + dJIndex.size() + " dJIndices");

        return dataSource;
    }
}
