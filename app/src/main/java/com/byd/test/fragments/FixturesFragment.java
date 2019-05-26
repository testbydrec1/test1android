package com.byd.test.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.byd.test.R;
import com.byd.test.adapter.FixturesAdapter;
import com.byd.test.entities.Fixture;
import com.byd.test.repository.FixtureRepository;
import com.byd.test.utils.Configure;
import com.byd.test.utils.Constants;
import com.byd.test.viewmodel.FixtureViewModel;
import java.util.ArrayList;
import java.util.List;


public class FixturesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView rv_fixtures;
    private FixturesAdapter adapter;
    private FixtureRepository repository;
    private FixtureViewModel fixtureViewModel;
    private List<String> listFilter;
    private Spinner sp_competitions;
    private ArrayAdapter<String> spinnerAdapter;

    public FixturesFragment() {
    }

    public static FixturesFragment newInstance(String param1, String param2) {
        FixturesFragment fragment = new FixturesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fixtures, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

    }

    private void init(View view){

        sp_competitions = (Spinner)view.findViewById(R.id.sp_competitions);
        sp_competitions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setCompetition(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rv_fixtures = view.findViewById(R.id.rv_fixtures);
        rv_fixtures.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_fixtures.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv_fixtures.getContext(),
                new LinearLayoutManager(getContext()).getOrientation());
        rv_fixtures.addItemDecoration(dividerItemDecoration);

        adapter = new FixturesAdapter();
        repository = new FixtureRepository();
        fixtureViewModel = ViewModelProviders.of(this).get(FixtureViewModel.class);
        fixtureViewModel.setLifecycleOwner(getViewLifecycleOwner());
        fixtureViewModel.callData(Constants.FIXTURES);

        fixtureViewModel.getFixturesLive()
                .observe(this, new Observer<List<Fixture>>() {
                    @Override
                    public void onChanged(@Nullable List<Fixture> fixtures) {
                        configureList(fixtures);
                        configureFilter();
                    }
                });

        listFilter = new ArrayList<>();
    }

    private void configureFilter() {
        spinnerAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,this.listFilter);
        if(sp_competitions.getAdapter()==null){
            sp_competitions.setAdapter(spinnerAdapter);
        }
    }

    private void configureList(List<Fixture> fixtures) {
        Configure conf = new Configure(fixtures);
        adapter.clearFixtureList();
        adapter.setFixtureList(conf.finalFixtures());
        rv_fixtures.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        this.listFilter = conf.getListFilter();
    }

    public void setCompetition(String competition){
        fixtureViewModel.setCompetition(competition);
    }

}
