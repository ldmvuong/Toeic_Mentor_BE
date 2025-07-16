package vn.edu.hcmute.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartSimpleResponse {
    private Byte id;
    private String name;
    private int questionCnt;
    private Set<String> tags;
} 