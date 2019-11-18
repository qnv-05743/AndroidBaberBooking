package com.teamnaqq.androidbaberbooking.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.teamnaqq.androidbaberbooking.Adapter.MyShoppingItemAdapter;
import com.teamnaqq.androidbaberbooking.Common.SpacesItemDecoration;
import com.teamnaqq.androidbaberbooking.Interface.IShoppingDataLoadListener;
import com.teamnaqq.androidbaberbooking.Model.ShoppingItem;
import com.teamnaqq.androidbaberbooking.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment implements IShoppingDataLoadListener {
    CollectionReference shoppingItemRef;

    Unbinder unbinder;

    IShoppingDataLoadListener iShoppingDataLoadListener;
    @BindView(R.id.chip_group)
    ChipGroup chipGroup;
    @BindView(R.id.chip_wax)
    Chip chip_wax;
    @BindView(R.id.chip_spray)
    Chip chip_spray;
    @BindView(R.id.recycler_items)
    RecyclerView recycler_items;

    @OnClick(R.id.chip_wax)
    void waxChipClick() {
        setSelectedChip(chip_wax);
        loadShoppingItem("Wax");
    }


    @OnClick(R.id.chip_spray)
    void sprayChipClick() {
        setSelectedChip(chip_spray);
        loadShoppingItem("Spray");
    }

    private void loadShoppingItem(String itemMenu) {
        shoppingItemRef = FirebaseFirestore.getInstance().collection("Shopping")
                .document(itemMenu)
                .collection("Items");

        shoppingItemRef.get().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iShoppingDataLoadListener.onShoppingDataLoadFailed(e.getMessage());
            }
        }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<ShoppingItem> shoppingItems = new ArrayList<>();
                    for (DocumentSnapshot snapshot : task.getResult()) {
                        ShoppingItem shoppingItem = snapshot.toObject(ShoppingItem.class);
                        shoppingItem.setId(snapshot.getId());
                        shoppingItems.add(shoppingItem);
                    }
                    iShoppingDataLoadListener.onShoppingDataLoadSuccess(shoppingItems);
                }
            }
        });
    }

    private void setSelectedChip(Chip chip_wax) {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chipItem = (Chip) chipGroup.getChildAt(i);
            if (chipItem.getId() != chip_wax.getId()) {
                chipItem.setChipBackgroundColorResource(android.R.color.darker_gray);
                chipItem.setTextColor(getResources().getColor(android.R.color.white));
            } else {
                chipItem.setChipBackgroundColorResource(android.R.color.holo_orange_dark);
                chipItem.setTextColor(getResources().getColor(android.R.color.black));
            }
        }
    }

    public ShoppingFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shoppinfragment, container, false);
        Object target;
        unbinder = ButterKnife.bind(this, view);
        iShoppingDataLoadListener = this;
        loadShoppingItem("Wax");
       // loadShoppingItem("Spray");
        initView();
        return view;
    }

    private void initView() {
        recycler_items.setHasFixedSize(true);
        recycler_items.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recycler_items.addItemDecoration(new SpacesItemDecoration(8));
    }

    @Override
    public void onShoppingDataLoadSuccess(List<ShoppingItem> shoppingItemList) {
        MyShoppingItemAdapter adapter = new MyShoppingItemAdapter(getContext(), shoppingItemList);
        recycler_items.setAdapter(adapter);

    }

    @Override
    public void onShoppingDataLoadFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
