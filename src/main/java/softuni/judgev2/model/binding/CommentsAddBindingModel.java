package softuni.judgev2.model.binding;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentsAddBindingModel {
    private Integer score;
    private String description;
    private Long homeworkId;


    public CommentsAddBindingModel() {
    }

    @Min(value = 2,message = "Score must be more than 2")
    @Max(value = 6,message = "Score must be less than 6")
    public Integer getScore() {
        return score;
    }

    public CommentsAddBindingModel setScore(Integer score) {
        this.score = score;
        return this;
    }

    @Size(min = 3,message = "Text content length must be more than 3 characters")
    @NotBlank(message = "Cannot be empty")
    public String getDescription() {
        return description;
    }

    public CommentsAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public CommentsAddBindingModel setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
        return this;
    }
}

