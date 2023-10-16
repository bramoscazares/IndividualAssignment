package Version2;

public class Puzzle {
    private String name;
    private String desciption;
    private String answer;
    public int roomLocation;

    public int numAttempts = 3;
    public boolean solved = false;

    public Puzzle(String name, String desciption, String answer, int roomLocation) {
        this.name = name;
        this.desciption = desciption;
        this.answer = answer;
        this.roomLocation = roomLocation;
    }


    //SETTERS AND GETTERS ==============================================
    public String getName() {
        return name;
    }

    public String getDesciption() {
        return desciption;
    }

    public String getAnswer() {
        return answer;
    }

    public int getNumAttempts() {
        return numAttempts;
    }

    public void setSolved(){
        this.solved = true;
    }
}
