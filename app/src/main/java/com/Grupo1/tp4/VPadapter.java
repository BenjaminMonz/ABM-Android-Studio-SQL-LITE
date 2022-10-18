package com.Grupo1.tp4;

import android.view.View;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

import database.DBQuery;

public class VPadapter extends FragmentStateAdapter {

    public VPadapter(@NonNull FragmentActivity FragmentActivity) {
        super(FragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Alta();
            case 1:
                return new Modificacion();
            default:
                return new Listado();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
