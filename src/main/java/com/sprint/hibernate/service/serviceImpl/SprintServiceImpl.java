package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.repository.SprintRepository;
import com.sprint.hibernate.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Service
@Transactional
public class SprintServiceImpl implements SprintService {

   private SprintRepository sprintRepository;
    @Autowired
    public void setSprintRepository(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Override
    public List<Sprint> getSprintsByMarathonId(long id) {
        return sprintRepository.getSprintsByMarathonId(id);
    }

    @Override
    public boolean addSprintToMarathon(Sprint sprint, Marathon marathon) {

        return marathon.getSprintList().add(sprint);
    }


    @Override
    public Sprint createOrUpdateSprint(Sprint sprint) {
        Sprint updated=sprintRepository.getOne(sprint.getId());
            if (updated == null)
                throw new EntityNotFoundException("No sprint exist for given id");
        updated.setMarathon(sprint.getMarathon());
        updated.setStartDate(sprint.getStartDate());
        updated.setFinish(sprint.getFinish());
        updated.setTasks(sprint.getTasks());
        updated.setTitle(sprint.getTitle());
        return updated;
    }

    @Override
    public Sprint getSprintById(long id) {
        return sprintRepository.getOne(id);
    }

    @Override
    public void deleteSprintById(long id) {
        sprintRepository.deleteById(id);
    }
}
