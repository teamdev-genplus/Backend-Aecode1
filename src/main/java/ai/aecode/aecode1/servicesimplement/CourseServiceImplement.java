package ai.aecode.aecode1.servicesimplement;

import ai.aecode.aecode1.entities.Course;
import ai.aecode.aecode1.repositories.ICourseRepository;
import ai.aecode.aecode1.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImplement implements ICourseService {
    @Autowired
    private ICourseRepository cR;
    @Override
    public void insert(Course course) {
        cR.save(course);
    }

    @Override
    public List<Course> list() {
        return cR.findAll();
    }

    @Override
    public void delete(int id_course) {
        cR.deleteById(id_course);
    }

    @Override
    public Course listId(int id_course) {
        return cR.findById(id_course).orElse(new Course());
    }

}
