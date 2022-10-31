package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    public static final String ADD = """
            insert into accident (name, text, address, type_id) values (?, ?, ?, ?)
            """;
    public static final String GET_ALL = """
            select *
            from accident a
            join type t on a.type_id = t.id
            join accident_rule ar on ar.accident_id = a.id
            join rule r on r.id = ar.rule_id
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
        jdbc.update(ADD,
                accident.getName(), accident.getText(),
                accident.getAddress(), accident.getType().getId());
        return Optional.ofNullable(accident);
    }

    public List<Accident> findAll() {
        return jdbc.query(GET_ALL,
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setName(rs.getString("text"));
                    accident.setName(rs.getString("address"));
                    typeJdbcTemplate.findTypeById(rs.getInt("type_id"));
                    accident.setRules((Set<Rule>) ruleJdbcTemplate.getRulesById(accident.getId()));
                    return accident;
                });
    }

    public boolean update(Accident accident) {
        return jdbc.update(UPDATE,
                accident.getName(), accident.getText(), accident.getType(),
                accident.getType().getId(), accident.getId()) > 0;
    }

    public boolean delete(Accident accident) {
        return jdbc.update(DELETE,
                accident.getId()) > 0;
    }

    public Optional<Accident> findById(int id) {
        Accident rsl = (Accident) jdbc.query(FIND_BY_ID,
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
