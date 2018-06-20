package com.lt.hc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lt.hc.game.Game;
import com.lt.hc.game.GameConfig;
import com.lt.hc.game.GameListener;
import com.lt.hc.game.Player;
import com.lt.hc.ui.ChoiceAdapter;
import com.lt.hc.ui.ChoiceListener;
import com.lt.hc.ui.model.Choice;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.choice_view)
    RecyclerView choiceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


        GameConfig gameConfig = new GameConfig();
        gameConfig.setWickets(1);
        gameConfig.setAIOpponent(true);
        final Game game = new Game(gameConfig, getGameListener());



        ArrayList<Choice> choices = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            Choice choice = new Choice();
            choice.setValue(i);
            choices.add(choice);
        }

        ChoiceAdapter choiceAdapter = new ChoiceAdapter(choices, new ChoiceListener() {
            @Override
            public void onChoice(Choice choice) {
                game.choice(choice.getValue());
            }
        });
        choiceView.setLayoutManager(new LinearLayoutManager(this));
        choiceView.setAdapter(choiceAdapter);





    }

    private GameListener getGameListener() {
        return new GameListener() {
            @Override
            public void playerScore(int score,int playerChoice,int opponentChoice) {
                Log.d(Constants.TAG, "playerScore " + score +"\n playerChoice " +playerChoice+"\nopponentChoice "+opponentChoice);
            }

            @Override
            public void playerWicketFell(int totalWickets, int remainingWickets) {
                Log.d(Constants.TAG, "playerWicketFell " + remainingWickets + "/" + totalWickets);
            }

            @Override
            public void playerLose() {
                Log.d(Constants.TAG, "playerLose ");
            }

            @Override
            public void playerWin() {
                Log.d(Constants.TAG, "playerWin ");
            }

            @Override
            public void playerAllOut() {
                Log.d(Constants.TAG, "playerAllOut ");
            }

            @Override
            public void draw() {
                Log.d(Constants.TAG, "draw ");
            }

            @Override
            public void opponentScore(int score,int playerChoice,int opponentChoice) {
                Log.d(Constants.TAG, "opponentScore " + score +"\n playerChoice " +playerChoice+"\nopponentChoice "+opponentChoice);
            }

            @Override
            public void opponentWicketFell(int totalWickets, int remainingWickets) {
                Log.d(Constants.TAG, "opponentWicketFell " + remainingWickets + "/" + totalWickets);
            }

            @Override
            public void opponentLose() {
                Log.d(Constants.TAG, "opponentLose ");
            }

            @Override
            public void opponentWin() {
                Log.d(Constants.TAG, "opponentWin ");
            }

            @Override
            public void opponentAllOut() {
                Log.d(Constants.TAG, "opponentAllOut ");
            }
        };
    }
}
