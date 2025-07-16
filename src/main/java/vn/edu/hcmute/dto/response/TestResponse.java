package vn.edu.hcmute.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestResponse {
    private Long examId;
    private String code;
    private Integer timeLimit;
    private List<PartResponse> parts;
} 