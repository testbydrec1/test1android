package com.byd.test.views;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.test.R;
import com.byd.test.entities.Fixture;
import com.byd.test.utils.Constants;
import com.byd.test.utils.DateUtil;

public class ResultView extends RelativeLayout {


    private TextView txtLigue, txtDate,txtPlace, txtTeam1, txtTeam2, txtResult1, txtResult2;

    public ResultView(Context context) {
        super(context);
        inflate(context, R.layout.fixture_result,this);

        txtLigue = findViewById(R.id.txtLigue);
        txtDate = findViewById(R.id.txtDate);
        txtPlace = findViewById(R.id.txtPlace);
        txtTeam1 = findViewById(R.id.txtTeam1);
        txtTeam2 = findViewById(R.id.txtTeam2);
        txtResult1 = findViewById(R.id.result1);
        txtResult2 = findViewById(R.id.result2);
    }

    @Override
    public void setAccessibilityDelegate(AccessibilityDelegate delegate) {
        findViewById(R.id.fixture_container).setAccessibilityDelegate(delegate);
    }

    public void bind(Fixture fixture){
        txtLigue.setText(fixture.getCompetitionStage().getCompetition().getName());
        txtPlace.setText(fixture.getVenue().getName());
        txtDate.setText(DateUtil.getDateFormatted(DateUtil.getLocalDate(fixture.getDate()),fixture.getDate()));
        txtTeam1.setText(fixture.getHomeTeam().getName());
        txtTeam2.setText(fixture.getAwayTeam().getName());
        txtResult1.setText(fixture.getScore().getHome());
        txtResult2.setText(fixture.getScore().getAway());

        evaluateWinner(fixture);
    }

    private void evaluateWinner(Fixture fixture) {
        if(fixture.getScore().getWinner()!=null){
            if(fixture.getScore().getWinner().equals(Constants.WINNER_HOME)){
                txtTeam1.setTextColor(Color.parseColor("#DB002C"));
                txtTeam2.setTextColor(Color.parseColor("#000675"));
            }else if(fixture.getScore().getWinner().equals(Constants.WINNER_AWAY)){
                txtTeam1.setTextColor(Color.parseColor("#000675"));
                txtTeam2.setTextColor(Color.parseColor("#DB002C"));
            }
        }else{
            txtTeam1.setTextColor(Color.parseColor("#000675"));
            txtTeam2.setTextColor(Color.parseColor("#000675"));
        }
    }


}
