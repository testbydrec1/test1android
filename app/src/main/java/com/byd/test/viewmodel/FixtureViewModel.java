package com.byd.test.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.byd.test.entities.Fixture;
import com.byd.test.repository.FixtureRepository;
import com.byd.test.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FixtureViewModel extends AndroidViewModel {

    private FixtureRepository fixtureRepository;
    private MutableLiveData competitionLiveData = new MutableLiveData();
    private MutableLiveData<List<Fixture>> fixturesLive = new MutableLiveData<>();
    private LifecycleOwner lifecycleOwner;
    private List<Fixture> cacheList = new ArrayList<>();

    public FixtureViewModel(@NonNull Application application) {
        super(application);
        fixtureRepository = new FixtureRepository();

        fixturesLive = (MutableLiveData<List<Fixture>>)
                Transformations.switchMap(competitionLiveData, competition -> {
                    return filterCompetitions(competition);
                });
    }

    public void callData(String whatRemote){

        fixtureRepository.getFixturesRemote(whatRemote);
        fixtureRepository.getFixturesLive()
                .observe(this.lifecycleOwner, new Observer<List<Fixture>>() {
                    @Override
                    public void onChanged(@Nullable List<Fixture> fixtures) {
                        fixturesLive.setValue(fixtures);
                        cacheList = fixtures;
                    }
                });
    }

    private MutableLiveData<List<Fixture>> filterCompetitions(Object competition) {

        if(competition.toString().equals(Constants.ALL)){
            this.fixturesLive.setValue(this.cacheList);
        }else{
            List<Fixture> newList = cacheList.stream().filter(i->i.getCompetitionStage().getCompetition().getName().equals(competition)).collect(Collectors.toList());
            this.fixturesLive.setValue(newList);
        }

        return null;

    }

    public MutableLiveData<List<Fixture>> getFixturesLive(){
        return fixturesLive;
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    public void setCompetition(String newCompetition){
        this.competitionLiveData.setValue(newCompetition);
    }


}
