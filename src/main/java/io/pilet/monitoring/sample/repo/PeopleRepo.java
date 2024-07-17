package io.pilet.monitoring.sample.repo;

import io.pilet.monitoring.sample.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PeopleRepo implements RowMapper<Person> {
    private final JdbcTemplate jdbcTemplate;

    public PeopleRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Person person) {
        jdbcTemplate.update(
                "INSERT INTO sample.people (id,name,age,zip) VALUES (?,?,?,?) " +
                        "ON CONFLICT DO NOTHING ",
                person.id(), person.name(), person.age(), person.zip());
    }

    public List<Person> list(int limit) {
        return jdbcTemplate.query(
                "SELECT * FROM sample.people LIMIT ?",
                this, limit);
    }

    public Person get(String id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM sample.people WHERE id = ?",
                this, id);
    }

    public void delete(String id) {
        jdbcTemplate.update("DELETE FROM sample.people WHERE id = ?", id);
    }


    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Person (
                rs.getString("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("zip"));
    }
}
