package com.byd.test.views;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.test.R;
import com.byd.test.entities.Fixture;
import com.byd.test.utils.Constants;
import com.byd.test.utils.DateUtil;
import java.time.format.TextStyle;
import java.util.Locale;

public class FixtureView extends RelativeLayout {


    private TextView txtLigue, txtPosponed, txtDate,txtPlace, txtTeam1, txtTeam2, txtDateDay, txtDay;

    public FixtureView(Context context) {
        super(context);
        inflate(context, R.layout.fixture_item,this);

        txtLigue = findViewById(R.id.txtLigue);
        txtPosponed = findViewById(R.id.txtPosponed);
        txtDate = findViewById(R.id.txtDate);
        txtPlace = findViewById(R.id.txtPlace);
        txtTeam1 = findViewById(R.id.txtTeam1);
        txtTeam2 = findViewById(R.id.txtTeam2);
        txtDateDay = findViewById(R.id.txtDateDay);
        txtDay = findViewById(R.id.txtDay);
    }

    @Override
    public void setAccessibilityDelegate(View.AccessibilityDelegate delegate) {
        findViewById(R.id.fixture_container).setAccessibilityDelegate(delegate);
    }

    public void bind(Fixture fixture){
        txtLigue.setText(fixture.getCompetitionStage().getCompetition().getName());
        txtPosponed.setText(fixture.getState());
        txtPlace.setText(fixture.getVenue().getName());
        txtDate.setText(DateUtil.getDateFormatted(DateUtil.getLocalDate(fixture.getDate()),fixture.getDate()));
        txtTeam1.setText(fixture.getHomeTeam().getName());
        txtTeam2.setText(fixture.getAwayTeam().getName());
        txtDateDay.setText(DateUtil.getLocalDate(fixture.getDate()).getDayOfMonth()+"");
        txtDay.setText(DateUtil.getLocalDate(fixture.getDate()).getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        evaluePosponed(fixture);
    }

    private void evaluePosponed(Fixture fixture) {
        if(fixture.getState()!=null){
            if(fixture.getState().equals(Constants.POSTPONED)){
               showPostponed();
            }else{
                hidePostponed();
            }
        }else{
            hidePostponed();
        }
    }

    private void showPostponed(){
        txtPosponed.setVisibility(VISIBLE);
        txtPosponed.setBackgroundColor(Color.parseColor("#DB002C"));
        txtDate.setTextColor(Color.parseColor("#DB002C"));
    }

    private void hidePostponed(){
        txtPosponed.setVisibility(INVISIBLE);
        txtPosponed.setBackgroundColor(Color.WHITE);
        txtDate.setTextColor(Color.parseColor("#616161"));
    }

}
