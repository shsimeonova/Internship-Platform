package org.isp.services.tasks_services.task_applications_services;

import org.isp.domain.applications.TaskApplication;
import org.isp.domain.tasks.dtos.TaskDto;
import org.isp.domain.tasks.entities.Task;
import org.isp.services.tasks_services.TaskService;
import org.isp.domain.users.entities.User;
import org.isp.repositories.tasks_repositories.TaskApplicationRepository;
import org.isp.services.user_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class TaskApplicationServiceImpl implements TaskApplicationService {
    private TaskApplicationRepository taskApplicationRepository;
    private TaskService taskService;
    private UserService userService;

    @Autowired
    public TaskApplicationServiceImpl(TaskApplicationRepository taskApplicationRepository,
                                      UserService userService,
                                      TaskService taskService) {
        this.taskApplicationRepository = taskApplicationRepository;
        this.userService = userService;
        this.taskService = taskService;
    }

    @Override
    public List<TaskApplication> fetchAll() {
        return this.taskApplicationRepository.findAll();
    }

    @Override
    public List<TaskApplication> fetchAllNonAssigned(){
        return this.taskApplicationRepository.findAllByNonAssignedTask();
    }

    @Override
    public void createTaskApplication(Task task, User user) {
        TaskApplication taskApplication = new TaskApplication(user, task);
        this.taskApplicationRepository.saveAndFlush(taskApplication);
    }

    @Override
    public void apply(String username, TaskDto taskDto) throws IOException {
        TaskApplication taskApplication = this.taskApplicationRepository.findByUserUsernameAndTaskId(username, taskDto.getId());
        if (taskApplication != null) {
            throw new IllegalArgumentException("User already applied to this task!");
        }
        Task task = this.taskService.findTaskById(taskDto.getId());
        User user = this.userService.findByUsername(username);
        this.createTaskApplication(task, user);
    }

    @Override
    public void approveTaskApplication(String taskApplicationId) throws Exception {
        TaskApplication taskApplication = this.taskApplicationRepository.getOne(taskApplicationId);
        taskApplication.setIsApproved(true);
        this.taskApplicationRepository.saveAndFlush(taskApplication);
    }

    @Override
    public void declineTaskApplication(String taskApplicationId) {
        TaskApplication taskApplication = this.taskApplicationRepository.getOne(taskApplicationId);
        taskApplication.setIsDeclined(true);
        this.taskApplicationRepository.saveAndFlush(taskApplication);
//        this.taskApplicationRepository.declineById(taskApplicationId);
    }
}
