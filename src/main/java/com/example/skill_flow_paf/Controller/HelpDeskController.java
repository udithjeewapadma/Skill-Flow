package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Controller.DTO.request.CreateHelpDeskRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.HelpDeskResponseDTO;
import com.example.skill_flow_paf.Models.HelpDesk;
import com.example.skill_flow_paf.Service.HelpDeskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/helps")
@CrossOrigin(origins = "http://localhost:5173")
public class HelpDeskController {

    @Autowired
    private HelpDeskService helpDeskService;

    @PostMapping
    public HelpDeskResponseDTO createHelpDesk(@Valid @RequestParam Long userId, @RequestBody CreateHelpDeskRequestDTO createHelpDeskRequestDTO){
        HelpDesk helpDesk = helpDeskService.createHelpDesk(userId,createHelpDeskRequestDTO);

        HelpDeskResponseDTO helpDeskResponseDTO = new HelpDeskResponseDTO();
        helpDeskResponseDTO.setId(helpDesk.getId());
        helpDeskResponseDTO.setQuestion(helpDesk.getQuestion());
        helpDeskResponseDTO.setUserId(helpDesk.getUser().getId());

        return helpDeskResponseDTO;
    }

    @GetMapping("/{help-desk-id}")
    public HelpDeskResponseDTO getHelpDeskById(@PathVariable("help-desk-id") Long helpDeskId){
        return helpDeskService.findHelpDeskById(helpDeskId);
    }

    @GetMapping
    public List<HelpDeskResponseDTO> getAllHelpDesk(){
        return helpDeskService.findAllHelpDesk();
    }

    @DeleteMapping("/{help-desk-id}")
    public void deleteHelpDeskById(@PathVariable("help-desk-id") Long  helpDeskId){
        helpDeskService.deleteHelpDeskById(helpDeskId);
    }

    @PutMapping("/{help-desk-id}")
    public HelpDeskResponseDTO updateHelpDeskById(@Valid @PathVariable("help-desk-id") Long helpDeskId, @RequestBody CreateHelpDeskRequestDTO createHelpDeskRequestDTO){
        HelpDesk helpDesk = helpDeskService.updateHelpDeskById(helpDeskId,createHelpDeskRequestDTO);
        HelpDeskResponseDTO helpDeskResponseDTO= new HelpDeskResponseDTO();
        helpDeskResponseDTO.setQuestion(helpDesk.getQuestion());

        return helpDeskResponseDTO;
    }



}
