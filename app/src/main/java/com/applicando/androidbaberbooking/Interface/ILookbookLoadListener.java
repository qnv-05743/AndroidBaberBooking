package com.applicando.androidbaberbooking.Interface;

import com.applicando.androidbaberbooking.Model.Banner;

import java.util.List;

public interface ILookbookLoadListener {
    void onLookbookLoadSucess(List<Banner> banners);
    void onLookbookLoadFailed(String message);
}
