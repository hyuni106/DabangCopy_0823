package kr.co.tjeit.dabangcopy;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class SearchActivity extends BaseActivity {

    private android.widget.TextView closeBtn;
    private android.widget.TabWidget tabs;
    private android.widget.LinearLayout tab1;
    private android.widget.LinearLayout tab2;
    private android.widget.LinearLayout tab3;
    private android.widget.LinearLayout tab4;
    private android.widget.FrameLayout tabcontent;

    int tabNum = 0;
    private android.widget.TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        tabNum = getIntent().getIntExtra("tab", 0);
        bindViews();
        makeTabHost();
        setupEvents();
        setValues();
    }

    private void makeTabHost() {
        // 탭호스트를 사용하기 위해서는 반드시 setup을 먼저 진행해야함
        tabHost.setup() ;

        // 탭에 들어가는 버튼(tabspec)을 생성하는 작업
        // 구별자(tab1), 표시(지역) 세팅
        TabHost.TabSpec ts1 = tabHost.newTabSpec("tab1").setIndicator("지역");
        ts1.setContent(R.id.tab1);
        tabHost.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost.newTabSpec("tab2").setIndicator("지하철");
        ts2.setContent(R.id.tab2) ;
        tabHost.addTab(ts2) ;

        TabHost.TabSpec ts3 = tabHost.newTabSpec("tab3").setIndicator("대학교") ;
        ts3.setContent(R.id.tab3) ;
        tabHost.addTab(ts3) ;

        TabHost.TabSpec ts4 = tabHost.newTabSpec("tab4").setIndicator("단지") ;
        ts4.setContent(R.id.tab4);
        tabHost.addTab(ts4);
    }

    @Override
    public void setupEvents() {
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setValues() {
        tabHost.setCurrentTab(tabNum);
    }

    @Override
    public void bindViews() {
        this.tabHost = (TabHost) findViewById(R.id.tabHost);
        this.tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        this.tab4 = (LinearLayout) findViewById(R.id.tab4);
        this.tab3 = (LinearLayout) findViewById(R.id.tab3);
        this.tab2 = (LinearLayout) findViewById(R.id.tab2);
        this.tab1 = (LinearLayout) findViewById(R.id.tab1);
        this.tabs = (TabWidget) findViewById(android.R.id.tabs);
        this.closeBtn = (TextView) findViewById(R.id.closeBtn);
    }
}
