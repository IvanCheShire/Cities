package ru.geekbrains.cities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CitiesFragment extends Fragment {
    boolean isExistPic;
    int currentPosition = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        isExistPic = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;


        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("CurrentCity", 0);
        }

        if (isExistPic) {
            showPic();
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("CurrentCity", currentPosition);
        super.onSaveInstanceState(outState);
    }


    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout)view;
        String[] cities = getResources().getStringArray(R.array.cities);

        for(int i=0; i < cities.length; i++){
            String city = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentPosition = fi;
                    showPic();
                }
            });
        }
    }


    private void showPic() {
        if (isExistPic) {

            PicFragment detail = (PicFragment)
                    getFragmentManager().findFragmentById(R.id.pics);

            if (detail == null || detail.getIndex() != currentPosition) {

                detail = PicFragment.create(currentPosition);


                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.pics, detail);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {

            Intent intent = new Intent();
            intent.setClass(getActivity(), PicActivity.class);

            intent.putExtra("index", currentPosition);
            startActivity(intent);
        }
    }

}