package ru.dadynsky.demo.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

import javax.sql.DataSource;

public abstract class BaseJdbcTemplateDao {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    protected BaseJdbcTemplateDao(DataSource dataSource) {
        Assert.notNull(dataSource, "dataSource should not be null");
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    protected JdbcTemplate getJdbcTemplate() {
        return namedParameterJdbcTemplate.getJdbcTemplate();
    }
}
