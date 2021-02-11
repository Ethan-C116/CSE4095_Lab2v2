package com.example.lab2_v2.Control;

import com.example.lab2_v2.Model.QuestionDB;
import com.example.lab2_v2.R;

public class Control {
    private static QuestionDB DB;
    private static int SKIP_COST = 5;
    private static int CORRECT_REWARD = 5;
    public static int PLAYER_SCORE = 0;

    public Control(int SKIP_DEDUCTION, int CORRECT_POINTS, int INITIAL_SCORE){
        SKIP_COST = SKIP_DEDUCTION;
        CORRECT_REWARD = CORRECT_POINTS;
        PLAYER_SCORE = INITIAL_SCORE;
        DB = new QuestionDB();
    }

    public Control(){
        SKIP_COST = 5;
        CORRECT_REWARD = 5;
        PLAYER_SCORE = 0;
        DB = new QuestionDB();
    }

    /**
     * Checks given answer against the currentQuestion's answer
     * if answer is correct adds CORRECT_COST to score.
     * note: will not know if last question's answer was t/f
     * @param ans the player's answer
     * @return rv = 1 on correct, 0 on incorrect, -1 if no more questions
     */
    public static int checkAnswer(boolean ans){
        //if answer is correct 1 else 0
        boolean isCorrect = (DB.getCurrentQuestion().getAnswer() == ans);
        int rv = (isCorrect) ? 1 : 0;
        //update score
        PLAYER_SCORE += (isCorrect) ? (CORRECT_REWARD) : (0);
        //R.string.score = PLAYER_SCORE;
        //go to next question
        try {
            DB.nextQuestion();
        }
        catch(IndexOutOfBoundsException e){
            rv = -1;
        }
        //return answer
        return rv;
    }

    /**
     * goes to nextQuestion in the QuestionDB
     * subtracts SKIP_COST from the score
     * @return rv = -1 if no more questions
     */
    public static int skip(){
        int rv = 0;
        PLAYER_SCORE -= SKIP_COST;
        try {
            DB.nextQuestion();
        }
        catch(IndexOutOfBoundsException e){
            rv = -1;
        }
        return rv;
    }

    public static void reset(){
        DB.resetCount();
        PLAYER_SCORE = 0;
    }

    public static String getQuestionString(){
        return DB.getCurrentQuestion().getString();
    }
}
