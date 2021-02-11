package com.example.lab2_v2;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lab2_v2.Control.Control;

public class EndFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //update score
        Resources resources = getResources();
        TextView scoreView = (TextView) view.findViewById(R.id.finalScoreView);
        String scoreString = resources.getString(R.string.score, Control.PLAYER_SCORE);
        scoreView.setText(scoreString);

        //set up restart button
        view.findViewById(R.id.restartButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reset DB
                Control.reset();

                //go back to start fragment
                NavHostFragment.findNavController(EndFragment.this)
                        .navigate(R.id.action_endFragment_to_startFragment);
            }
        });
    }
}