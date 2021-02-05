package com.openclassrooms.entrevoisins.ui.activities.neighbour_list;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.ui.activities.neighbour_list.fragments.FavoritesFragment;
import com.openclassrooms.entrevoisins.ui.activities.neighbour_list.fragments.NeighbourFragment;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        System.out.println("Fragment Item position" + position);
        if (position == 0) {
            return NeighbourFragment.newInstance();
        } else {
            return FavoritesFragment.newInstance();
        }
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}