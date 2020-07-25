package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.repository.MarathonRepository;
import com.sprint.hibernate.service.MarathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

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
    public Marathon getMarathonById(BigInteger id) {
        return marathonRepository.getOne(id);
    }

    @Override
    public Marathon createOrUpdate(Marathon input) {
        Marathon marathon=marathonRepository.getOne(input.getId());
        if(marathon.getId()==null)
            return marathonRepository.save(input);
        return marathon;
    }

    @Override
    public void deleteMarathonById(BigInteger id) {

    }
}
