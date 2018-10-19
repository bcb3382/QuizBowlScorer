import java.util.Stack;

class Team {
    private String name;
    private int score;
    private int tossUpCount;
    private Stack<Integer> scoreTracker;
    private Stack<String> tossUpTracker;

    Team(String name, int score, int tossUpCount, Stack<Integer> scoreTracker, Stack<String> tossUpTracker) {
        this.name = name;
        this.score = score;
        this.tossUpCount = tossUpCount;
        this.scoreTracker = scoreTracker;
        this.tossUpTracker = tossUpTracker;
    }

    void setScore(int pointVal, boolean isTossUp){
        score = score + pointVal;
        scoreTracker.push(pointVal);
        if (isTossUp){
            tossUpCount++;
            tossUpTracker.push("true");
        }
        tossUpTracker.push("false");
    }

    void undoScore(int pointVal) {
        if (!scoreTracker.isEmpty() || !tossUpTracker.isEmpty()) {
            score = score - pointVal;
            scoreTracker.pop();
            if (tossUpTracker.peek().equals("true")) {
                tossUpCount--;
                tossUpTracker.pop();
            }
        }
        if (scoreTracker.isEmpty() || tossUpTracker.isEmpty()){
            System.out.println("Nothing more to undo");
        }
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getScore() {

        return score;
    }

    int getTossUpCount() {
        return tossUpCount;
    }

    Stack<Integer> getScoreTracker() {
        return scoreTracker;
    }
}
