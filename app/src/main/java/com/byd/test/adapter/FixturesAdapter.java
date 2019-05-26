package com.byd.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.byd.test.entities.Fixture;
import com.byd.test.entities.Month;
import com.byd.test.utils.Constants;
import com.byd.test.utils.Delegate;
import com.byd.test.utils.MultiViewTypeAdapter;
import com.byd.test.views.FixtureView;
import com.byd.test.views.MonthView;
import com.byd.test.views.ResultView;

import java.util.ArrayList;

public class FixturesAdapter extends MultiViewTypeAdapter {


    public FixturesAdapter() {
        putDelegate(ViewType.MONTH, new MonthDelegate());
        putDelegate(ViewType.DETAIL, new FixtureDelegate());
        putDelegate(ViewType.RESULT, new ResultDelegate());
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class MonthDelegate implements Delegate<ViewHolder, Month>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            return new ViewHolder(new MonthView(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, Month monthEntity) {
            MonthView view = (MonthView) holder.itemView;
            view.bind(monthEntity);
        }
    }

    private class FixtureDelegate implements Delegate<ViewHolder, Fixture>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
           return new ViewHolder(new FixtureView(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, Fixture fixtureEntity) {
            FixtureView fix = (FixtureView)holder.itemView;
            fix.bind(fixtureEntity);
        }
    }

    private class ResultDelegate implements Delegate<ViewHolder, Fixture>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            return new ViewHolder(new ResultView(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, Fixture fixtureEntity) {
            ResultView fix = (ResultView)holder.itemView;
            fix.bind(fixtureEntity);
        }
    }

    private static final class ViewType {
        static final int MONTH = 0x00;
        static final int DETAIL = 0x01;
        static final int RESULT = 0x02;
    }

    public void setFixtureList(ArrayList<Object> list){
        for(Object o: list){
            if(o instanceof Month){
                addItem(ViewType.MONTH,o);
            }
            if(o instanceof Fixture){
                Fixture obj = (Fixture)o;
                if(obj.getType().equals(Constants.FIXTURE_ON_COMMING))
                    addItem(ViewType.DETAIL,o);
                else if(obj.getType().equals(Constants.FIXTURE_FINAL))
                    addItem(ViewType.RESULT,o);
            }
        }
        notifyDataSetChanged();
    }

    public void clearFixtureList(){
        this.clearItemList();
    }
}
