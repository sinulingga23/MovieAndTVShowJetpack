package com.example.movieandtvshowjetpack.ui.menu.favorite.tvshow;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandtvshowjetpack.R;
import com.example.movieandtvshowjetpack.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTVShowFragment extends Fragment {
    private RecyclerView rvFavoriteTVShow;
    private ProgressBar progressBar;
    private FavoriteTVShowPagedAdapter favoriteTVShowPagedAdapter;
    private FavoriteTVShowViewModel favoriteTVShowViewModel;

    public FavoriteTVShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle SavedInstanceState) {
        rvFavoriteTVShow = view.findViewById(R.id.rv_fav_tv_show);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            progressBar.setVisibility(View.VISIBLE);
            favoriteTVShowViewModel = obtainViewModel(getActivity());

            favoriteTVShowPagedAdapter = new FavoriteTVShowPagedAdapter();
            favoriteTVShowViewModel.getFavoriteListTVShowAsPaged().observe(this, favoriteListTVShow -> {
                if (favoriteListTVShow != null) {
                    switch (favoriteListTVShow.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            favoriteTVShowPagedAdapter.submitList(favoriteListTVShow.data);
                            favoriteTVShowPagedAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            rvFavoriteTVShow.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFavoriteTVShow.setHasFixedSize(true);
            rvFavoriteTVShow.setAdapter(favoriteTVShowPagedAdapter);
        }
    }

    @NonNull
    private static FavoriteTVShowViewModel obtainViewModel(FragmentActivity fragmentActivity) {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(fragmentActivity.getApplication());
        return ViewModelProviders.of(fragmentActivity, viewModelFactory).get(FavoriteTVShowViewModel.class);
    }
}
