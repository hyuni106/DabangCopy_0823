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

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.dabangcopy.R;
import kr.co.tjeit.dabangcopy.SettingMyProfileActivity;
import kr.co.tjeit.dabangcopy.util.ContextUtil;

/**
 * Created by user on 2017-08-25.
 */

public class MyProfileFragment extends Fragment {

    private android.widget.TextView callTxt;
    private android.widget.LinearLayout settingProfileLayout;
    private TextView userIdTxt;
    private TextView userPhoneNumTxt;
    private de.hdodenhof.circleimageview.CircleImageView userProfileImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_profile, container, false);
        this.userProfileImg = (CircleImageView) v.findViewById(R.id.userProfileImg);
        this.userPhoneNumTxt = (TextView) v.findViewById(R.id.userPhoneNumTxt);
        this.userIdTxt = (TextView) v.findViewById(R.id.userIdTxt);
        this.settingProfileLayout = (LinearLayout) v.findViewById(R.id.settingProfileLayout);
        this.callTxt = (TextView) v.findViewById(R.id.callTxt);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
        setValuse();
    }

    @Override
    public void onResume() {
        super.onResume();
        userIdTxt.setText(ContextUtil.getLoginUser(getActivity()).getName());
        userPhoneNumTxt.setText(ContextUtil.getLoginUser(getActivity()).getPhoneNum());
    }

    private void setValuse() {
        if (ContextUtil.getLoginUserName(getActivity()) != null) {
            userIdTxt.setText(ContextUtil.getLoginUserName(getActivity()));
        } else {
            userIdTxt.setText(ContextUtil.getLoginUserId(getActivity()));
        }

        Glide.with(getActivity()).load(ContextUtil.getLoginUser(getActivity()).getProfileImageURL()).into(userProfileImg);
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
