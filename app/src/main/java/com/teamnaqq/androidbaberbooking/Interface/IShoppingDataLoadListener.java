package com.teamnaqq.androidbaberbooking.Interface;

import com.teamnaqq.androidbaberbooking.Model.ShoppingItem;

import java.util.List;

public interface IShoppingDataLoadListener {
    void onShoppingDataLoadSuccess(List<ShoppingItem> shoppingItemList);

    void onShoppingDataLoadFailed(String message);
}
