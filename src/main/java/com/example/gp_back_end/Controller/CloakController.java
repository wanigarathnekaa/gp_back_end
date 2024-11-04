package com.example.gp_back_end.Controller;

import com.example.gp_back_end.model.CloakModel;
import com.example.gp_back_end.services.CloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cloaks")
@CrossOrigin(origins = "*")
public class CloakController {

    @Autowired
    private CloakService cloakService;

    @PostMapping("/add")
    public CloakModel addCloak(@RequestBody CloakModel cloakModel){
        return cloakService.addCloak(cloakModel.getSmallCount(), cloakModel.getMediumCount(), cloakModel.getLargeCount());
    }
}
