package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {

    public static final String ADD = """
            insert into accident (name, text, address, type_id) values (?, ?, ?, ?)
            """;
    public static final String GET_ALL = """
            select *
            from accident
            """;
    public static final String UPDATE = """
            update accident set name = ?, text = ?, address = ?, type_id = ? where id = ?
            """;
    public static final String DELETE = """
            delete from accident where id = ?
            """;
    public static final String FIND_BY_ID = """
            select id, name, text, address, type_id from accident where id = ?
            """;

    private final JdbcTemplate jdbc;
    private final TypeJdbcTemplate typeJdbcTemplate;
    private final RuleJdbcTemplate ruleJdbcTemplate;

    public Optional<Accident> add(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        ruleJdbcTemplate.add(accident.getRules(), (Integer) keyHolder.getKeys().get("id"));
        return Optional.ofNullable(accident);
    }

    public List<Accident> findAll() {
        return jdbc.query(GET_ALL,
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    typeJdbcTemplate.findTypeById(rs.getInt("type_id")).ifPresent(accident::setType);
                    accident.setRules(Set.copyOf(ruleJdbcTemplate.getRulesById(accident.getId())));
                    return accident;
                });
    }

    public boolean update(Accident accident) {
        ruleJdbcTemplate.update(accident.getRules(), accident.getId());
        return jdbc.update(UPDATE,
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), accident.getId()) > 0;
    }

    public boolean delete(Accident accident) {
        return jdbc.update(DELETE,
                accident.getId()) > 0;
    }

    public Optional<Accident> findById(int id) {
        Accident rsl = jdbc.queryForObject(FIND_BY_ID,
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setName(rs.getString("text"));
                    accident.setName(rs.getString("address"));
                    typeJdbcTemplate.findTypeById(rs.getInt("type_id"));
                    return accident;
                }, id);
        return Optional.ofNullable(rsl);
    }
}
