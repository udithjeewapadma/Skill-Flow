package com.example.skill_flow_paf.Service.Impl;

import com.example.skill_flow_paf.Controller.DTO.request.CreateReplyRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.ReplyResponseDTO;
import com.example.skill_flow_paf.Exception.HelpDeskNotFoundException;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private HelpDeskRepository helpDeskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Reply createReply(Long userId, Long helpDeskId, CreateReplyRequestDTO createReplyRequestDTO)
            throws HelpDeskNotFoundException,UserNotFoundExecption{
        HelpDesk helpDesk = helpDeskRepository.findById(helpDeskId)
                .orElseThrow(()-> new HelpDeskNotFoundException("Reply not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundExecption("User not found"));

        Reply reply = new Reply();
        reply.setReplyText(createReplyRequestDTO.getReplyText());
        reply.setHelpDesk(helpDesk);
        reply.setUser(user);

        return replyRepository.save(reply);
    }

    @Override
    public ReplyResponseDTO findReplyById(Long id)
            throws ReplyNotFoundException{
        Reply reply = replyRepository.findById(id).orElseThrow(()-> new ReplyNotFoundException("Reply not found"));

        ReplyResponseDTO replyResponseDTO = new ReplyResponseDTO();
        replyResponseDTO.setId(reply.getId());
        replyResponseDTO.setReplyText(reply.getReplyText());
        replyResponseDTO.setUserId(reply.getUser().getId());
        replyResponseDTO.setHelpDeskId(reply.getHelpDesk().getId());

        return replyResponseDTO;

    }

    @Override
    public List<ReplyResponseDTO> findAllReplies() {
        List<Reply> replies = replyRepository.findAll();

        return replies.stream().map(reply -> {
            ReplyResponseDTO replyResponseDTO = new ReplyResponseDTO();

            replyResponseDTO.setId(reply.getId());
            replyResponseDTO.setReplyText(reply.getReplyText());
            replyResponseDTO.setHelpDeskId(reply.getHelpDesk().getId());
            replyResponseDTO.setUserId(reply.getUser().getId());

            return replyResponseDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteReplyById(Long id) {
        replyRepository.deleteById(id);
    }

    @Override
    public Reply updateReplyById(Long id, CreateReplyRequestDTO createReplyRequestDTO) throws ReplyNotFoundException{

        Reply existingReply = replyRepository
                .findById(id).orElseThrow(()-> new ReplyNotFoundException("Reply not found"));

        existingReply.setReplyText(createReplyRequestDTO.getReplyText());
        return replyRepository.save(existingReply);
    }
}
