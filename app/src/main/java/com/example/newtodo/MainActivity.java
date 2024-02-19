package com.example.newtodo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;

    private static final int RC_NOTIFICATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Request notification permission if running on Android 12 (API level 31) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            requestNotificationPermission();
        }

        // Initialize ViewPager2 and attach FragmentAdapter
        viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(new FragmentAdapter(this));

        // Initialize TabLayout and configure tab labels
        tabLayout = findViewById(R.id.tabLayout);
        String[] tabLabels = getResources().getStringArray(R.array.tab_labels);
        configureTabLayout(tabLabels);
    }

    // Request notification permission for Android 12 (API level 31) and higher
    private void requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, RC_NOTIFICATION);
        }
    }

    // Configure TabLayout with tab labels
    private void configureTabLayout(String[] tabLabels) {
        TabLayoutMediator.TabConfigurationStrategy tabConfigurationStrategy = (tab, position) -> {
            tab.setText(tabLabels[position]);
        };
        new TabLayoutMediator(tabLayout, viewPager2, false, false, tabConfigurationStrategy).attach();
    }

    // Handle permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == RC_NOTIFICATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                Log.d("MainActivity", "Notification permission granted");
            } else {
                // Permission denied
                Log.d("MainActivity", "Notification permission denied");
            }
        }
    }
}
