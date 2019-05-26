package com.byd.test.views;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.byd.test.R;
import com.byd.test.entities.Month;

public class MonthView extends RelativeLayout {

    private TextView txtMonth;

    public MonthView(Context context) {
        super(context);
        inflate(context, R.layout.month_item,this);
        txtMonth = findViewById(R.id.txtMonth);
    }

    @Override
    public void setAccessibilityDelegate(View.AccessibilityDelegate delegate) {
        findViewById(R.id.month_container).setAccessibilityDelegate(delegate);
    }

    public void bind(Month m){
        txtMonth.setText(m.getMonth());
    }

}
