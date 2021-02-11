package com.example.lab2_v2;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lab2_v2.Control.Control;
import com.example.lab2_v2.Model.Question;

public class QuizFragment extends Fragment {

    private final String BUTTON_TAG = "Button Pressed";
    private final String CONTROL_CALL_TAG = "Call to Control";
    private final String TO_END_TAG = "Go to EndFragment";
    private final String STARTED_TAG = "Fragment Started";
    private final String ANSWER_TAG = "Answer returned";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }
    //<--------------- Notes ------------------->
    // get a question
    // call to Control: Control.getQuestion() -> String
    // update questionView with String
    // when button pressed call Control.checkAnswer(bool) -> boolean
    // (knows the current question so no need to pass the question)
    // Controller updates score and returns the boolean
    // put in Toast
    // Controller will check for nextQuestion after checkAnswer
    // will update @string/question
    // if last question, will be sent to EndFragment
    //----------------------------------------

    /**
     * used to check the return value of CheckAnswer
     * if rv = -1 we will go to EndFragment
     * if rv = 1 display correct, rv = 0 display incorrect
     * @param rv
     */
    private void checkReturnValue (int rv) {
        switch (rv) {
            case 0:
                Toast.makeText(getContext(),
                        "Incorrect :(", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getContext(),
                        "Correct :)", Toast.LENGTH_SHORT).show();
                break;
            case -1:
                //if no more questions go to end screen
                NavHostFragment.findNavController(QuizFragment.this)
                        .navigate(R.id.action_quizFragment_to_endFragment);
                break;
        }
    }

    /**
     * Gets the current Question's string and updates
     * the displayed question with that new string.
     * @param resources current resources library
     * @param questionView the TextView object of the question to update
     */
    private void updateQuestionView(Resources resources, TextView questionView){
        String questionString = resources.getString(R.string.question, Control.getQuestionString());
        questionView.setText(questionString);
    }

    /**
     * Updates TextView with the id scoreView with the
     * current PLAYER_SCORE
     * @param resources current resources library
     * @param scoreView the TextView object of the score to update
     */
    private void updateScoreView(Resources resources, TextView scoreView){
        String scoreString = resources.getString(R.string.score, Control.PLAYER_SCORE);
        scoreView.setText(scoreString);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(STARTED_TAG, "QuizFragment started");

        // removing this breaks the app bc the constructor isn't called
        Control control = new Control();

        //set up score
        final Resources resources = getResources();
        final TextView scoreView = (TextView) view.findViewById(R.id.scoreView);
        updateScoreView(resources, scoreView);

        //set up questionView
        final TextView questionView = (TextView) view.findViewById(R.id.questionView);
        updateQuestionView(resources, questionView);


        // Set up exitButton
        view.findViewById(R.id.exitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TO_END_TAG, "Exit button pressed");
                NavHostFragment.findNavController(QuizFragment.this)
                        .navigate(R.id.action_quizFragment_to_endFragment);
            }
        });

        // Set up trueButton
        view.findViewById(R.id.trueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(BUTTON_TAG, "True button pressed");
                int rv = Control.checkAnswer(true);
                //format and update the score
                updateScoreView(resources, scoreView);

                Log.i(ANSWER_TAG, "answer returned: " + rv);
                checkReturnValue(rv);

                //update questionView
                //check to make sure there are more questions first
                if(rv != -1){
                    updateQuestionView(resources, questionView);
                }

            }
        });

        // Set up falseButton
        view.findViewById(R.id.falseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(BUTTON_TAG, "False button pressed");
                int rv = Control.checkAnswer(false);
                //format and update the score
                updateScoreView(resources, scoreView);

                Log.i(ANSWER_TAG, "answer returned: " + rv);
                checkReturnValue(rv);

                //update questionView
                //check to make sure there are more questions first
                if(rv != -1){
                    updateQuestionView(resources, questionView);
                }
            }
        });

        // Set up skipButton
        view.findViewById(R.id.skipButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(BUTTON_TAG, "Skip button pressed");
                int rv = Control.skip();
                //format and update the score
                updateScoreView(resources, scoreView);

                //if last question goto EndFragment
                if(rv == -1){
                    NavHostFragment.findNavController(QuizFragment.this)
                            .navigate(R.id.action_quizFragment_to_endFragment);
                }
                else {

                    //update questionView
                    //check to make sure there are more questions first
                    if (rv != -1) {
                        updateQuestionView(resources, questionView);
                    }
                }
            }
        });

    }
}