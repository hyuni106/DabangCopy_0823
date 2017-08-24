package kr.co.tjeit.dabangcopy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.dabangcopy.R;
import kr.co.tjeit.dabangcopy.datas.Subway;

/**
 * Created by user on 2017-08-24.
 */

public class SubwayAdapter extends ArrayAdapter<Subway> {
    Context mContext;
    List<Subway> mList;
    LayoutInflater inf;

    public SubwayAdapter(Context context, List<Subway> list) {
        super(context, R.layout.subway_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = inf.inflate(R.layout.subway_list_item, null);
        }

        Subway data = mList.get(position);

        TextView stationNameTxt = (TextView) row.findViewById(R.id.stationNameTxt);
        TextView line1 = (TextView) row.findViewById(R.id.line1);
        TextView line2 = (TextView) row.findViewById(R.id.line2);
        TextView line3 = (TextView) row.findViewById(R.id.line3);
        TextView line4 = (TextView) row.findViewById(R.id.line4);

        List<TextView> lines = new ArrayList<>();
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);

        for (TextView tv : lines) {
            tv.setVisibility(View.GONE);
        }

        stationNameTxt.setText(data.getStationName());

        for (int i=0; i<data.getLines().size(); i++) {
            lines.get(i).setVisibility(View.VISIBLE);
            lines.get(i).setText(data.getLines().get(i));
        }

        return row;
    }
}
