package com.lhd.ontap06.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lhd.ontap06.R;
import com.lhd.ontap06.adapter.CastAdapter;
import com.lhd.ontap06.adapter.CompanyAdapter;
import com.lhd.ontap06.databinding.ActivityDetailBinding;
import com.lhd.ontap06.model.modelzip.ModelZip2;
import com.lhd.ontap06.model.movieModel.Cast;
import com.lhd.ontap06.model.movieModel.DetailMovie;
import com.lhd.ontap06.model.response.CastResponse;
import com.lhd.ontap06.viewmodel.DetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    private ActivityDetailBinding binding;
    private DetailViewModel detailViewModel;
    private CompanyAdapter companyAdapter;
    private CastAdapter castAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        getDataFromMainActivity();
        detailViewModel.getZip2MutableLiveData().observe(this, new Observer<ModelZip2<DetailMovie, CastResponse>>() {
            @Override
            public void onChanged(ModelZip2<DetailMovie, CastResponse> detailMovieCastResponseModelZip2) {
                disPlayDetail(detailMovieCastResponseModelZip2.getRes());
                disPlayCast(detailMovieCastResponseModelZip2.getRes1());
                Log.e(TAG, "onChanged: " + detailMovieCastResponseModelZip2.getRes1().getCast().get(0).getName());
            }
        });
    }

    private void disPlayCast(CastResponse res1) {
        List<Cast> ls = new ArrayList<>();
        if (ls.size() > 10) {
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

    private void getDataFromMainActivity() {
        Intent i = getIntent();
        int id = i.getIntExtra("idMovie", 0);
        detailViewModel.getDetailMovie(id);
        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();
    }


    public void backActivity(View view) {
        finish();
    }
}