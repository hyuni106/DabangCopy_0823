package kr.co.tjeit.dabangcopy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import kr.co.tjeit.dabangcopy.R;
import kr.co.tjeit.dabangcopy.SearchActivity;

/**
 * Created by user on 2017-08-23.
 */

public class HomeFragment extends Fragment {
    private android.widget.LinearLayout locationSearchLayout;
    private android.widget.LinearLayout subwaySearchLayout;
    private android.widget.LinearLayout univSearchLayout;
    private android.widget.LinearLayout danjiSearchLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        this.danjiSearchLayout = (LinearLayout) v.findViewById(R.id.danjiSearchLayout);
        this.univSearchLayout = (LinearLayout) v.findViewById(R.id.univSearchLayout);
        this.subwaySearchLayout = (LinearLayout) v.findViewById(R.id.subwaySearchLayout);
        this.locationSearchLayout = (LinearLayout) v.findViewById(R.id.locationSearchLayout);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
        setValues();
    }

    private void setValues() {

    }

    private void setupEvents() {
        View.OnClickListener intentTab = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tagInt = Integer.parseInt(v.getTag().toString());
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("tab", tagInt);
                startActivity(intent);
            }
        };

        locationSearchLayout.setOnClickListener(intentTab);
        subwaySearchLayout.setOnClickListener(intentTab);
        univSearchLayout.setOnClickListener(intentTab);
        danjiSearchLayout.setOnClickListener(intentTab);
    }
}
