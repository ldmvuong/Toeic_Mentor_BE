package vn.edu.hcmute.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartResponse {
    private Byte id;
    private String name;
    private int questionCnt;
    private List<GroupResponse> groups;
} 