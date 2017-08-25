package kr.co.tjeit.dabangcopy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.dabangcopy.adapter.SubwayAdapter;
import kr.co.tjeit.dabangcopy.adapter.UniversityAdapter;
import kr.co.tjeit.dabangcopy.datas.Subway;
import kr.co.tjeit.dabangcopy.datas.University;
import kr.co.tjeit.dabangcopy.util.GlobalData;
import kr.co.tjeit.dabangcopy.util.SoundSearcher;

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
    private android.widget.EditText searchEdt;
    private android.widget.ListView subwayListView;
    List<Subway> mDisplaySubwayArray = new ArrayList<>();
    List<University> mDisplayUnivArray = new ArrayList<>();
    SubwayAdapter subwayAdapter;
    private ListView univListView;
    UniversityAdapter universityAdapter;

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
        tabHost.setup();

        // 탭에 들어가는 버튼(tabspec)을 생성하는 작업
        // 구별자(tab1), 표시(지역) 세팅
        TabHost.TabSpec ts1 = tabHost.newTabSpec("tab1").setIndicator("지역");
        ts1.setContent(R.id.tab1);
        tabHost.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost.newTabSpec("tab2").setIndicator("지하철");
        ts2.setContent(R.id.tab2);
        tabHost.addTab(ts2);

        TabHost.TabSpec ts3 = tabHost.newTabSpec("tab3").setIndicator("대학교");
        ts3.setContent(R.id.tab3);
        tabHost.addTab(ts3);

        TabHost.TabSpec ts4 = tabHost.newTabSpec("tab4").setIndicator("단지");
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

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                searchEdt.setText("");
                switch (tabId) {
                    case "tab1":
                        searchEdt.setHint("동, 면, 읍 명을 검색하세요");
                        break;
                    case "tab2":
                        searchEdt.setHint("지하철 명을 검색하세요");
                        break;
                    case "tab3":
                        searchEdt.setHint("대학교 명을 검색하세요");
                        break;
                    case "tab4":
                        searchEdt.setHint("단지 명을 검색하세요");
                        break;
                }
            }
        });

        subwayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, RoomListActivity.class);
                intent.putExtra("subway", mDisplaySubwayArray.get(position));
                startActivity(intent);
            }
        });

        univListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, RoomListActivity.class);
                intent.putExtra("univ", mDisplayUnivArray.get(position));
                startActivity(intent);
            }
        });

        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (tabHost.getCurrentTab() == 1) {
                    filterSubwayList(s.toString());
                } else if (tabHost.getCurrentTab() == 2) {
                    filterUnivList(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void filterUnivList(String inputStr) {
        mDisplayUnivArray.clear();
        if (inputStr.length() > 0) {
            for (University univ : GlobalData.universities) {
                if (univ.getName().startsWith(inputStr)) {
                    mDisplayUnivArray.add(univ);
                }
            }

            if (mDisplayUnivArray.size() == 0) {
                for (University univ : GlobalData.universities) {
                    if (SoundSearcher.matchString(univ.getName(), inputStr)) {
                        mDisplayUnivArray.add(univ);
                    }
                }
            }
        } else {
            mDisplayUnivArray.addAll(GlobalData.universities);
        }
        if (universityAdapter != null) {
            universityAdapter.notifyDataSetChanged();
        }
    }

    private void filterSubwayList(String inputStr) {
        mDisplaySubwayArray.clear();
        if (inputStr.length() > 0) {
            for (Subway s : GlobalData.stations) {
                if (s.getStationName().startsWith(inputStr)) {
                    mDisplaySubwayArray.add(s);
                }
            }

            if (mDisplaySubwayArray.size() == 0) {
                for (Subway s : GlobalData.stations) {
                    if (SoundSearcher.matchString(s.getStationName(), inputStr)) {
                        mDisplaySubwayArray.add(s);
                    }
                }
            }
        } else {
            mDisplaySubwayArray.addAll(GlobalData.stations);
        }
        if (subwayAdapter != null) {
            subwayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setValues() {
        tabHost.setCurrentTab(tabNum);

//        mDisplaySubwayArray.addAll(GlobalData.stations);
        subwayAdapter = new SubwayAdapter(mContext, mDisplaySubwayArray);
        subwayListView.setAdapter(subwayAdapter);

//        mDisplayUnivArray.addAll(GlobalData.universities);
        universityAdapter = new UniversityAdapter(mContext, mDisplayUnivArray);
        univListView.setAdapter(universityAdapter);
    }

    @Override
    public void bindViews() {
        this.tabHost = (TabHost) findViewById(R.id.tabHost);
        this.tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        this.tab4 = (LinearLayout) findViewById(R.id.tab4);
        this.tab3 = (LinearLayout) findViewById(R.id.tab3);
        this.univListView = (ListView) findViewById(R.id.univListView);
        this.tab2 = (LinearLayout) findViewById(R.id.tab2);
        this.subwayListView = (ListView) findViewById(R.id.subwayListView);
        this.tab1 = (LinearLayout) findViewById(R.id.tab1);
        this.tabs = (TabWidget) findViewById(android.R.id.tabs);
        this.searchEdt = (EditText) findViewById(R.id.searchEdt);
        this.closeBtn = (TextView) findViewById(R.id.closeBtn);
    }
}
