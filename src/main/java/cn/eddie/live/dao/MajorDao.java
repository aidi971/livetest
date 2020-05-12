package cn.eddie.live.dao;

import cn.eddie.live.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorDao extends JpaRepository<Major, String> {
}
