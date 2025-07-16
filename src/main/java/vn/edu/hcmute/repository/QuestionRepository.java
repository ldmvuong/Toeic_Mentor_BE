package vn.edu.hcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmute.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
} 