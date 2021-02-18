package com.view.myapplication4.helper;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.material.navigation.NavigationView;
import com.view.myapplication4.MainActivity;
import com.view.myapplication4.R;
import com.view.myapplication4.SubActivity;

public class NavigationViewHelper {
    private static final String TAG = "NavigationViewHelper";

    public static void enable(Context context, NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if(id == R.id.item1) {
                Log.d(TAG, "onCreate: 메뉴 1 클릭됨");
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);

            } else if (id == R.id.item2) {
                Log.d(TAG, "onCreate: 메뉴 2 클릭됨");
                Intent intent = new Intent(context, SubActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            } else if (id == R.id.item3) {
                Log.d(TAG, "onCreate: 메뉴 3 클릭됨");
            }

            return true;
        });
    }
}
