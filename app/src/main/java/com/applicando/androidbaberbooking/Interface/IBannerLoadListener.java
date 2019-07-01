package com.applicando.androidbaberbooking.Interface;

import com.applicando.androidbaberbooking.Model.Banner;

import java.util.List;

public interface IBannerLoadListener {
    void onBannerLoadSucess(List<Banner> banners);
    void onBannerLoadFailed(String message);
}
