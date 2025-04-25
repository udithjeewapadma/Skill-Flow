package com.example.skill_flow_paf.Repository;

import com.example.skill_flow_paf.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
