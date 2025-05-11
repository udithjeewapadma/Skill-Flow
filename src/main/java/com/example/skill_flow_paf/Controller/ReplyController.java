package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Controller.DTO.request.CreateReplyRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.HelpDeskResponseDTO;
import com.example.skill_flow_paf.Controller.DTO.response.ReplyResponseDTO;
import com.example.skill_flow_paf.Models.Reply;
import com.example.skill_flow_paf.Models.User;
import com.example.skill_flow_paf.Service.ReplyService;
import com.example.skill_flow_paf.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping
    public ReplyResponseDTO createReply(@RequestBody CreateReplyRequestDTO createReplyRequestDTO,
                                        @RequestParam Long userId,
                                        @RequestParam Long helpDeskId) {
        // Log the request to verify the parameters
        System.out.println("userId: " + userId + ", helpDeskId: " + helpDeskId + ", replyText: " + createReplyRequestDTO.getReplyText());

        // Call the service to create the reply
        Reply reply = replyService.createReply(userId, helpDeskId, createReplyRequestDTO);

        ReplyResponseDTO replyResponseDTO = new ReplyResponseDTO();
        replyResponseDTO.setReplyText(reply.getReplyText());
        replyResponseDTO.setId(reply.getId());
        replyResponseDTO.setHelpDeskId(reply.getHelpDesk().getId());
        replyResponseDTO.setUserId(reply.getUser().getId());
        replyResponseDTO.setUsername(reply.getUser().getUsername());

        return replyResponseDTO;
    }

    @GetMapping("/helpdesk/{helpDeskId}")
    public List<ReplyResponseDTO> getRepliesByHelpDesk(@PathVariable Long helpDeskId) {
        return replyService.getRepliesByHelpDesk(helpDeskId);
    }

    @GetMapping("/{reply-id}")
    public ReplyResponseDTO getReplyById(@PathVariable("reply-id") Long replyId){
        return replyService.findReplyById(replyId);
    }

    @GetMapping
    public List<ReplyResponseDTO> getAllReplies(){
        return replyService.findAllReplies();
    }

    @DeleteMapping("/{reply-id}")
    public void deleteReplyById(@PathVariable("reply-id") Long replyId){
        replyService.deleteReplyById(replyId);
    }

    @PutMapping("/{reply-id}")
    public ReplyResponseDTO updateReplyById(@PathVariable ("reply-id") Long replyId,@RequestBody @Valid CreateReplyRequestDTO createReplyRequestDTO){

        Reply reply = replyService.updateReplyById(replyId,createReplyRequestDTO);
        ReplyResponseDTO replyResponseDTO = new ReplyResponseDTO();
        replyResponseDTO.setReplyText(reply.getReplyText());

        return replyResponseDTO;

    }
}
