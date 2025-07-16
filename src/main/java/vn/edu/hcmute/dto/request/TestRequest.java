package vn.edu.hcmute.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class TestRequest {
    private Long examId;
    private List<Byte> partIds;
    private Integer timeLimit; // in minutes
} 