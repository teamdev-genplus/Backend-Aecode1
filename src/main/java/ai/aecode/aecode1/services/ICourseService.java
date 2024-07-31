package ai.aecode.aecode1.services;

import ai.aecode.aecode1.entities.Course;

import java.util.List;

public interface ICourseService {
    public void insert(Course course);
    List<Course> list();
    public void delete(int id_course);
    public Course listId(int id_course);
}
