package home.denis.dataviewer.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import home.denis.dataviewer.model.utils.EntityTypeUtils;
import home.denis.dataviewer.ui.fragment.ListFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new ListFragment(EntityTypeUtils.UNIVERSITY);
        }
        else if (position == 1)
        {
            fragment = new ListFragment(EntityTypeUtils.EVENT);
        }
        else if (position == 2)
        {
            fragment = new ListFragment(EntityTypeUtils.NEWS);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Университеты";
        }
        else if (position == 1)
        {
            title = "События";
        }
        else if (position == 2)
        {
            title = "Новости";
        }
        return title;
    }
}
