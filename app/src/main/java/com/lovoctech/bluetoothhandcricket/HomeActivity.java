package com.lovoctech.bluetoothhandcricket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.TurnBasedMultiplayerClient;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.lovoctech.bluetoothhandcricket.game.Game;
import com.lovoctech.bluetoothhandcricket.game.GameListener;
import com.lovoctech.bluetoothhandcricket.game.Over;
import com.lovoctech.bluetoothhandcricket.game.dependency.DaggerGameComponent;
import com.lovoctech.bluetoothhandcricket.game.dependency.GameComponent;
import com.lovoctech.bluetoothhandcricket.game.dependency.GameModule;
import com.lovoctech.bluetoothhandcricket.ui.ChoiceAdapter;
import com.lovoctech.bluetoothhandcricket.ui.ChoiceListener;
import com.lovoctech.bluetoothhandcricket.ui.model.Choice;
import com.lovoctech.bluetoothhandcricket.util.Constants;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 101;

    @BindView(R.id.choice_view)
    RecyclerView choiceView;

    @BindView(R.id.batting_indicator)
    TextView battingIndicator;

    @BindView(R.id.player_score)
    TextView playerScore;

    @BindView(R.id.player_wicket)
    TextView playerWicket;

    @BindView(R.id.opponent_score)
    TextView opponentScore;

    @BindView(R.id.opponent_wicket)
    TextView opponentWicket;

    @BindView(R.id.opponent_choice)
    TextView opponentChoice;

    @BindView(R.id.player_choice)
    TextView playerChoice;

    @Inject
    Game game;

    @Inject
    ArrayList<Choice> choices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        GameComponent gameComponent = DaggerGameComponent
                .builder()
                .gameModule(new GameModule(getGameListener(), this))
                .build();

        gameComponent.inject(this);
        setUpGameUI();
        // signInSilently();

    }

    private void setUpGameUI() {
        ChoiceAdapter choiceAdapter = new ChoiceAdapter(choices, new ChoiceListener() {
            @Override
            public void onChoice(Choice choice) {
                game.choice(choice);
            }
        });
        choiceView.setLayoutManager(new GridLayoutManager(this, 3));
        choiceView.setAdapter(choiceAdapter);
    }

    private GameListener getGameListener() {
        return new GameListener() {
            @Override
            public void playerScore(int score) {
                Log.d(Constants.TAG, "playerScore " + score);
                playerScore.setText(String.valueOf(score));
            }

            @Override
            public void playerWicketFell(int totalWickets, int remainingWickets) {
                Log.d(Constants.TAG, "playerWicketFell " + remainingWickets + "/" + totalWickets);
                playerWicket.setText(remainingWickets + "/" + totalWickets);
            }

            @Override
            public void playerLose() {
                Log.d(Constants.TAG, "playerLose ");
                battingIndicator.setText("Lose");
            }

            @Override
            public void playerWin() {
                Log.d(Constants.TAG, "playerWin ");
                battingIndicator.setText("Win");
            }

            @Override
            public void playerAllOut(int wickets) {
                Log.d(Constants.TAG, "playerAllOut ");
                battingIndicator.setText("Bowling");
                playerWicket.setText(wickets + "/" + wickets);
            }

            @Override
            public void playerOversFinish() {
                Log.d(Constants.TAG, "playerOversFinish ");
                battingIndicator.setText("Bowling");
            }

            @Override
            public void draw() {
                Log.d(Constants.TAG, "draw ");
            }

            @Override
            public void opponentScore(int score) {
                Log.d(Constants.TAG, "opponentScore " + score);
                opponentScore.setText(String.valueOf(score));
            }

            @Override
            public void opponentWicketFell(int totalWickets, int remainingWickets) {
                Log.d(Constants.TAG, "opponentWicketFell " + remainingWickets + "/" + totalWickets);
                opponentWicket.setText(remainingWickets + "/" + totalWickets);
            }

            @Override
            public void opponentAllOut(int wickets) {
                Log.d(Constants.TAG, "opponentAllOut ");
                battingIndicator.setText("Batting");
                opponentWicket.setText(wickets + "/" + wickets);
            }

            @Override
            public void choice(Choice playerChoice, Choice opponentChoice, Over overs) {
                Log.d(Constants.TAG, "playerChoice " + playerChoice + "\nopponentChoice " + opponentChoice + " Overs- " + overs);
                HomeActivity.this.playerChoice.setText(playerChoice.toString());
                HomeActivity.this.opponentChoice.setText(String.valueOf(opponentChoice));
            }

            @Override
            public void opponentOversFinish() {
                Log.d(Constants.TAG, "opponentOversFinish ");
                battingIndicator.setText("Batting");
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
                            onSignIn(signedInAccount);
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
                onSignIn(signedInAccount);
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

    private void onSignIn(GoogleSignInAccount googleSignInAccount) {
        TurnBasedMultiplayerClient turnBasedMultiplayerClient = Games.getTurnBasedMultiplayerClient(this, googleSignInAccount);
        Bundle autoMatchCriteria = RoomConfig.createAutoMatchCriteria(1, 1, 0);
        TurnBasedMatchConfig turnBasedMatchConfig = TurnBasedMatchConfig.builder().setAutoMatchCriteria(autoMatchCriteria).build();
        turnBasedMultiplayerClient.createMatch(turnBasedMatchConfig)
                .addOnSuccessListener(new OnSuccessListener<TurnBasedMatch>() {
                    @Override
                    public void onSuccess(TurnBasedMatch turnBasedMatch) {

                    }
                });
    }

}
