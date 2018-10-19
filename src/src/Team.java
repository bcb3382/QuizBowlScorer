import java.util.Stack;

public class Team {
    private String name;
    private int score;
    private int tossUpCount;
    private Stack<Integer> scoreTracker;

    private int setScore(int pointVal, boolean isTossUp){
        score = score + pointVal;
        scoreTracker.push(pointVal);
        if (isTossUp){
            tossUpCount++;
        }
        return score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getTossUpCount() {
        return tossUpCount;
    }

    public Stack<Integer> getScoreTracker() {
        return scoreTracker;
    }

    public Team(String name, int score, int tossUpCount, Stack<Integer> scoreTracker) {
        this.name = name;
        this.score = score;
        this.tossUpCount = tossUpCount;
        this.scoreTracker = scoreTracker;

    }
}
