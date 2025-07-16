package vn.edu.hcmute.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)            // group_id
    @JoinColumn(name = "group_id")
    private QuestionGroup questionGroup;

    @Column(name = "number_in_group")
    private Short seqGroup;                 // 1..n

    @Column(columnDefinition = "TEXT")
    private String questionText;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    @Column(nullable = false, length = 1)
    private String correctAnswer;           // ‘A’ | ‘B’ | …

    @Column(columnDefinition = "TEXT")
    private String explanation;

    /* Tag N‒N */
    @ManyToMany
    @JoinTable(name = "question_tag",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();
}
