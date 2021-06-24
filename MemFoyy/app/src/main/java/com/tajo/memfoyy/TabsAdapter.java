package com.tajo.memfoyy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsAdapter extends FragmentPagerAdapter {
    public TabsAdapter(@NonNull @org.jetbrains.annotations.NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                ProfilFragment profilFragment = new ProfilFragment();
                return profilFragment;
            case 1:
                KisilerFragment kisilerFragment = new KisilerFragment();
                return kisilerFragment;
            case 2:
                AnilarFragment anilarFragment = new AnilarFragment();
                return anilarFragment;
            case 3:
                NotlarFragment notlarFragment = new NotlarFragment();
                return notlarFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return  "PROFİL";
            case 1:
                return  "KİŞİLER";
            case 2:
                return  "ANILAR";
            case 3:
                return  "NOTLAR";
            default:
                return null;
        }
    }
}
