package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> getCity(@PathVariable long id) throws JsonProcessingException {
        Optional<City> cityOptional = cityRepository.findById(id);
        City city = cityOptional.orElseThrow(() -> new CityNotFoundException("City Not Found"));
        String response = weatherService.getWetherByCity(city.getName());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> jsonMap = objectMapper.readValue(response,
                new TypeReference<Map<String,String>>(){});
        return jsonMap;
    }
    @GetMapping(path = "/search")
    public List<Map<String,String>> getCities(@RequestParam(required = false) String name) throws JsonProcessingException {
        List<City> cities = new ArrayList<>();
        if (name == null) {
            cities = cityRepository.findAllByOrderByName();
        } else {
            cities = cityRepository.findAllByNameStartingWithIgnoreCase(name);
        }
        if (cities.isEmpty()) {
            throw new CityNotFoundException("City Not Found");
        }
        List<Map<String, String>> response = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (City city : cities) {
            String weather = weatherService.getWetherByCity(city.getName());
            Map<String, String> jsonMap = objectMapper.readValue(weather,
                    new TypeReference<Map<String,String>>(){});
            Map<String, String> filteredMap = new HashMap<>();
            filteredMap.put("name", jsonMap.get("name"));
            filteredMap.put("temperature", jsonMap.get("temperature"));
            response.add(filteredMap);
        }

        return response;
    }
    // END
}

