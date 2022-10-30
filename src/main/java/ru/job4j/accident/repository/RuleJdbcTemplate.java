package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RuleJdbcTemplate {

    public static final String ADD = """
            insert into rule (name) values (?)
            """;
    public static final String GET_ALL = """
            select id, name from rule
            """;
    public static final String UPDATE = """
            update rule set name = ? where id = ?
            """;

    public static final String FIND_BY_ID = """
            select id, name from rule where id = ?
            """;

    private final JdbcTemplate jdbc;

    public Optional<Rule> add(Rule rule) {
        jdbc.update(ADD,
                rule.getName());
        return Optional.ofNullable(rule);
    }

    public boolean update(Rule rule) {
        return jdbc.update(UPDATE,
                rule.getName(), rule.getId()) > 0;
    }

    public Optional<Rule> findById(int id) {
        Rule rsl = (Rule) jdbc.query(FIND_BY_ID,
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);
        return Optional.ofNullable(rsl);
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

    public Set<Rule> getByIds(Set<Integer> ids) {
        return ids.stream().map(
                id -> {
                    Rule rsl = (Rule) jdbc.query(FIND_BY_ID,
                            (rs, row) -> {
                                Rule rule = new Rule();
                                rule.setId(rs.getInt("id"));
                                rule.setName(rs.getString("name"));
                                return rule;
                            }, id);
                    return rsl;
                }
        ).collect(Collectors.toSet());
    }
}
