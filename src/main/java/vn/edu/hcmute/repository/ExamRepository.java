package vn.edu.hcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmute.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
} 