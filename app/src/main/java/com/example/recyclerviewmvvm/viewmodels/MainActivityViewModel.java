package com.example.recyclerviewmvvm.viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recyclerviewmvvm.models.NicePlace;
import com.example.recyclerviewmvvm.repositories.NicePlaceRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<NicePlace>> mNicePlace;
    private NicePlaceRepository nicePlaceRepository;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();


    public void init(){
        if (mNicePlace != null){
            return;
        }
        nicePlaceRepository = NicePlaceRepository.getInstance();
        mNicePlace = nicePlaceRepository.getNicePlaces();
    }

    public void addNewValue(final NicePlace nicePlace){

        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<NicePlace> currentPlaces = mNicePlace.getValue();
                currentPlaces.add(nicePlace);
                mNicePlace.postValue(currentPlaces);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<List<NicePlace>> getNicePlace(){
        return mNicePlace;
    }
    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
