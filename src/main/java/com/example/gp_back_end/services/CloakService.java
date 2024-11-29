package com.example.gp_back_end.services;

import com.example.gp_back_end.model.CloakModel;
import com.example.gp_back_end.repository.CloakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CloakService {

    @Autowired
    private CloakRepository cloakRepository;

    public CloakModel addCloak(int smallCount, int mediumCount, int largeCount){
        CloakModel cloak = new CloakModel(smallCount, mediumCount, largeCount);
//        CloakModel savedCloak = cloakRepository.save(cloak);
        return cloakRepository.save(cloak);
    }

}
