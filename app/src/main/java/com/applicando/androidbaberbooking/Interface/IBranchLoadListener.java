package com.applicando.androidbaberbooking.Interface;

import com.applicando.androidbaberbooking.Model.Salon;

import java.util.List;

public interface IBranchLoadListener {
    void onBranchLoadSuccess(List<Salon> salonList);
    void onBranchLoadFailed(String message);

}
