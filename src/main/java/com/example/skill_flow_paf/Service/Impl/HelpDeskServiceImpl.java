package com.example.skill_flow_paf.Service.Impl;

import com.example.skill_flow_paf.Controller.DTO.request.CreateHelpDeskRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.HelpDeskResponseDTO;
import com.example.skill_flow_paf.Exception.HelpDeskNotFoundException;
import com.example.skill_flow_paf.Exception.UserNotFoundExecption;
import com.example.skill_flow_paf.Models.HelpDesk;
import com.example.skill_flow_paf.Models.User;
import com.example.skill_flow_paf.Repository.HelpDeskRepository;
import com.example.skill_flow_paf.Repository.UserRepository;
import com.example.skill_flow_paf.Service.HelpDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelpDeskServiceImpl implements HelpDeskService {

    @Autowired
    private HelpDeskRepository helpDeskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public HelpDesk createHelpDesk(Long userId,CreateHelpDeskRequestDTO createHelpDeskRequestDTO) {

        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundExecption("User ID not found"));

        HelpDesk helpDesk = new HelpDesk();
        helpDesk.setQuestion(createHelpDeskRequestDTO.getQuestion());
        helpDesk.setUser(user);
        return helpDeskRepository.save(helpDesk);
    }

    @Override
    public HelpDeskResponseDTO findHelpDeskById(Long id) {
        HelpDesk helpDesk = helpDeskRepository.findById(id).orElseThrow(()-> new HelpDeskNotFoundException("HelpDesk not found"));
        //User user =userRepository.findById(id).orElseThrow(()-> new UserNotFoundExecption("User not found"));

        HelpDeskResponseDTO helpDeskResponseDTO = new HelpDeskResponseDTO();
        helpDeskResponseDTO.setUserId(helpDesk.getUser().getId());
        helpDeskResponseDTO.setUserName(helpDesk.getUser().getUsername()); // ✅ Add this
        helpDeskResponseDTO.setId(helpDesk.getId());
        helpDeskResponseDTO.setId(helpDesk.getId());
        helpDeskResponseDTO.setQuestion(helpDesk.getQuestion());


        return helpDeskResponseDTO;
    }

    @Override
    public List<HelpDeskResponseDTO> findHelpDesksByUserId(Long userId) {
        // Fetch help desks by user ID
        List<HelpDesk> helpDesks = helpDeskRepository.findByUserId(userId);

        // Map the HelpDesk entities to HelpDeskResponseDTOs
        return helpDesks.stream()
                .map(helpDesk -> {
                    HelpDeskResponseDTO responseDTO = new HelpDeskResponseDTO();
                    responseDTO.setId(helpDesk.getId());
                    responseDTO.setQuestion(helpDesk.getQuestion());
                    responseDTO.setUserId(helpDesk.getUser().getId());
                    responseDTO.setUserName(helpDesk.getUser().getUsername());
                    return responseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<HelpDeskResponseDTO> findAllHelpDesk() {
        List<HelpDesk> helpDesks = helpDeskRepository.findAll();

        return helpDesks.stream().map(helpDesk -> {
            HelpDeskResponseDTO helpDeskResponseDTO = new HelpDeskResponseDTO();

            helpDeskResponseDTO.setId(helpDesk.getId());
            helpDeskResponseDTO.setQuestion(helpDesk.getQuestion());
            helpDeskResponseDTO.setUserId(helpDesk.getUser().getId());
            helpDeskResponseDTO.setUserName(helpDesk.getUser().getUsername()); // ✅ Add user name

            return helpDeskResponseDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteHelpDeskById(Long id) {
        helpDeskRepository.deleteById(id);
    }

    @Override
    public HelpDesk updateHelpDeskById(Long id, CreateHelpDeskRequestDTO createHelpDeskRequestDTO) {

        HelpDesk existingHelpDesk = helpDeskRepository.findById(id).orElseThrow(()-> new HelpDeskNotFoundException("Help Desk not found"));

        existingHelpDesk.setQuestion(createHelpDeskRequestDTO.getQuestion());
        return helpDeskRepository.save(existingHelpDesk);
    }

}
