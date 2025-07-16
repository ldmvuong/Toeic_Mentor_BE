package vn.edu.hcmute.service;

import org.springframework.web.multipart.MultipartFile;

public interface IExamService {
    void saveExamFromExcel(MultipartFile file) throws Exception;
}
