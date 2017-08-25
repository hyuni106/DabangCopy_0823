package kr.co.tjeit.dabangcopy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.dabangcopy.adapter.RoomAdapter;
import kr.co.tjeit.dabangcopy.datas.Room;
import kr.co.tjeit.dabangcopy.datas.Subway;
import kr.co.tjeit.dabangcopy.datas.University;
import kr.co.tjeit.dabangcopy.util.GlobalData;

public class RoomListActivity extends BaseActivity {

    private final int REQ_FOR_FILTER = 1;

    boolean isMonthPaySelected = true;
    boolean isCharterSelected = true;
    boolean isOneRoomSelected = true;
    boolean isTwoRoomSelected = true;
    boolean isThreeRoomSelected = true;
    int minDeposit = 0;
    int maxDeposit = 50000;

    private android.widget.ListView roomListView;
    //    필터되서 출력될 수 있도록 지원해주는 출력용 리스트
    List<Room> mDisplayRoomArray = new ArrayList<>();
    RoomAdapter mAdapter;
    private android.widget.ImageView filterBtn;
    Subway mSubway;
    University mUniversity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
//        임시로, 이곳에서 GlobalData에 더미데이터를 채워넣음.
//        원하는리스트.addAll(원본리스트)?
//        원본 리스트에 있는 모든 내용물을 복사해서 원하는 리스트에 추가해주는 메쏘드
//        차후에 필터를 동작시키기 위해 mDisplayRoomArray를 활용하는 방안으로 코딩.
//        mDisplayRoomArray.addAll(GlobalData.allRooms);
        mSubway = (Subway) getIntent().getSerializableExtra("subway");
        mUniversity = (University) getIntent().getSerializableExtra("univ");
        bindViews();
        setupEvents();
        setValues();
        roomFilter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_FOR_FILTER) {
            if (resultCode == RESULT_OK) {
                // getIntent() 와 별개 > getIntent() 사용하면 안됨
                isMonthPaySelected = data.getBooleanExtra("monthPay", true);
                isCharterSelected = data.getBooleanExtra("charter", true);
                isOneRoomSelected = data.getBooleanExtra("oneRoom", true);
                isTwoRoomSelected = data.getBooleanExtra("twoRoom", true);
                isThreeRoomSelected = data.getBooleanExtra("threeRoom", true);
                minDeposit = data.getIntExtra("min", 0);
                maxDeposit = data.getIntExtra("max", 0);
                roomFilter();
            }
        }
    }

    private void roomFilter() {
        mDisplayRoomArray.clear();

        boolean payTypeOk;
        boolean roomTypeOk;
        boolean depositOk;
        boolean subwayOk;
        boolean universityOk;

        for (Room room : GlobalData.allRooms) {
            payTypeOk = false;
            roomTypeOk = false;
            depositOk = false;
            subwayOk = false;
            universityOk = false;

            if (isMonthPaySelected) {
                if (room.getRentPay() != 0) {
                    payTypeOk = true;
                }
            }

            if (isCharterSelected) {
                if (room.getRentPay() == 0) {
                    payTypeOk = true;
                }
            }

            if (isOneRoomSelected) {
                if (room.getRoomCount() == 1) {
                    roomTypeOk = true;
                }
            }

            if (isTwoRoomSelected) {
                if (room.getRoomCount() == 2) {
                    roomTypeOk = true;
                }
            }

            if (isThreeRoomSelected) {
                if (room.getRoomCount() == 3) {
                    roomTypeOk = true;
                }
            }

            if (minDeposit <= room.getDeposit() && room.getDeposit() <= maxDeposit) {
                depositOk = true;
            }

            if (mSubway != null) {
                if (room.getNearStations().contains(mSubway)) {
                    subwayOk = true;
                }
            } else {
                subwayOk = true;
            }

            if (mUniversity != null) {
                if (room.getNearUniversities().contains(mUniversity)) {
                    universityOk = true;
                }
            } else  {
                universityOk = true;
            }

            if (payTypeOk && roomTypeOk && depositOk && subwayOk && universityOk) {
                mDisplayRoomArray.add(room);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setupEvents() {

        roomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ViewRoomDetailActivity.class);
                intent.putExtra("방데이터", mDisplayRoomArray.get(position));
                startActivity(intent);
            }
        });

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RoomFilterActivity.class);
                startActivityForResult(intent, REQ_FOR_FILTER);
            }
        });
    }

    @Override
    public void setValues() {
        mDisplayRoomArray.clear();

        mAdapter = new RoomAdapter(mContext, mDisplayRoomArray);
        roomListView.setAdapter(mAdapter);
    }

    @Override
    public void bindViews() {
        this.roomListView = (ListView) findViewById(R.id.roomListView);
        this.filterBtn = (ImageView) findViewById(R.id.filterBtn);
    }
}
