package cn.eddie.live.dao;

import cn.eddie.live.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends JpaRepository<Course, String> {
}
