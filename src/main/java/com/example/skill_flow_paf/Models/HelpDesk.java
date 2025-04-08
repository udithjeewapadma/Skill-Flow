package com.example.skill_flow_paf.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Help_desks")
public class HelpDesk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "helpDesk")
    private List<Reply> reply;
}
