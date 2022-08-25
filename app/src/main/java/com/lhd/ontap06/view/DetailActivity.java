package com.lhd.ontap06.view;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.lhd.ontap06.R;
import com.lhd.ontap06.adapter.CastAdapter;
import com.lhd.ontap06.adapter.CompanyAdapter;
import com.lhd.ontap06.adapter.MovieAdapter;
import com.lhd.ontap06.databinding.ActivityDetailBinding;
import com.lhd.ontap06.databinding.BottomSheetDialogBinding;
import com.lhd.ontap06.model.movieModel.Cast;
import com.lhd.ontap06.model.movieModel.DetailMovie;
import com.lhd.ontap06.model.movieModel.Movie;
import com.lhd.ontap06.model.response.CastResponse;
import com.lhd.ontap06.model.response.MovieResponse;
import com.lhd.ontap06.viewmodel.DetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements MovieAdapter.IOnClickItem {

    private static final String TAG = DetailActivity.class.getSimpleName();
    private ActivityDetailBinding binding;
    private DetailViewModel detailViewModel;
    private CompanyAdapter companyAdapter;
    private CastAdapter castAdapter;
    private MovieAdapter movieAdapter;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        getDataFromMainActivity();
        setBottomSheet();
        binding.setViewModel(detailViewModel);
        // catch download complete event from android download manager which broadcast message
        registerReceiver(onDownLoadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        // id
        detailViewModel.getIdMovie().observe(this, integer -> detailViewModel.getDetailMovie(integer));
        // data zip
        detailViewModel.getZip3Movie().observe(this, responseZip3 -> {
            disPlayDetail(responseZip3.getRes());
            disPlayCast(responseZip3.getRes1());
            disPlaySimilarMovie(responseZip3.getRes2());
        });
        // show menu
        detailViewModel.getIsShowMenu().observe(this, aBoolean -> {
            if (aBoolean) {
                bottomSheetDialog.show();
            } else {
                bottomSheetDialog.cancel();
            }
        });
        // toast mess
        detailViewModel.getPermissionRequest().observe(this, s -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permission, 1);
                } else {
                    detailViewModel.onPermissionResult(true);
                }
            } else {
                detailViewModel.onPermissionResult(true);
            }
        });
        // mess
        detailViewModel.getMess().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(DetailActivity.this, "" + s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                detailViewModel.onPermissionResult(true);
            } else {
                detailViewModel.onPermissionResult(false);
            }
        }
    }

    private void setBottomSheet() {
        BottomSheetDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.bottom_sheet_dialog, null, false);
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(binding.getRoot());
        binding.setViewModel(detailViewModel);
    }


    private void getDataFromMainActivity() {
        Intent i = getIntent();
        int id = i.getIntExtra("idMovie", 0);
        detailViewModel.setIdMovie(id);
    }

    private void disPlaySimilarMovie(MovieResponse res2) {
        movieAdapter = new MovieAdapter(res2.getResults(), this::clickItem, 0);
        binding.setAdapter3(movieAdapter);

    }

    private void disPlayCast(CastResponse res1) {
        List<Cast> ls = new ArrayList<>();
        // lấy 10 cast
        if (res1.getCast().size() > 10) {
            for (int i = 0; i < 9; i++) {
                ls.add(res1.getCast().get(i));
            }
        } else {
            ls.addAll(res1.getCast());
        }
        castAdapter = new CastAdapter(ls);
        binding.setAdapter2(castAdapter);
    }

    private void disPlayDetail(DetailMovie res) {
        binding.setItem(res);
        companyAdapter = new CompanyAdapter(res.getProductionCompanies());
        binding.setAdapter(companyAdapter);
    }


    public void backActivity(View view) {
        finish();
    }

    @Override
    public void clickItem(Movie movie) {
        Toast.makeText(this, "" + movie.getTitle(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(DetailActivity.this, DetailActivity.class);
        i.putExtra("idMovie", movie.getId());
        startActivity(i);
    }

    private BroadcastReceiver onDownLoadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            Log.e(TAG, "onReceive: " + id);
            if (detailViewModel.getIdDownLoad().get() == null) return;
            if (detailViewModel.getIdDownLoad().get() == id) {
                Toast.makeText(context, "Download complete!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Error download!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onDownLoadComplete);
    }
}