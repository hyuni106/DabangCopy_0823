package kr.co.tjeit.dabangcopy;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.dabangcopy.adapter.LargePhotoViewPagerAdapter;


public class DetailViewImageActivity extends BaseActivity {

    String mURL = "";
    private android.support.v4.view.ViewPager photosViewPager;
    LargePhotoViewPagerAdapter mAdapter;
    List<String> urls = new ArrayList<>();
    int mPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view_image);
        urls = getIntent().getStringArrayListExtra("urls");
        mURL = getIntent().getStringExtra("url");
        mPosition = getIntent().getIntExtra("position", 0);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
        mAdapter = new LargePhotoViewPagerAdapter(mContext, urls);
        photosViewPager.setAdapter(mAdapter);
        photosViewPager.setCurrentItem(mPosition, false);
        // ViewPager의 현재 페이지를 설정 : serCurrentItem(원하는페이지, 애니메이션여부);
    }

    @Override
    public void bindViews() {
        this.photosViewPager = (ViewPager) findViewById(R.id.photosViewPager);
    }
}
