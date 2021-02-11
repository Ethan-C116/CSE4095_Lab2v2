package com.example.lab2_v2.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionDB {
    // A HashMap to store Question objects
    private static HashMap<String, Question> DB;
    private final int numQuestions = 10;
    //start at question 0
    private static int currentQuestion = 0;
    //list for question strings
    private static final ArrayList<String> questionStrings = new ArrayList<String>();
    //list for question answers
    private static final ArrayList<Boolean> questionAnswers = new ArrayList<Boolean>();
    private final String DB_TAG = "DB message";

    // Build out the two lists with corresponding questions
    private void constructQuestions(){
        questionStrings.add("There are 8 planets in the solar system.");
        questionAnswers.add(true);
        questionStrings.add("Hummingbird eggs are the size of a quarter.");
        questionAnswers.add(false);
        questionStrings.add("Squid have 5 tentacles.");
        questionAnswers.add(true);
        questionStrings.add("5*3 = 15.");
        questionAnswers.add(true);
        questionStrings.add("The U.S. has 5 time zones.");
        questionAnswers.add(false);
        questionStrings.add("The first blue LED was made in the 1990s.");
        questionAnswers.add(true);
        questionStrings.add("Snow can form at temperatures up to 38 deg F.");
        questionAnswers.add(true);
        questionStrings.add("Cows can live up to 100 years.");
        questionAnswers.add(false);
        questionStrings.add("A group of crows is called a murder.");
        questionAnswers.add(true);
        questionStrings.add("U.S. presidential elections take place every 6 years.");
        questionAnswers.add(false);
    }

    /**
     * Constructor function.
     * Sets up a HashMap DB of size numQuestions.
     */
    public QuestionDB(){
        constructQuestions();
        Log.i(DB_TAG, "Questions constructed.");
        DB = new HashMap<String, Question>(numQuestions);
        // Create HashMap with key '0' up to '[numQuestions]'
        for(int q = 0; q < numQuestions; q++){
            //put in DB <'q', Question(qString, qAnswer)>
            DB.put(Integer.toString(q),
                    new Question(questionStrings.get(q), questionAnswers.get(q)));
        }
        Log.i(DB_TAG, "HashMap construction complete.");
        /*
        Log.d(DB_TAG, questionStrings.toString());
        Log.d(DB_TAG, questionAnswers.toString());
        Log.d(DB_TAG, DB.toString());
        Log.d(DB_TAG, "get1 " + DB.get("1").getString());
        Log.d(DB_TAG, "get0 " + DB.get("0").getString());
        */
    }

    public Question getCurrentQuestion(){
        Question question = DB.get(Integer.toString(currentQuestion));
        Log.i(DB_TAG, "Current question: " + question.getString());
        return question;
    }

    /**
     * Returns the next question in the DB
     * and updates currentQuestion. Returns
     * null if no more questions
     */
    public void nextQuestion(){
        currentQuestion++;
        if(currentQuestion >= numQuestions){
            throw new IndexOutOfBoundsException("No more questions in DB");
        }
        else{
            Question question = DB.get(Integer.toString(currentQuestion));
            Log.i(DB_TAG, "Next question called: " + question.getString());
        }
    }

    public void resetCount(){
        currentQuestion = 0;
    }

}
