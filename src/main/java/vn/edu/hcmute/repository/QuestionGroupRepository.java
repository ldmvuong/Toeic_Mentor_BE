package vn.edu.hcmute.repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.hcmute.model.Exam;
import vn.edu.hcmute.model.QuestionGroup;

import java.util.List;

public interface QuestionGroupRepository extends JpaRepository<QuestionGroup, Long> {
    @EntityGraph(attributePaths = {"questions"})
    List<QuestionGroup> findByExam(Exam exam);

    @Query("SELECT qs FROM QuestionGroup qs JOIN FETCH qs.questions WHERE qs.exam = :exam")
    List<QuestionGroup> findAllWithQuestionsByExam(@Param("exam") Exam exam);

    @EntityGraph(attributePaths = {"questions", "questions.tags"})
    List<QuestionGroup> findWithQuestionsAndTagsByExam(Exam exam);
} 