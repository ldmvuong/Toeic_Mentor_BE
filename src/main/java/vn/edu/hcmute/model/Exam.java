package vn.edu.hcmute.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exams")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exam {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String code;

    /* Each exam have 7 part (Part 1‒7) */
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("part.id ASC")
    private List<QuestionGroup> questionGroups = new ArrayList<>();
}