import java.util.Stack;

class Team {
    private String name;
    private int score;
    private int tossUpCount;
    private Stack<Integer> scoreTracker;
    private Stack<Integer> tossUpTracker;

    Team(String name, int score, int tossUpCount, Stack<Integer> scoreTracker, Stack<Integer> tossUpTracker) {
        this.name = name;
        this.score = score;
        this.tossUpCount = tossUpCount;
        this.scoreTracker = scoreTracker;
        this.tossUpTracker = tossUpTracker;
    }

    void penalty() {
        score = score - 5;
        scoreTracker.push(-5);
        tossUpTracker.push(0);
    }

    void setScore(int pointVal, boolean isTossUp){
        score = score + pointVal;
        scoreTracker.push(pointVal);
        if (isTossUp){
            tossUpCount++;
            tossUpTracker.push(1);
        }
        tossUpTracker.push(0);
    }

    void undoScore(int pointVal) {
        if (!scoreTracker.isEmpty() || !tossUpTracker.isEmpty()) {
            score = score - pointVal;
            scoreTracker.pop();
            if (tossUpTracker.pop() == 1) {
                tossUpCount = tossUpCount - 1;
                System.out.println(Integer.toString(tossUpCount));
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

    Stack<Integer> getTossUpTracker() {
        return tossUpTracker;
    }
}
