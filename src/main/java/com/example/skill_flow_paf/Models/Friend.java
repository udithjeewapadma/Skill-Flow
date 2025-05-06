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
    @JoinColumn(name = "sender")
    private User sender; // First friend

    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver; // Second friend
}

