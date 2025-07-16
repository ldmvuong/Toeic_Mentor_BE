package vn.edu.hcmute.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.edu.hcmute.model.Tag;
import vn.edu.hcmute.repository.TagRepository;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "INIT-TAG-RUNNER")
public class InitTagRunner implements CommandLineRunner {

    private final TagRepository tagRepository;

    @Override
    public void run(String... args) {
        String[] tags = {
                // PART 1
                "[Part 1] Tranh tả người",
                "[Part 1] Tranh tả vật",
                "[Part 1] Tranh tả cả người và vật",
                // PART 2
                "[Part 2] Câu hỏi WHAT",
                "[Part 2] Câu hỏi WHO",
                "[Part 2] Câu hỏi WHERE",
                "[Part 2] Câu hỏi WHEN",
                "[Part 2] Câu hỏi HOW",
                "[Part 2] Câu hỏi WHY",
                "[Part 2] Câu hỏi YES/NO",
                "[Part 2] Câu hỏi đuôi",
                "[Part 2] Câu hỏi lựa chọn",
                "[Part 2] Câu yêu cầu, đề nghị",
                "[Part 2] Câu trần thuật",
                // PART 3
                "[Part 3] Câu hỏi về chủ đề, mục đích",
                "[Part 3] Câu hỏi về danh tính người nói",
                "[Part 3] Câu hỏi về chi tiết cuộc hội thoại",
                "[Part 3] Câu hỏi về hành động tương lai",
                "[Part 3] Câu hỏi kết hợp bảng biểu",
                "[Part 3] Câu hỏi về hàm ý câu nói",
                "[Part 3] Chủ đề: Company - General Office Work",
                "[Part 3] Chủ đề: Company - Personnel",
                "[Part 3] Chủ đề: Company - Business, Marketing",
                "[Part 3] Chủ đề: Company - Event, Project",
                "[Part 3] Chủ đề: Company - Facility",
                "[Part 3] Chủ đề: Shopping, Service",
                "[Part 3] Chủ đề: Housing",
                "[Part 3] Câu hỏi về yêu cầu, gợi ý",
                // PART 4
                "[Part 4] Câu hỏi về chủ đề, mục đích",
                "[Part 4] Câu hỏi về danh tính, địa điểm",
                "[Part 4] Câu hỏi về chi tiết",
                "[Part 4] Câu hỏi về hành động tương lai",
                "[Part 4] Câu hỏi kết hợp bảng biểu",
                "[Part 4] Câu hỏi về hàm ý câu nói",
                "[Part 4] Dạng bài: Telephone message - Tin nhắn thoại",
                "[Part 4] Dạng bài: Announcement - Thông báo",
                "[Part 4] Dạng bài: Advertisement - Quảng cáo",
                "[Part 4] Dạng bài: Talk - Bài phát biểu, diễn văn",
                "[Part 4] Dạng bài: News report, Broadcast - Bản tin",
                "[Part 4] Câu hỏi yêu cầu, gợi ý",
                // PART 5
                "[Part 5] Câu hỏi từ loại",
                "[Part 5] Câu hỏi ngữ pháp",
                "[Grammar] Danh từ",
                "[Grammar] Đại từ",
                "[Grammar] Tính từ",
                "[Grammar] Thì",
                "[Grammar] Thể",
                "[Grammar] Trạng từ",
                "[Grammar] Động từ nguyên mẫu có to",
                "[Grammar] Danh động từ",
                "[Grammar] Giới từ",
                "[Grammar] Liên từ",
                "[Grammar] Câu điều kiện",
                // PART 6
                "[Part 6] Câu hỏi từ loại",
                "[Part 6] Câu hỏi ngữ pháp",
                "[Part 6] Câu hỏi từ vựng",
                "[Part 6] Câu hỏi điền câu vào đoạn văn",
                "[Part 6] Hình thức: Thư điện tử/ thư tay (Email/ Letter)",
                "[Part 6] Hình thức: Thông báo/ văn bản hướng dẫn (Notice/ Announcement Information)",
                "[Grammar] Danh từ",
                "[Grammar] Đại từ",
                "[Grammar] Tính từ",
                "[Grammar] Thì",
                "[Grammar] Trạng từ",
                "[Grammar] Động từ nguyên mẫu có to",
                "[Grammar] Liên từ",
                // PART 7
                "[Part 7] Câu hỏi tìm thông tin",
                "[Part 7] Câu hỏi tìm chi tiết sai",
                "[Part 7] Câu hỏi điền câu",
                "[Part 7] Cấu trúc: một đoạn",
                "[Part 7] Dạng bài: Email/ Letter: Thư điện tử/ Thư tay",
                "[Part 7] Dạng bài: Advertisement - Quảng cáo",
                "[Part 7] Dạng bài: Article/ Review: Báo/ Bài đánh giá",
                "[Part 7] Dạng bài: Announcement/ Notice: Thông báo",
                "[Part 7] Dạng bài: Text message chain - Chuỗi tin nhắn",
                "[Part 7] Câu hỏi tìm từ đồng nghĩa",
                "[Part 7] Câu hỏi về hàm ý câu nói",
                "[Part 7] Câu hỏi suy luận",
                "[Part 7] Câu hỏi về chủ đề, mục đích",
                "[Part 7] Cấu trúc: nhiều đoạn"
        };

        for (String tagName : tags) {
            if (!tagRepository.existsByName(tagName)) {
                Tag tag = new Tag();
                tag.setName(tagName);
                tagRepository.save(tag);
            }
        }
        log.info("Initialized tags successfully.");
    }
}