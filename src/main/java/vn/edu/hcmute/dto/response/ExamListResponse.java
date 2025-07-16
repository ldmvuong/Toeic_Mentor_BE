package vn.edu.hcmute.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamListResponse {
    private List<ExamSummary> content;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExamSummary {
        private Long id;
        private String code;
        private String createdAt;
    }
} 