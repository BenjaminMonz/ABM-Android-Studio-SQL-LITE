package com.Grupo1.tp4;

import android.os.Build;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import database.DBQuery;

public class Listado extends Fragment {
    private GridView gridView;

    public Listado(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado, container, false);
        // Inflate the layout for this fragment
        gridView = (GridView) view.findViewById(R.id.grid);
        gridView.setVerticalSpacing(70);
        gridView.setPadding(5,30,5,30);
        DBQuery dbQuery = new DBQuery(view.getContext(), gridView);
        dbQuery.execute();
        return view;
    }



}