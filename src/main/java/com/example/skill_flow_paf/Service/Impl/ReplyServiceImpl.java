package com.example.skill_flow_paf.Service.Impl;

import com.example.skill_flow_paf.Controller.DTO.request.CreateReplyRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.ReplyResponseDTO;
import com.example.skill_flow_paf.Exception.ReplyNotFoundException;
import com.example.skill_flow_paf.Exception.UserNotFoundExecption;
import com.example.skill_flow_paf.Models.HelpDesk;
import com.example.skill_flow_paf.Models.Reply;
import com.example.skill_flow_paf.Models.User;
import com.example.skill_flow_paf.Repository.HelpDeskRepository;
import com.example.skill_flow_paf.Repository.ReplyRepository;
import com.example.skill_flow_paf.Repository.UserRepository;
import com.example.skill_flow_paf.Service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private HelpDeskRepository helpDeskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Reply createReply(Long userId, Long helpDeskId, CreateReplyRequestDTO createReplyRequestDTO) {
        HelpDesk helpDesk = helpDeskRepository.findById(helpDeskId).orElseThrow(()-> new ReplyNotFoundException("Reply not found"));
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundExecption("User not found"));

        Reply reply = new Reply();
        reply.setReplyText(createReplyRequestDTO.getReplyText());
        reply.setHelpDesk(helpDesk);
        reply.setUser(user);

        return replyRepository.save(reply);
    }
}
