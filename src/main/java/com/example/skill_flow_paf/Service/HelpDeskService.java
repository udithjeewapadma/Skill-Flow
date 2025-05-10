package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateHelpDeskRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.HelpDeskResponseDTO;
import com.example.skill_flow_paf.Models.HelpDesk;

import java.util.List;

public interface HelpDeskService {
    HelpDesk createHelpDesk(Long userId,CreateHelpDeskRequestDTO createHelpDeskRequestDTO);

    HelpDeskResponseDTO findHelpDeskById(Long id);

    List<HelpDeskResponseDTO> findAllHelpDesk();

    void deleteHelpDeskById(Long id);

    HelpDesk updateHelpDeskById(Long id, CreateHelpDeskRequestDTO createHelpDeskRequestDTO);
}
