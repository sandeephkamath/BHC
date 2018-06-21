package com.lovoctech.bluetoothhandcricket;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.lovoctech.bluetoothhandcricket.game.Game;
import com.lovoctech.bluetoothhandcricket.game.GameConfig;
import com.lovoctech.bluetoothhandcricket.game.GameListener;
import com.lovoctech.bluetoothhandcricket.ui.ChoiceAdapter;
import com.lovoctech.bluetoothhandcricket.ui.ChoiceListener;
import com.lovoctech.bluetoothhandcricket.ui.model.Choice;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 101;
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

        signInSilently();
    }

    private GameListener getGameListener() {
        return new GameListener() {
            @Override
            public void playerScore(int score, int playerChoice, int opponentChoice) {
                Log.d(Constants.TAG, "playerScore " + score + "\n playerChoice " + playerChoice + "\nopponentChoice " + opponentChoice);
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
            public void opponentScore(int score, int playerChoice, int opponentChoice) {
                Log.d(Constants.TAG, "opponentScore " + score + "\n playerChoice " + playerChoice + "\nopponentChoice " + opponentChoice);
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


    private void signInSilently() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        signInClient.silentSignIn().addOnCompleteListener(this,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if (task.isSuccessful()) {
                            // The signed in account is stored in the task's result.
                            GoogleSignInAccount signedInAccount = task.getResult();
                        } else {
                            // Player will need to sign-in explicitly using via UI
                            startSignInIntent();
                        }
                    }
                });
    }

    private void startSignInIntent() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        Intent intent = signInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // The signed in account is stored in the result.
                GoogleSignInAccount signedInAccount = result.getSignInAccount();
            } else {
                String message = result.getStatus().getStatusMessage();
                if (message == null || message.isEmpty()) {
                    message = getString(R.string.signin_other_error);
                }
                new AlertDialog.Builder(this).setMessage(message)
                        .setNeutralButton(android.R.string.ok, null).show();
            }
        }
    }

}
