package vn.edu.hcmute.dto.request;

import lombok.Data;

@Data
public class ExcelRowDTO {
    private Byte partId;
    private String groupKey;
    private Short seqNumber;
    private String passageText;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private String audioUrl;
    private String imageUrl;
    private String tags;
}