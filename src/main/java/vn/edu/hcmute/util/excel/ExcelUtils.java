package vn.edu.hcmute.util.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import vn.edu.hcmute.dto.request.ExcelRowDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Excel-related helper methods used across the project. */
public final class ExcelUtils {

    private ExcelUtils() {}          // static-only

    // ---------- basic cell readers -------------------------------------------------

    public static String getString(Cell cell) {
        return cell == null ? null : cell.toString().trim();
    }

    public static Byte getByte(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC)
            return (byte) cell.getNumericCellValue();
        try { return Byte.parseByte(cell.toString().trim()); }
        catch (NumberFormatException _) { return null; }
    }

    public static Short getShort(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC)
            return (short) cell.getNumericCellValue();
        try { return Short.parseShort(cell.toString().trim()); }
        catch (NumberFormatException _) { return null; }
    }

    // ---------- higher-level convenience ------------------------------------------

    /**
     * Parse an Apache POI {@link Sheet} into a list of {@link ExcelRowDTO}.
     * Assumes: header row is the first row.
     */
    public static List<ExcelRowDTO> toDtoList(Sheet sheet) {
        List<ExcelRowDTO> list = new ArrayList<>();
        Iterator<Row> it = sheet.iterator();
        if (it.hasNext()) it.next();                // skip header
        while (it.hasNext()) {
            Row row = it.next();
            ExcelRowDTO dto = new ExcelRowDTO();
            dto.setPartId   (getByte (row.getCell(0)));
            dto.setGroupKey (getString(row.getCell(1)));
            dto.setSeqNumber(getShort (row.getCell(2)));
            dto.setPassageText  (getString(row.getCell(3)));
            dto.setQuestionText (getString(row.getCell(4)));
            dto.setOptionA  (getString(row.getCell(5)));
            dto.setOptionB  (getString(row.getCell(6)));
            dto.setOptionC  (getString(row.getCell(7)));
            dto.setOptionD  (getString(row.getCell(8)));
            dto.setCorrectAnswer(getString(row.getCell(9)));
            dto.setAudioUrl (getString(row.getCell(10)));
            dto.setImageUrl (getString(row.getCell(11)));
            dto.setTags     (getString(row.getCell(12)));
            list.add(dto);
        }
        return list;
    }
}