package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN

    @GetMapping(path = "/{id}/previous")
    public Iterable<Course> getCourses(@PathVariable long id) {
        Course course = (Course) courseRepository.findById(id);
        String path = course.getPath();
        if (path == null) {
            List<Course> coursesList = new ArrayList<>();
            return coursesList;
        }
        String[] courses = path.split("\\.");
        List<Long> ids = Arrays.stream(courses)
                .map(s -> Long.parseLong(s))
                .toList();
        return courseRepository.findAllById(ids);
    }
    // END

}
