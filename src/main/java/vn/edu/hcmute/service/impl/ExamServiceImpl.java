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
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            for (int sheetIdx = 0; sheetIdx < workbook.getNumberOfSheets(); sheetIdx++) {
                Sheet sheet = workbook.getSheetAt(sheetIdx);
                String examCode = sheet.getSheetName();
                List<ExcelRowDTO> rows = parseSheetToDTOs(sheet);
                if (examCode == null) {
                    examCode = "SHEET_" + (sheetIdx + 1);
                }
                Exam exam = new Exam();
                exam.setCode(examCode);
                exam = examRepo.save(exam);
                Map<String, QuestionGroup> groupMap = new HashMap<>();
                for (ExcelRowDTO dto : rows) {
                    processExcelRow(dto, exam, groupMap);
                }
            }
        }
    }

    private List<ExcelRowDTO> parseSheetToDTOs(Sheet sheet) {
        List<ExcelRowDTO> rows = new ArrayList<>();
        Iterator<Row> it = sheet.iterator();
        if (it.hasNext()) it.next(); // skip header
        while (it.hasNext()) {
            Row row = it.next();
            ExcelRowDTO dto = new ExcelRowDTO();
            dto.setPartId(getByteCell(row, 0));
            dto.setGroupKey(getStringCell(row, 1));
            dto.setSeqNumber(getShortCell(row));
            dto.setPassageText(getStringCell(row, 3));
            dto.setQuestionText(getStringCell(row, 4));
            dto.setOptionA(getStringCell(row, 5));
            dto.setOptionB(getStringCell(row, 6));
            dto.setOptionC(getStringCell(row, 7));
            dto.setOptionD(getStringCell(row, 8));
            dto.setCorrectAnswer(getStringCell(row, 9));
            dto.setAudioUrl(getStringCell(row, 10));
            dto.setImageUrl(getStringCell(row, 11));
            dto.setTags(getStringCell(row, 12));
            rows.add(dto);
        }
        return rows;
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

    // Hàm hỗ trợ đọc cell null-safe
    private String getStringCell(Row row, int idx) {
        Cell cell = row.getCell(idx);
        return cell == null ? null : cell.toString().trim();
    }
    private Byte getByteCell(Row row, int idx) {
        Cell cell = row.getCell(idx);
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) return (byte) cell.getNumericCellValue();
        try { return Byte.parseByte(cell.toString().trim()); }
        catch (Exception e) { return null; }
    }
    private Short getShortCell(Row row) {
        Cell cell = row.getCell(2);
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) return (short) cell.getNumericCellValue();
        try { return Short.parseShort(cell.toString().trim()); }
        catch (Exception _) { return null; }
    }
}
