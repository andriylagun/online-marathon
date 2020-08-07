package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.entity.Sprint;
import com.sprint.hibernate.entity.Task;
import com.sprint.hibernate.exceptions.SprintExistException;
import com.sprint.hibernate.repository.SprintRepository;
import com.sprint.hibernate.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public boolean addSprintToMarathon(Sprint sprint, Marathon marathon) throws SprintExistException {
        if (checkTitle(sprint.getTitle())) {
            throw new SprintExistException("Sprint with this title already exists in this marathon");
        }
        sprint.setMarathon(marathon);
        sprintRepository.save(sprint);
        return true;
    }

    @Override
    public boolean checkTitle(String title) {
        return sprintRepository.findSprintByTitle(title) != null;
    }


    @Override
    public Sprint updateSprint(Sprint sprint) {
        if (sprint != null) {
            Optional<Sprint> temp = sprintRepository.findById(sprint.getId());
            if (temp.isPresent()) {
                Sprint newSprint = temp.get();
                newSprint.setTitle(sprint.getTitle());
                newSprint.setTasks(sprint.getTasks());
                newSprint.setFinish(sprint.getFinish());
                newSprint.setStartDate(sprint.getStartDate());
                newSprint.setMarathon(sprint.getMarathon());
                newSprint = sprintRepository.save(newSprint);
                return newSprint;
            }
        }
        return null;
    }

    @Override
    public Sprint getSprintById(long id) {

        return sprintRepository.findById(id).get();
    }

    @Override
    public void deleteSprintById(long id, Marathon marathon) {
        Sprint sprint = getSprintById(id);
        List<Sprint> sprints = marathon.getSprintList();
        sprints.remove(sprint);
        marathon.setSprintList(sprints);
        List<Task> tasks = sprint.getTasks();
        sprintRepository.deleteById(id);
    }

    public void deleteAll() {
        sprintRepository.deleteAll();
    }
}
