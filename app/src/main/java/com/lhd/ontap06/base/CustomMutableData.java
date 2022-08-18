package com.lhd.ontap06.base;

import androidx.databinding.BaseObservable;
import androidx.databinding.Observable;
import androidx.lifecycle.MutableLiveData;

public class CustomMutableData<T extends BaseObservable> extends MutableLiveData<T> {
    @Override
    public void setValue(T value) {
        super.setValue(value);
        value.addOnPropertyChangedCallback(callback);
    }

    Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            //Trigger LiveData observer on change of any property in object
            setValue(getValue());

        }
    };
}
