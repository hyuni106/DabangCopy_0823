package kr.co.tjeit.dabangcopy.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import kr.co.tjeit.dabangcopy.R;
import kr.co.tjeit.dabangcopy.SettingMyProfileActivity;

/**
 * Created by user on 2017-08-25.
 */

public class MyProfileFragment extends Fragment {

    private android.widget.TextView callTxt;
    private android.widget.LinearLayout settingProfileLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_profile, container, false);
        this.settingProfileLayout = (LinearLayout) v.findViewById(R.id.settingProfileLayout);
        this.callTxt = (TextView) v.findViewById(R.id.callTxt);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
    }

    private void setupEvents() {
        callTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:07042113951"));
                startActivity(intent);
            }
        });

        settingProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingMyProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
