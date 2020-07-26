package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.repository.MarathonRepository;
import com.sprint.hibernate.service.MarathonService;
import com.sprint.hibernate.validator.EntityValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MarathonServiceImpl implements MarathonService {

    @Autowired
    private EntityValidate validator;
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

        Optional<Marathon> marathon = marathonRepository.findById(id);

        if (marathon.isPresent()) {
            return marathon.get();
        } else {
            throw new EntityNotFoundException("No user exist for given id");
        }
    }

    @Override
    public Marathon createOrUpdate(Marathon input) {
        validator.validate(input);
        Optional<Marathon> marathon=marathonRepository.findById(input.getId());
        if(!marathon.isPresent())
            return marathonRepository.save(input);
        Marathon newMarathon=marathon.get();
        newMarathon.setSprintList(input.getSprintList());
        newMarathon.setTitle(input.getTitle());
        newMarathon.setUsers(input.getUsers());
        return newMarathon;
    }
    @Override
    public void deleteMarathonById(long id) {
    marathonRepository.deleteById(id);
    }

    public void deleteAll(){
        marathonRepository.deleteAll();
    }
}
