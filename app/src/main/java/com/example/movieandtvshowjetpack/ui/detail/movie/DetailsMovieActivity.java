package com.example.movieandtvshowjetpack.ui.detail.movie;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.request.RequestOptions;
import com.example.movieandtvshowjetpack.BuildConfig;
import com.example.movieandtvshowjetpack.R;
import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.utils.GlideApp;
import com.example.movieandtvshowjetpack.viewmodel.ViewModelFactory;

public class DetailsMovieActivity extends AppCompatActivity{
    private static final String TAG = DetailsMovieActivity.class.getSimpleName();
    public static final String EXTRA_MOVIE = "extra_movie";
    private ImageView poster;
    private TextView title;
    private TextView date;
    private TextView overview;
    private TextView voteAverage;
    private ProgressBar progressBar;
    private ImageView icStar;
    private ImageView icDate;
    private TextView labelOverview;
    private DetailsMovieViewModel detailsMovieViewModel;
    private Menu menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Details Movie");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setUp();


        detailsMovieViewModel  = obtainViewModel(this);
        Bundle extraMovie = getIntent().getExtras();
        if (extraMovie != null) {
            int movieId = extraMovie.getInt(EXTRA_MOVIE);
            detailsMovieViewModel.setMovieId(movieId);
        }
        detailsMovieViewModel.movieEntity.observe(this, movieEntityResource -> {
            if (movieEntityResource != null) {
                switch (movieEntityResource.status) {
                    case LOADING:
                        hiddenView();
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        if (movieEntityResource.data != null) {
                            showUpView();
                            progressBar.setVisibility(View.GONE);
                            populateMovie(movieEntityResource.data);
                            Log.d(TAG, "Status: " + movieEntityResource.data.isFavorite());
                        }
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    private void setUp() {
        progressBar = findViewById(R.id.progress_bar);
        poster = findViewById(R.id.poster);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date_detail);
        overview = findViewById(R.id.overview_detail);
        voteAverage = findViewById(R.id.vote_average_detail);
        icStar = findViewById(R.id.ic_vote_average);
        icDate = findViewById(R.id.ic_date);
        labelOverview = findViewById(R.id.overview);
    }

    private void hiddenView() {
        poster.setVisibility(View.INVISIBLE);
        title.setVisibility(View.INVISIBLE);
        date.setVisibility(View.INVISIBLE);
        overview.setVisibility(View.INVISIBLE);
        voteAverage.setVisibility(View.INVISIBLE);
        icStar.setVisibility(View.INVISIBLE);
        icDate.setVisibility(View.INVISIBLE);
        labelOverview.setVisibility(View.INVISIBLE);
    }

    private void showUpView() {
        poster.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        date.setVisibility(View.VISIBLE);
        overview.setVisibility(View.VISIBLE);
        voteAverage.setVisibility(View.VISIBLE);
        icDate.setVisibility(View.VISIBLE);
        icStar.setVisibility(View.VISIBLE);
        labelOverview.setVisibility(View.VISIBLE);
    }

    private void populateMovie(MovieEntity movieEntity) {
        assert movieEntity != null;
        GlideApp.with(getApplicationContext())
                .load(BuildConfig.URL_POSTER_PATH + movieEntity.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)).error(R.drawable.ic_error)
                .into(poster);
        Log.d(TAG, "URL_POSTER_PATH: " + BuildConfig.URL_POSTER_PATH);
        Log.d(TAG,"POSTER_PATH: " + movieEntity.getPosterPath());
        Log.d(TAG, "FULL_URL_POSTER_PATH: " + BuildConfig.URL_POSTER_PATH  + movieEntity.getPosterPath());


        title.setText(movieEntity.getTitle());
        date.setText(movieEntity.getReleaseDate());
        overview.setText(movieEntity.getOverview());
        voteAverage.setText(String.format("%s", (float)movieEntity.getVoteAverage()));
    }

    @NonNull
    private static DetailsMovieViewModel obtainViewModel(AppCompatActivity appCompatActivity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(appCompatActivity.getApplication());
        return ViewModelProviders.of(appCompatActivity, viewModelFactory).get(DetailsMovieViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        detailsMovieViewModel.movieEntity.observe(this, movieEntity -> {
            if (movieEntity != null) {
                switch (movieEntity.status) {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        if (movieEntity.data != null) {
                            progressBar.setVisibility(View.GONE);
                            boolean state = movieEntity.data.isFavorite();
                            setFavoriteState(state);
                        }
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        break;
                }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_favorite) {
            detailsMovieViewModel.setFavorite();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void setFavoriteState(boolean state) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.action_favorite);
        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_true));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_false));
        }
    }
}
