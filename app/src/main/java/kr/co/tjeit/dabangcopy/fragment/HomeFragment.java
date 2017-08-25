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
import kr.co.tjeit.dabangcopy.RequestMoveActivity;
import kr.co.tjeit.dabangcopy.SearchActivity;

/**
 * Created by user on 2017-08-23.
 */

public class HomeFragment extends Fragment {
    private android.widget.LinearLayout locationSearchLayout;
    private android.widget.LinearLayout subwaySearchLayout;
    private android.widget.LinearLayout univSearchLayout;
    private android.widget.LinearLayout danjiSearchLayout;
    private LinearLayout moveLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        this.danjiSearchLayout = (LinearLayout) v.findViewById(R.id.danjiSearchLayout);
        this.univSearchLayout = (LinearLayout) v.findViewById(R.id.univSearchLayout);
        this.subwaySearchLayout = (LinearLayout) v.findViewById(R.id.subwaySearchLayout);
        this.locationSearchLayout = (LinearLayout) v.findViewById(R.id.locationSearchLayout);
        this.moveLayout = (LinearLayout) v.findViewById(R.id.moveLayout);

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
        moveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RequestMoveActivity.class);
                startActivity(intent);
            }
        });

        // 검색용 버튼들은 하는 일이 모두 비슷함
        // onClickListener 변수 생성
        View.OnClickListener intentTab = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tagInt = Integer.parseInt(v.getTag().toString());
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("tab", tagInt);
                startActivity(intent);
            }
        };

        // 4개의 버튼에 공통된 업무 부여
        locationSearchLayout.setOnClickListener(intentTab);
        subwaySearchLayout.setOnClickListener(intentTab);
        univSearchLayout.setOnClickListener(intentTab);
        danjiSearchLayout.setOnClickListener(intentTab);
    }
}
