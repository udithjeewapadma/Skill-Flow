package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateHelpDeskRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.HelpDeskResponseDTO;
import com.example.skill_flow_paf.Models.HelpDesk;

public interface HelpDeskService {
    HelpDesk createHelpDesk(Long userId,CreateHelpDeskRequestDTO createHelpDeskRequestDTO);

    HelpDeskResponseDTO findHelpDeskById(Long id);
}
