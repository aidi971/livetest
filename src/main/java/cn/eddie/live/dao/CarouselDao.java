package cn.eddie.live.dao;

import cn.eddie.live.model.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarouselDao extends JpaRepository<Carousel, String> {
}
