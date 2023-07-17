package exercise;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

// BEGIN
@RestController
// Класс контроллера
public class WelcomeController {

    @GetMapping("/")
    @ResponseBody
    public String welcomeUser(@RequestParam(value = "name", defaultValue = "Spring") String name) {
        return String.format("Welcome to %s", name);
    }
    @GetMapping("/hello")
    @ResponseBody
    public String getName(@RequestParam(defaultValue = "World") String name) {
        return String.format("Hello, %s", name);
    }
}
// END