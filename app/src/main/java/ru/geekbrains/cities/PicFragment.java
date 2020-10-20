package ru.geekbrains.cities;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class PicFragment extends Fragment {
public static PicFragment create(int index) {
    PicFragment f = new PicFragment();

    Bundle args = new Bundle();
    args.putInt("index", index);
    f.setArguments(args);
    return f;
}

    public int getIndex() {
        int index = getArguments().getInt("index", 0);
        return index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ImageView pics = new ImageView(getContext());


        TypedArray imgs = getResources().obtainTypedArray(R.array.pics_imgs);

        pics.setImageResource(imgs.getResourceId(getIndex(), -1));
        return pics;
    }
}