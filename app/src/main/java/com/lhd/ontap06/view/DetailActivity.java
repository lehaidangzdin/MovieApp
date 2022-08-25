package com.lhd.ontap06.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
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
        detailViewModel.getIdMovie().observe(this, integer -> detailViewModel.getDetailMovie(integer));
        detailViewModel.getZipSimilarMovie().observe(this, responseZip3 -> {
            disPlayDetail(responseZip3.getRes());
            disPlayCast(responseZip3.getRes1());
            disPlaySimilarMovie(responseZip3.getRes2());
        });
        detailViewModel.getIsShowMenu().observe(this, aBoolean -> {
            if (aBoolean) {
                bottomSheetDialog.show();
            } else {
                bottomSheetDialog.cancel();
            }
        });

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

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                detailViewModel.onPermissionResult(true);
            }
        } else {
            Toast.makeText(DetailActivity.this, "Permission denied!", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();
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

}