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

    public CloakModel getCloakCount() {
        return cloakRepository.findByName("cloakCounts").orElse(null); // Fetch the cloakCounts entry by name
    }

    public CloakModel addOrUpdateCloak(String name, int smallCount, int mediumCount, int largeCount){
        Optional<CloakModel> existingCloak = cloakRepository.findByName(name);
        System.out.println("Name: " + name );

        if (existingCloak.isPresent()){
            CloakModel cloak = existingCloak.get();
            cloak.setSmallCount(cloak.getSmallCount() + smallCount);
            cloak.setMediumCount(cloak.getMediumCount() + mediumCount);
            cloak.setLargeCount(cloak.getLargeCount() + largeCount);
            return cloakRepository.save(cloak);
        } else {
            CloakModel newCloak = new CloakModel(name, smallCount, mediumCount, largeCount);
//        CloakModel savedCloak = cloakRepository.save(cloak);
//            newCloak.setName(name);
            return cloakRepository.save(newCloak);
        }
    }

    public CloakModel removeCloak(int smallCount, int mediumCount, int largeCount){
        Optional<CloakModel> existingCloak = cloakRepository.findByName("cloakCounts");

        if(existingCloak.isPresent()){
            CloakModel cloak = existingCloak.get();

            cloak.setSmallCount(Math.max(cloak.getSmallCount() - smallCount, 0));
            cloak.setMediumCount(Math.max(cloak.getMediumCount() - mediumCount, 0));
            cloak.setLargeCount(Math.max(cloak.getLargeCount() - largeCount, 0));

            return cloakRepository.save(cloak);
        } else {
            throw new IllegalStateException("No cloak counts found to remove");
        }
    }
}
