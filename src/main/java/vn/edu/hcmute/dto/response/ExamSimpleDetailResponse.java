package vn.edu.hcmute.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamSimpleDetailResponse {
    private Long id;
    private String code;
    private List<PartSimpleResponse> parts;
} 