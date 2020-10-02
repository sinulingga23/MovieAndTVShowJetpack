package com.example.movieandtvshowjetpack.ui.menu.tvshow;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.movieandtvshowjetpack.R;
import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.viewmodel.ViewModelFactory;

import java.util.ArrayList;

import static com.example.movieandtvshowjetpack.vo.Status.LOADING;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {
    private final static String TAG = TVShowFragment.class.getSimpleName();
    private RecyclerView rvTVShow;
    private TVShowAdapter tvShowAdapter;
    private ProgressBar progressBar;
    private TVShowViewModel tvShowViewModel;
    private ArrayList<TVShowEntity> listTVShow;


    public TVShowFragment() {
        // Required empty public constructor
    }

    public static TVShowFragment newInstance() {
        return new TVShowFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTVShow = view.findViewById(R.id.rv_tvshow);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            progressBar.setVisibility(View.VISIBLE);
            tvShowViewModel = obtainViewModel(getActivity());

            tvShowAdapter =  new TVShowAdapter(getActivity());
            tvShowViewModel.setUsername("TVShow");
            tvShowViewModel.listTVShow.observe(this, listTVShow -> {
                if (listTVShow != null) {
                    switch (listTVShow.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            tvShowAdapter.setTvShowList(listTVShow.data);
                            tvShowAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi Kelasahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            rvTVShow.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTVShow.setHasFixedSize(true);
            rvTVShow.setAdapter(tvShowAdapter);
        }
    }

    @NonNull
    private static TVShowViewModel obtainViewModel(FragmentActivity fragmentActivity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(fragmentActivity.getApplication());
        return ViewModelProviders.of(fragmentActivity, viewModelFactory).get(TVShowViewModel.class);
    }
}
