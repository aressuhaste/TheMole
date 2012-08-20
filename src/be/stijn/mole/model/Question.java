package be.stijn.mole.model;



import java.util.List;

/**
 *
 * @author Stijn Bouchier
 * @since 02/06/2012 
 * @version 20120602.1 
 */
public class Question {
    private int id;
    private String question;
    private QuestionType type;
    private List<Answer> answerPossibilities;

    public Question(int id, String question, QuestionType type, List<Answer> answerPossibilities) {
        this.id = id;
        this.question = question;
        this.type = type;
        this.answerPossibilities = answerPossibilities;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public List<Answer> getAnswerPossibilities() {
        return answerPossibilities;
    }

    public void setAnswerPossibilities(List<Answer> answerPossibilities) {
        this.answerPossibilities = answerPossibilities;
    }
}
