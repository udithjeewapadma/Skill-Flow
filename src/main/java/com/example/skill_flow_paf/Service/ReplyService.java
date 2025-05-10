package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateReplyRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.ReplyResponseDTO;
import com.example.skill_flow_paf.Models.Reply;

import java.util.List;

public interface ReplyService {
    Reply createReply(Long userId,Long helpDeskId, CreateReplyRequestDTO createReplyRequestDTO);

    ReplyResponseDTO findReplyById(Long id);

    List<ReplyResponseDTO> getRepliesByHelpDesk(Long helpDeskId);

    List<ReplyResponseDTO> findAllReplies();

    void deleteReplyById(Long id);

    Reply updateReplyById(Long id, CreateReplyRequestDTO createReplyRequestDTO);


}
