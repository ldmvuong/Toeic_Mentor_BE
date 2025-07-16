package vn.edu.hcmute.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question_groups")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionGroup{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "TEXT")
    private String passageText;

    @Column(columnDefinition = "TEXT")
    private String audioUrl;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToOne(optional = false)            // exam_id
    @JoinColumn(name = "exam_id")
    private Exam exam;

    /* FK tới Part (1–7) */
    @ManyToOne(optional = false)            // part_id
    @JoinColumn(name = "part_id")
    private Part part;

    @OneToMany(mappedBy = "questionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("seqGroup ASC")
    private List<Question> questions = new ArrayList<>();
}