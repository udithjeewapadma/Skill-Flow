package com.example.skill_flow_paf.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "replies")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String replyText;

    @ManyToOne
    @JoinColumn(name = "help_desk_id")
    private HelpDesk helpDesk;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

