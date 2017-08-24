package kr.co.tjeit.dabangcopy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import kr.co.tjeit.dabangcopy.R;
import kr.co.tjeit.dabangcopy.RoomListActivity;
import kr.co.tjeit.dabangcopy.adapter.SubwayAdapter;
import kr.co.tjeit.dabangcopy.util.GlobalData;

/**
 * Created by user on 2017-08-24.
 */

public class SubwayFragment extends Fragment {
    private android.widget.ListView subwayListView;
    SubwayAdapter subwayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_subway, container, false);
        this.subwayListView = (ListView) v.findViewById(R.id.subwayListView);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
        setVaules();
    }

    private void setVaules() {
        subwayAdapter = new SubwayAdapter(getActivity(), GlobalData.stations);
        subwayListView.setAdapter(subwayAdapter);
    }

    private void setupEvents() {
        subwayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), RoomListActivity.class);

            }
        });
    }

}
