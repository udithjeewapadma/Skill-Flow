package com.example.skill_flow_paf.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "learning_plans")
public class LearningPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String description;
    private String resources;
    private String timeLine;


    @ManyToMany
    @JoinTable(
            name = "post_learning_plans",
            joinColumns = @JoinColumn(name = "learning_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> posts;
}
