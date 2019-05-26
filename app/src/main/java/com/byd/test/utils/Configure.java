package com.byd.test.utils;

import com.byd.test.entities.Fixture;
import com.byd.test.entities.Month;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Configure {

    private ArrayList<Fixture> m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12;
    private List<Fixture> listFixtures;
    private ArrayList<Object> finalFixtures;
    private List<ArrayList<Fixture>> listMonths;
    private ArrayList<String> listFilter;

    public Configure(List<Fixture> listFixtures){
        this.listFixtures = listFixtures;

        m1 = new ArrayList<>();
        m2 = new ArrayList<>();
        m3 = new ArrayList<>();
        m4 = new ArrayList<>();
        m5 = new ArrayList<>();
        m6 = new ArrayList<>();
        m7 = new ArrayList<>();
        m8 = new ArrayList<>();
        m9 = new ArrayList<>();
        m10 = new ArrayList<>();
        m11 = new ArrayList<>();
        m12 = new ArrayList<>();

        listMonths = new ArrayList<>();

        listMonths.add(m1);
        listMonths.add(m2);
        listMonths.add(m3);
        listMonths.add(m4);
        listMonths.add(m5);
        listMonths.add(m6);
        listMonths.add(m7);
        listMonths.add(m8);
        listMonths.add(m9);
        listMonths.add(m10);
        listMonths.add(m11);
        listMonths.add(m12);

        listFilter = new ArrayList<>();

        init();
    }

    private void init() {
        for(Fixture f: listFixtures){
            int monthValue = DateUtil.getLocalDate(f.getDate()).getMonthValue();
            listMonths.get(monthValue-1).add(f);
        }
        nextConfiguration();
    }

    private void nextConfiguration() {
        finalFixtures = new ArrayList<>();
        for(List<Fixture> listFix: listMonths){
            if(listFix.size()>0){
                Month m = new Month();
                LocalDate local = DateUtil.getLocalDate(listFix.get(0).getDate());
                m.setMonth(DateUtil.getMonthYearStyle(local));
                finalFixtures.add(m);
                for(Fixture f:listFix){
                    finalFixtures.add(f);
                }
            }
        }
        nextFilterConfiguration();
    }

    private void nextFilterConfiguration() {
        listFilter.add(Constants.ALL);
        for(Fixture f: listFixtures){
            if(!listFilter.contains(f.getCompetitionStage().getCompetition().getName())){
                listFilter.add(f.getCompetitionStage().getCompetition().getName());
            }
        }
    }

    public ArrayList<Object> finalFixtures(){
        return finalFixtures;
    }

    public ArrayList<String> getListFilter(){return  listFilter;};

}
