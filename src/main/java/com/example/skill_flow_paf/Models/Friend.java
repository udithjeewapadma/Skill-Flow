package com.example.skill_flow_paf.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "friends")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1; // First friend

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2; // Second friend
}

