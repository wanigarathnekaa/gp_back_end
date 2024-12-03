package com.example.gp_back_end.services;

import com.example.gp_back_end.model.PrivilegedUsersModel;
import com.example.gp_back_end.repository.PrivilegedUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PrivilegedUsersService {

    private final PrivilegedUsersRepository privilegedUsersRepository;
    private final MongoTemplate mongoTemplate;

    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    @Autowired
    public PrivilegedUsersService(PrivilegedUsersRepository privilegedUsersRepository, MongoTemplate mongoTemplate) {
        this.privilegedUsersRepository = privilegedUsersRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public PrivilegedUsersModel addPrivilegedUser(String roleName, String name, String email, String nic) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        PrivilegedUsersModel newPrivilegedUser = new PrivilegedUsersModel();

        newPrivilegedUser.setRoleName(roleName);
        newPrivilegedUser.setName(name);
        newPrivilegedUser.setEmail(email);
        newPrivilegedUser.setNic(nic);
        newPrivilegedUser.setPassword(nic);
        newPrivilegedUser.setRegNumber(generateUniqueRegNumber());

        return privilegedUsersRepository.save(newPrivilegedUser);
    }

    private String generateUniqueRegNumber() {
        String currentYear = String.valueOf(LocalDate.now().getYear());
        int sequenceNumber = COUNTER.getAndIncrement();
        String regNumber = currentYear + "/PU/" + String.format("%03d", sequenceNumber);

        // Ensure uniqueness in the database
        while (isRegNumberExists(regNumber)) {
            sequenceNumber = COUNTER.getAndIncrement();
            regNumber = currentYear + "/PU/" + String.format("%03d", sequenceNumber);
        }

        return regNumber;
    }

    private boolean isRegNumberExists(String regNumber) {
        Query query = new Query();
        query.addCriteria(Criteria.where("regNumber").is(regNumber));
        return mongoTemplate.exists(query, PrivilegedUsersModel.class);
    }
}
