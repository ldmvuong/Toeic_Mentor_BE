package vn.edu.hcmute.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.edu.hcmute.model.Part;
import vn.edu.hcmute.repository.PartRepository;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "INIT-PART-RUNNER")
public class InitPartRunner implements CommandLineRunner {

    private final PartRepository partRepository;

    @Override
    public void run(String... args) {
        String[] partNames = {
                "Photographs", "Question-Response", "Short Conversations",
                "Short Talks", "Incomplete Sentences", "Text Completion", "Reading Comprehension"
        };
        short[] questionCounts = {6, 25, 39, 30, 30, 16, 54};

        for (byte i = 1; i <= 7; i++) {
            if (!partRepository.existsById(i)) {
                Part part = new Part();
                part.setId(i);
                part.setName(partNames[i - 1]);
                part.setQuestionCnt(questionCounts[i - 1]);
                partRepository.save(part);
            }
        }
        log.info("Initialized parts successfully.");
    }
}