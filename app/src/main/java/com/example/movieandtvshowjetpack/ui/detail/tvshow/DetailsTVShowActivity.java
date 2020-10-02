package com.example.movieandtvshowjetpack.ui.detail.tvshow;

import android.os.Bundle;

import com.bumptech.glide.request.RequestOptions;
import com.example.movieandtvshowjetpack.BuildConfig;
import com.example.movieandtvshowjetpack.R;
import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.utils.GlideApp;
import com.example.movieandtvshowjetpack.viewmodel.ViewModelFactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DetailsTVShowActivity extends AppCompatActivity {
    private static final String TAG = DetailsTVShowActivity.class.getSimpleName();
    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    private ImageView poster;
    private TextView title;
    private TextView firstAirDate;
    private TextView overview;
    private TextView voteAverage;
    private ProgressBar progressBar;
    private ImageView icStar;
    private ImageView icDate;
    private TextView labelOverview;
    private DetailsTVShowViewModel detailsTVShowViewModel;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tvshow);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Details TVShow");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setUp();

        detailsTVShowViewModel = obtainViewModel(this);
        Bundle extraTVShow = getIntent().getExtras();
        if (extraTVShow != null) {
            int tvShowId = extraTVShow.getInt(EXTRA_TV_SHOW);
            detailsTVShowViewModel.setTvShowId(tvShowId);
        }
        detailsTVShowViewModel.tvShowEntity.observe(this, tvShowEntityResource -> {
            if (tvShowEntityResource != null) {
                switch (tvShowEntityResource.status) {
                    case LOADING:
                        hiddenView();
                        break;
                    case SUCCESS:
                        if (tvShowEntityResource.data != null) {
                            showUpView();
                            progressBar.setVisibility(View.GONE);
                            populateTVShow(tvShowEntityResource.data);
                        }
                        break;
                    case ERROR:
                        showUpView();
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
        firstAirDate = findViewById(R.id.date_detail);
        overview = findViewById(R.id.overview_detail);
        voteAverage = findViewById(R.id.vote_average_detail);
        icStar = findViewById(R.id.ic_vote_average);
        icDate = findViewById(R.id.ic_date);
        labelOverview = findViewById(R.id.overview);

    }

    private void hiddenView() {
        poster.setVisibility(View.INVISIBLE);
        title.setVisibility(View.INVISIBLE);
        firstAirDate.setVisibility(View.INVISIBLE);
        overview.setVisibility(View.INVISIBLE);
        voteAverage.setVisibility(View.INVISIBLE);
        icDate.setVisibility(View.INVISIBLE);
        icStar.setVisibility(View.INVISIBLE);
        labelOverview.setVisibility(View.INVISIBLE);
    }

    private void showUpView() {
        poster.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        firstAirDate.setVisibility(View.VISIBLE);
        overview.setVisibility(View.VISIBLE);
        voteAverage.setVisibility(View.VISIBLE);
        icDate.setVisibility(View.VISIBLE);
        icStar.setVisibility(View.VISIBLE);
        labelOverview.setVisibility(View.VISIBLE);
    }

    private void populateTVShow(TVShowEntity tvShowEntity) {
        assert tvShowEntity != null;
        GlideApp.with(getApplicationContext())
                .load(BuildConfig.URL_POSTER_PATH + tvShowEntity.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)).error(R.drawable.ic_error)
                .into(poster);
        Log.d(TAG, "URL_POSTER_PATH: " + BuildConfig.URL_POSTER_PATH);
        Log.d(TAG,"POSTER_PATH: " + tvShowEntity.getPosterPath());
        Log.d(TAG, "FULL_URL_POSTER_PATH: " + BuildConfig.URL_POSTER_PATH  + tvShowEntity.getPosterPath());


        title.setText(tvShowEntity.getName());
        firstAirDate.setText(tvShowEntity.getFirstAirDate());
        overview.setText(tvShowEntity.getOverview());
        voteAverage.setText(String.format("%s", (float)tvShowEntity.getVoteAverage()));
    }

    @NonNull
    private static DetailsTVShowViewModel obtainViewModel(AppCompatActivity appCompatActivity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(appCompatActivity.getApplication());
        return ViewModelProviders.of(appCompatActivity, viewModelFactory).get(DetailsTVShowViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        detailsTVShowViewModel.tvShowEntity.observe(this, tvShowEntity -> {
            if (tvShowEntity != null) {
                switch (tvShowEntity.status) {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        if (tvShowEntity.data != null) {
                            progressBar.setVisibility(View.GONE);
                            boolean state = tvShowEntity.data.isFavorite();
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
            detailsTVShowViewModel.setFavorite();
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
