package com.byd.test.remote;


import com.byd.test.entities.Fixture;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IRemote {

    @GET("fixtures.json")
    Call<List<Fixture>> getFixtures();

    @GET("results.json")
    Call<List<Fixture>> getResults();

}
