package exercise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {


    private final JdbcTemplate jdbc;


    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(path = "")
    @ResponseBody
    public List<Map<String, Object>> getPersons() {
        String query = "SELECT * FROM person";
        List<Map<String, Object>> persons = jdbc.queryForList(query);
        return persons;
    }
    @GetMapping(path = "/{id}")
    @ResponseBody
    public Map<String, Object> getPerson(@PathVariable Long id) {
        String query = "SELECT * FROM person WHERE id = ?";
        Map<String, Object> person = jdbc.queryForMap(query, id);
        return person;
    }
    // END
}
