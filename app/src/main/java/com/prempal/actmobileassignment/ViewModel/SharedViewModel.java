package com.prempal.actmobileassignment.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<String> selectedRegion = new MutableLiveData<String>();
    private MutableLiveData<String> selectedRegionFlag = new MutableLiveData<String>();
    private MutableLiveData<Integer> selectedRegionPos = new MutableLiveData<Integer>();

    public void setSelectedRegion(String selectedregion){
        Log.d("setsharedRegion:" , selectedregion);
        selectedRegion.setValue(selectedregion);
    }

    public MutableLiveData<String> getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegionFlag(String selectedregionflag){
        Log.d("setsharedRegionFlag:" , selectedregionflag);
        selectedRegionFlag.setValue(selectedregionflag);
    }

    public MutableLiveData<String> getSelectedRegionFlag() {
        return selectedRegionFlag;
    }

    public void setSelectedRegionPosition(int selectedregionPosition){
        Log.d("setsharedRegionPos:" , String.valueOf(selectedregionPosition));
        selectedRegionPos.setValue(selectedregionPosition);
    }

    public MutableLiveData<Integer> getSelectedRegionPos() {
        return selectedRegionPos;
    }
}
