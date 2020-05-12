package cn.eddie.live.dao;

import cn.eddie.live.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherDao extends JpaRepository<Teacher, String> {
    Optional<Teacher> findByIdAndPassword(String id, String password);
}
