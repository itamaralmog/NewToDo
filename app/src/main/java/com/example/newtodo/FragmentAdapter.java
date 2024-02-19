package com.example.newtodo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    // Array of fragment classes
    private static final Class<? extends Fragment>[] FRAGMENT_CLASSES = new Class[]{
            FragmentAll.class,
            FragmentActive.class,
            FragmentCompleted.class
    };

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Ensuring the position is within bounds
        position = Math.max(0, Math.min(position, FRAGMENT_CLASSES.length - 1));

        try {
            // Creating an instance of the specified fragment class
            return FRAGMENT_CLASSES[position].newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        // Default to FragmentAll in case of an exception
        return new FragmentAll();
    }

    @Override
    public int getItemCount() {
        return FRAGMENT_CLASSES.length;
    }
}
