package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TypeJdbcTemplate {

    public static final String GET_ALL = """
            select id, name from type
            """;
    public static final String FIND_BY_ID = """
            select id, name from type where id = ?
            """;

    private final JdbcTemplate jdbc;

    public Optional<AccidentType> findTypeById(int id) {
        return Optional.ofNullable(jdbc.queryForObject(FIND_BY_ID,
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                }, id));
    }

    public Collection<AccidentType> findAllTypes() {
        return jdbc.query(GET_ALL,
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }
}
