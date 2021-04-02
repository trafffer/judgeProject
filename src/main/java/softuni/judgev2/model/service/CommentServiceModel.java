package softuni.judgev2.model.service;


public class CommentServiceModel {
    private int score;
    private String textContent;
    private UserServiceModel serviceModel;


    public CommentServiceModel() {
    }

    public int getScore() {
        return score;
    }

    public CommentServiceModel setScore(int score) {
        this.score = score;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentServiceModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public UserServiceModel getServiceModel() {
        return serviceModel;
    }

    public CommentServiceModel setServiceModel(UserServiceModel serviceModel) {
        this.serviceModel = serviceModel;
        return this;
    }
}
