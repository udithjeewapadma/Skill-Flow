package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateReplyRequestDTO;
import com.example.skill_flow_paf.Models.Reply;

public interface ReplyService {
    Reply createReply(Long userId,Long helpDeskId, CreateReplyRequestDTO createReplyRequestDTO);
}
