package com.example.movieandtvshowjetpack.ui.menu.favorite.movie;


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
public class FavoriteMovieFragment extends Fragment {
    private RecyclerView rvFavoriteMovie;
    private ProgressBar progressBar;
    private FavoriteMoviePagedAdapter favoriteMoviePagedAdapter;
    private FavoriteMovieViewModel favoriteMovieViewModel;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvFavoriteMovie = view.findViewById(R.id.rv_fav_movie);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            progressBar.setVisibility(View.VISIBLE);
            favoriteMovieViewModel = obtainViewModel(getActivity());

            favoriteMoviePagedAdapter = new FavoriteMoviePagedAdapter();
            favoriteMovieViewModel.getFavoriteListMovieAsPaged().observe(this, favoriteListMovie -> {
                if (favoriteListMovie != null) {
                    switch (favoriteListMovie.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            favoriteMoviePagedAdapter.submitList(favoriteListMovie.data);
                            favoriteMoviePagedAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            rvFavoriteMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFavoriteMovie.setHasFixedSize(true);
            rvFavoriteMovie.setAdapter(favoriteMoviePagedAdapter);
        }
    }

    @NonNull
    private static FavoriteMovieViewModel obtainViewModel(FragmentActivity fragmentActivity) {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(fragmentActivity.getApplication());
        return ViewModelProviders.of(fragmentActivity, viewModelFactory).get(FavoriteMovieViewModel.class);
    }
}
