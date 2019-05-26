package com.byd.test.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import com.byd.test.entities.Fixture;
import com.byd.test.remote.IRemote;
import com.byd.test.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FixtureRepository {

    private ArrayList<Object> objects = new ArrayList<>();
    private final MutableLiveData<List<Fixture>> fixturesLive = new MutableLiveData<>();


    public FixtureRepository(){

    }

    public ArrayList<Object> getFixturesRemote(String whatRemote){
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        IRemote remote = retrofit.create(IRemote.class);

        Call<List<Fixture>> fixtures = null;
        if(whatRemote.equals(Constants.FIXTURES))
            fixtures = remote.getFixtures();
        else if(whatRemote.equals(Constants.RESULTS))
            fixtures = remote.getResults();

        fixtures
                .enqueue(new Callback<List<Fixture>>() {
                    @Override
                    public void onResponse(Call<List<Fixture>> call, Response<List<Fixture>> response) {
                        List<Fixture> fixtures = response.body();
                        fixturesLive.setValue(fixtures);
                    }

                    @Override
                    public void onFailure(Call<List<Fixture>> call, Throwable t) {
                        Log.e("ex",t.getMessage());
                    }
                });

        return null;

    }


    public MutableLiveData<List<Fixture>> getFixturesLive(){
        return this.fixturesLive;
    }

}
