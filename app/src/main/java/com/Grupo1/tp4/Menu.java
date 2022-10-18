package com.Grupo1.tp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Menu extends AppCompatActivity {

    public TabLayout Tab;
    public ViewPager2 ViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Tab = (TabLayout) findViewById(R.id.Tab);
        ViewPager = (ViewPager2) findViewById(R.id.ViewPager);

        VPadapter vPadapter = new VPadapter(this);
        ViewPager.setAdapter(vPadapter);

        new TabLayoutMediator(Tab,ViewPager,
                new TabLayoutMediator.TabConfigurationStrategy(){
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position){

                        switch (position){
                            case 0:
                                tab.setText("ALTA");
                                break;
                            case 1:
                                tab.setText("MODIFICACION");
                                break;
                            default:
                                tab.setText("LISTADO");
                                break;
                        }
        }
                }).attach();

    }
}