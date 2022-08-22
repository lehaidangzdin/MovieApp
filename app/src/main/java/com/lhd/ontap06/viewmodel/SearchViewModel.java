package com.lhd.ontap06.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.lhd.ontap06.database.DAO;
import com.lhd.ontap06.database.HistoryDatabase;
import com.lhd.ontap06.model.db.History;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class SearchViewModel extends AndroidViewModel {
    private final String TAG = SearchViewModel.class.getSimpleName();

    private DAO dao;
    private MutableLiveData<List<History>> lsHistory;


    public SearchViewModel(@NonNull Application application) {
        super(application);
        dao = HistoryDatabase.getInstance(application).daoDB();
    }


    public MutableLiveData<List<History>> getLsHistory() {
        if (lsHistory == null) {
            lsHistory = new MutableLiveData<>();
            lsHistory.setValue(dao.getAll());
        }
        return lsHistory;
    }

    public void setLsHistory(MutableLiveData<List<History>> lsHistory) {
        this.lsHistory = lsHistory;
    }


    public void onClickInsert(String title) {
        try {
            dao.insert(new History(title));
            lsHistory.setValue(dao.getAll());
            Log.e("tag", "onClickInsert: OKKK");
        } catch (Exception e) {
            Log.e("TAG", "onClickInsert: " + e.getMessage());
        }
    }

    public void deleteHistory(int id) {
        dao.delete(id);
        lsHistory.setValue(dao.getAll());
    }
}
