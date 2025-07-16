package vn.edu.hcmute.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDetailResponse {
    private Long id;
    private String code;
    private String createdAt;
    private List<String> tags;
    private List<PartResponse> parts;
} 