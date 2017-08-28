package kr.co.tjeit.dabangcopy;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kr.co.tjeit.dabangcopy.util.GlobalData;

public class MainActivity extends BaseActivity {

    private android.widget.LinearLayout homeFragment;
    private android.widget.LinearLayout myProfileFragment;
    private LinearLayout homeTabLayout;
    private LinearLayout favoriteTabLayout;
    private LinearLayout mapTabLayout;
    private LinearLayout myProfileabLayout;
    private LinearLayout seeMoreTabLayout;
    private LinearLayout favoriteListFragment;
    private LinearLayout mapFragment;
    private LinearLayout seeMoreFragment;

    public static MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        GlobalData.initGlobalData();

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "kr.co.tjeit.dabangcopy",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        final LinearLayout[] frags = {homeFragment, favoriteListFragment, mapFragment, myProfileFragment, seeMoreFragment};

        View.OnClickListener tabClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (LinearLayout linearLayout : frags) {
                    linearLayout.setVisibility(View.GONE);
                }

                int index = Integer.parseInt(v.getTag().toString());
                frags[index].setVisibility(View.VISIBLE);
            }
        };

        homeTabLayout.setOnClickListener(tabClick);
        favoriteTabLayout.setOnClickListener(tabClick);
        mapTabLayout.setOnClickListener(tabClick);
        myProfileabLayout.setOnClickListener(tabClick);
        seeMoreTabLayout.setOnClickListener(tabClick);
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.seeMoreTabLayout = (LinearLayout) findViewById(R.id.seeMoreTabLayout);
        this.myProfileabLayout = (LinearLayout) findViewById(R.id.myProfileabLayout);
        this.mapTabLayout = (LinearLayout) findViewById(R.id.mapTabLayout);
        this.favoriteTabLayout = (LinearLayout) findViewById(R.id.favoriteTabLayout);
        this.homeTabLayout = (LinearLayout) findViewById(R.id.homeTabLayout);
        this.seeMoreFragment = (LinearLayout) findViewById(R.id.seeMoreFragment);
        this.mapFragment = (LinearLayout) findViewById(R.id.mapFragment);
        this.favoriteListFragment = (LinearLayout) findViewById(R.id.favoriteListFragment);
        this.myProfileFragment = (LinearLayout) findViewById(R.id.myProfileFragment);
        this.homeFragment = (LinearLayout) findViewById(R.id.homeFragment);
    }
}
