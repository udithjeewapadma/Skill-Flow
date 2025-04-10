package com.example.skill_flow_paf.Service.Impl;

import com.example.skill_flow_paf.Controller.DTO.request.CreateHelpDeskRequestDTO;
import com.example.skill_flow_paf.Exception.HelpDeskNotFoundException;
import com.example.skill_flow_paf.Exception.UserNotFoundExecption;
import com.example.skill_flow_paf.Models.HelpDesk;
import com.example.skill_flow_paf.Models.User;
import com.example.skill_flow_paf.Repository.HelpDeskRepository;
import com.example.skill_flow_paf.Repository.UserRepository;
import com.example.skill_flow_paf.Service.HelpDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
