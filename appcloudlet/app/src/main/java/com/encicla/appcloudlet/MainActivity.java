package com.encicla.appcloudlet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Home",R.drawable.encicla_digital_web_tramite_100_digital_jpg));
        menuItems.add(new MenuItem("QR",R.drawable.descarga));
        menuItems.add(new MenuItem("+ Tiempo",R.drawable.encicla_reactivacion_boletin_jpg));
        menuItems.add(new MenuItem("Puntos",R.drawable.encicla_web_jpg));
        sNavigationDrawer.setMenuItemList(menuItems);

        fragmentClass =  home.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }
        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position "+position);

                switch (position){
                    case 0:{
                        fragmentClass = home.class;
                        break;
                    }
                    case 1:{
                        fragmentClass = qr.class;
                        break;
                    }
                    case 2:{
                        fragmentClass = time_extra.class;
                        break;
                    }
                    case 3:{
                        fragmentClass = score.class;
                        break;
                    }

                }

                //Listener for drawer events such as opening and closing.
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening(){

                    }

                    @Override
                    public void onDrawerClosing(){
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State "+newState);
                    }
                });
            }
        });



    }
}
