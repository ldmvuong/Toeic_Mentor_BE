package vn.edu.hcmute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmute.model.Part;

public interface PartRepository extends JpaRepository<Part, Byte> {
} 