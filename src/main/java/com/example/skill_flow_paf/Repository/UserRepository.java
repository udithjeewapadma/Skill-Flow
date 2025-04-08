package com.example.skill_flow_paf.Repository;

import com.example.skill_flow_paf.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
