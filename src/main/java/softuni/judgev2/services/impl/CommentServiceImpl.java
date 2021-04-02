package softuni.judgev2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.judgev2.model.entities.Comment;
import softuni.judgev2.model.service.CommentServiceModel;
import softuni.judgev2.repositories.CommentRepository;
import softuni.judgev2.security.CurrentUser;
import softuni.judgev2.services.CommentService;
import softuni.judgev2.services.HomeworkService;
import softuni.judgev2.services.UserService;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final ModelMapper mapper;
    private final UserService userService;
    private final CurrentUser user;
    private final HomeworkService homeworkService;

    public CommentServiceImpl(CommentRepository repository, ModelMapper mapper, UserService userService, CurrentUser user, HomeworkService homeworkService) {
        this.repository = repository;
        this.mapper = mapper;
        this.userService = userService;
        this.user = user;
        this.homeworkService = homeworkService;
    }


    @Override
    public void add(CommentServiceModel serviceModel, Long homeworkId) {
        Comment comment = mapper.map(serviceModel,Comment.class);
        comment.setAuthor(userService.findById(user.getId()));
        comment.setHomework(homeworkService.findById(homeworkId));
        repository.save(comment);
    }

    @Override
    public double avgScore() {
        return repository.findAverageScore();
    }

    @Override
    public Map<Integer, Integer> findScoreMap() {
        Map<Integer, Integer>scoreMap = defineScore();
        repository.findAll().forEach(
                c->{
                    Integer score = c.getScore();
                    scoreMap.put(score,scoreMap.get(score)+1);
                }
        );
        return scoreMap;
    }

    private Map<Integer, Integer>defineScore (){
        Map<Integer,Integer>newMap = new HashMap<>();
        for (int i =2;i<=6;i++){
            newMap.put(i,0);
        }
        return newMap;
    }


}
