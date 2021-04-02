package softuni.judgev2.services;

import softuni.judgev2.model.service.CommentServiceModel;

import java.util.Map;
import java.util.TreeMap;

public interface CommentService {
    void add(CommentServiceModel serviceModel, Long homeworkId);

    double avgScore();

    Map<Integer,Integer> findScoreMap();
}
