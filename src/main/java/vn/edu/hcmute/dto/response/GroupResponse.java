package vn.edu.hcmute.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {
    private Long id;
    private String passageText;
    private String audioUrl;
    private String imageUrl;
    private List<QuestionResponse> questions;
} 