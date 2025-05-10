package com.example.skill_flow_paf.Repository;

import com.example.skill_flow_paf.Models.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByHelpDeskId(Long helpDeskId);
}
