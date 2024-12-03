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

    @GetMapping("/current")
    public CloakModel getCloakCount() {
        return cloakService.getCloakCount();
    }

    @PostMapping("/save")
    public CloakModel addOrUpdateCloak(@RequestBody CloakModel cloakModel){

        String name = cloakModel.getName();
        int smallCount = cloakModel.getSmallCount();
        int mediumCount = cloakModel.getMediumCount();
        int largeCount = cloakModel.getLargeCount();
        return cloakService.addOrUpdateCloak(name, smallCount, mediumCount, largeCount);
    }

    @PostMapping("/remove")
    public CloakModel removeCloak(@RequestBody CloakModel cloakModel){
        int smallCount = cloakModel.getSmallCount();
        int mediumCount = cloakModel.getMediumCount();
        int largeCount = cloakModel.getLargeCount();
        return cloakService.removeCloak(smallCount, mediumCount, largeCount);
    }


}
