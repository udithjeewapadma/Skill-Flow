package com.example.skill_flow_paf.Repository;

import com.example.skill_flow_paf.Models.HelpDesk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelpDeskRepository extends JpaRepository<HelpDesk, Long> {
    List<HelpDesk> findByUserId(Long userId);
}
