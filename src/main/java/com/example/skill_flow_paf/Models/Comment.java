package com.example.skill_flow_paf.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private int likedCount = 0;

    @OneToMany(mappedBy = "comment")
    private List<CommentReply> commentReply;
    //come to many comments
    //one comment has many reply

}
