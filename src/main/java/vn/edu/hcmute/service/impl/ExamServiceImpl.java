package vn.edu.hcmute.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmute.dto.request.ExcelRowDTO;
import vn.edu.hcmute.model.*;
import vn.edu.hcmute.repository.*;
import vn.edu.hcmute.service.IExamService;
import vn.edu.hcmute.util.excel.ExcelUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "EXAM-SERVICE")
public class ExamServiceImpl implements IExamService {

    private final ExamRepository examRepo;
    private final PartRepository partRepo;
    private final QuestionGroupRepository questionGrRepo;
    private final QuestionRepository questionRepo;
    private final TagRepository tagRepo;

    @Override
    @Transactional
    public void saveExamFromExcel(MultipartFile file) throws Exception {
        try (Workbook wb = new XSSFWorkbook(file.getInputStream())) {
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i);
                String examCode = Optional.ofNullable(sheet.getSheetName())
                        .orElse("SHEET_" + (i + 1));

                Exam exam = examRepo.save(
                        Exam.builder()
                                .code(examCode)
                                .build()
                );

                Map<String, QuestionGroup> cache = new HashMap<>();
                for (ExcelRowDTO dto : ExcelUtils.toDtoList(sheet)) {
                    processExcelRow(dto, exam, cache);
                }
            }
        }
    }

    private void processExcelRow(ExcelRowDTO dto, Exam exam, Map<String, QuestionGroup> groupMap) {
        Part part = partRepo.findById(dto.getPartId())
                .orElseThrow(() -> new RuntimeException("Part not found: " + dto.getPartId()));
        QuestionGroup group = groupMap.get(dto.getGroupKey());
        if (group == null) {
            group = new QuestionGroup();
            group.setExam(exam);
            group.setPart(part);
            group.setPassageText(dto.getPassageText());
            group.setAudioUrl(dto.getAudioUrl());
            group.setImageUrl(dto.getImageUrl());
            group = questionGrRepo.save(group);
            groupMap.put(dto.getGroupKey(), group);
        }
        Question q = new Question();
        q.setQuestionGroup(group);
        q.setSeqGroup(dto.getSeqNumber());
        q.setQuestionText(dto.getQuestionText());
        q.setOptionA(dto.getOptionA());
        q.setOptionB(dto.getOptionB());
        q.setOptionC(dto.getOptionC());
        q.setOptionD(dto.getOptionD());
        q.setCorrectAnswer(dto.getCorrectAnswer());
        q.setTags(parseAndSaveTags(dto.getTags()));
        questionRepo.save(q);
    }

    private Set<Tag> parseAndSaveTags(String tags) {
        Set<Tag> tagSet = new HashSet<>();
        if (tags != null && !tags.isBlank()) {
            String[] tagArr = tags.split(";");
            for (String tagName : tagArr) {
                String cleanTag = tagName.trim();
                if (!cleanTag.isEmpty()) {
                    Tag tag = tagRepo.findByName(cleanTag)
                            .orElseGet(() -> tagRepo.save(new Tag(cleanTag)));
                    tagSet.add(tag);
                }
            }
        }
        return tagSet;
    }
}
