package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RuleJdbcTemplate {

    public static final String ADD = """
            insert into accident_rule (accident_id, rule_id) values (?, ?)
            """;
    public static final String GET_ALL = """
            select id, name from rule
            """;
    public static final String UPDATE = """
            delete from accident_rule where accident_id = ?
            """;

    public static final String FIND_BY_ID = """
            select id, name from rule where id = ?
            """;
    public static final String FIND_BY_IDS = """
            select * from rule where id in (select rule_id from accident_rule where
            accident_id = ?)
            """;

    private final JdbcTemplate jdbc;

    public void add(Collection<Rule> rules, int accidentID) {
        rules.forEach(rule -> jdbc.update(ADD, accidentID, rule.getId()));
    }

    public void update(Collection<Rule> rules, int accidentID) {
        jdbc.update(UPDATE, accidentID);
        add(rules, accidentID);
    }

    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject(FIND_BY_ID,
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id));
    }

    public Collection<Rule> findAll() {
        return jdbc.query(GET_ALL,
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public Collection<Rule> getRulesById(int id) {
        return jdbc.query(FIND_BY_IDS,
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);
    }
}
