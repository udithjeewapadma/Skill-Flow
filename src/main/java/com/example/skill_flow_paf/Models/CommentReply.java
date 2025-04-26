package com.example.skill_flow_paf.Models;

import jakarta.persistence.*;

@Entity
public class CommentReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //set id comment reply class

    private String ReplyBody;

    @ManyToOne
    @JoinColumn(name ="comment_id")
    private Comment comment;
    //mapped with comment
    //many comment reply has one comment



}
