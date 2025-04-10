package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Controller.DTO.request.CreateHelpDeskRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.HelpDeskResponseDTO;
import com.example.skill_flow_paf.Models.HelpDesk;
import com.example.skill_flow_paf.Service.HelpDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/helps")
public class HelpDeskController {

    @Autowired
    private HelpDeskService helpDeskService;

    @PostMapping
    public HelpDeskResponseDTO createHelpDesk(@RequestParam Long userId, @RequestBody CreateHelpDeskRequestDTO createHelpDeskRequestDTO){
        HelpDesk helpDesk = helpDeskService.createHelpDesk(userId,createHelpDeskRequestDTO);

        HelpDeskResponseDTO helpDeskResponseDTO = new HelpDeskResponseDTO();
        helpDeskResponseDTO.setId(helpDesk.getId());
        helpDeskResponseDTO.setQuestion(helpDesk.getQuestion());
        helpDeskResponseDTO.setUserId(helpDesk.getUser().getId());

        return helpDeskResponseDTO;
    }



}
