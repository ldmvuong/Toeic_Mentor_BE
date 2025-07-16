package vn.edu.hcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmute.model.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByName(String name);
    Optional<Tag> findByName(String name);
} 