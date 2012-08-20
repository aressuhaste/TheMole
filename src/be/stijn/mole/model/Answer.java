package be.stijn.mole.model;



import java.util.List;

/**
 *
 * @author Stijn Bouchier
 * @since 02/06/2012 
 * @version 20120602.1 
 */
public class Answer {
    private int id;
    private String answer;
    private String properties;
    private List<Person> correctForPersons;

    public Answer(int id, String answer, String properties, List<Person> correctForPersons) {
        this.id = id;
        this.answer = answer;
        this.properties = properties;
        this.correctForPersons = correctForPersons;
    }

    public int getId() {
        return id;
    }
    
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public List<Person> getCorrectForPersons() {
        return correctForPersons;
    }

    public void setCorrectForPersons(List<Person> correctForPersons) {
        this.correctForPersons = correctForPersons;
    }
}
