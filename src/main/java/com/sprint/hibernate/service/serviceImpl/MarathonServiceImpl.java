package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.exceptions.MarathonExistException;
import com.sprint.hibernate.repository.MarathonRepository;
import com.sprint.hibernate.service.MarathonService;
import com.sprint.hibernate.service.SprintService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MarathonServiceImpl implements MarathonService {

    private MarathonRepository marathonRepository;
    private SprintService sprintService;

    @Autowired
    public MarathonServiceImpl(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
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
            throw new EntityNotFoundException("No marathon exist for given id");
        }
    }

    @Override
    public Marathon createOrUpdate(Marathon input) throws MarathonExistException {
        Optional<Marathon> marathon = marathonRepository.findById(input.getId());
        if (!marathon.isPresent()){
            if(checkTitle(input.getTitle()))
                throw new MarathonExistException("This marathon is already exist");
        return marathonRepository.save(input);
        }
        Marathon newMarathon=marathon.get();
        newMarathon.setTitle(input.getTitle());
        return newMarathon;
    }


    @Override
    public void deleteMarathonById(long id) {
    marathonRepository.deleteById(id);
    }

    public void deleteAll(){
        marathonRepository.deleteAll();
    }

    @Override
    public boolean checkTitle(String title) {
        return marathonRepository.getMarathonByTitle(title)!=null;
    }
}
