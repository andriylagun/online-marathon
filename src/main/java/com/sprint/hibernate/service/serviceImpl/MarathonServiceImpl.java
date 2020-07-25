package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.repository.MarathonRepository;
import com.sprint.hibernate.service.MarathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MarathonServiceImpl implements MarathonService {

    private MarathonRepository marathonRepository;
    @Autowired
    public void setMarathonRepository(MarathonRepository marathonRepository){
        this.marathonRepository=marathonRepository;
    }

    @Override
    public List<Marathon> getAll() {
        return marathonRepository.findAll();
    }

    @Override
    public Marathon getMarathonById(long id) {
        return marathonRepository.getOne(id);
    }

    @Override
    public Marathon createOrUpdate(Marathon input) {
        Marathon marathon=marathonRepository.findById(input.getId()).get();
        if(marathon==null)
            return marathonRepository.save(input);

        marathon.setSprintList(input.getSprintList());
        marathon.setTitle(input.getTitle());
        marathon.setUsers(input.getUsers());
        return marathon;
    }

    @Override
    public void deleteMarathonById(long id) {
    marathonRepository.deleteById(id);
    }
}
