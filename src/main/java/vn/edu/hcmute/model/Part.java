package vn.edu.hcmute.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Part{

    @Id        // 1..7
    private Byte id;

    @Column(nullable = false)
    private String name;

    @Column(name = "question_cnt", nullable = false)
    private Short questionCnt;
}