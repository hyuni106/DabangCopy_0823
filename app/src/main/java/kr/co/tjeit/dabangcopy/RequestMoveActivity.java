package kr.co.tjeit.dabangcopy;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class RequestMoveActivity extends BaseActivity {

    private android.widget.LinearLayout move1Layout;
    private android.widget.LinearLayout move2Layout;
    private android.widget.TextView textView2;
    private android.widget.LinearLayout move3Layout;
    private android.widget.LinearLayout move4Layout;
    private android.widget.TextView textView3;
    private android.widget.TextView textView;
    private TextView calendarTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_move);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        calendarTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(mContext,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

//                                날짜 => 글자 : SimpleDateFormat

//                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String str = String.format(Locale.KOREA, "%4d-%02d-%02d", year, month+1, dayOfMonth);
                                calendarTxt.setText(str);
                            }
                        },
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        View.OnClickListener moveClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = v.getTag().toString();
                setBackGray();
                if (tag.equals("move1")) {
                    move1Layout.setBackgroundColor(Color.parseColor("#176DE1"));
                } else if (tag.equals("move2")) {
                    move2Layout.setBackgroundColor(Color.parseColor("#176DE1"));
                } else if (tag.equals("move3")) {
                    move3Layout.setBackgroundColor(Color.parseColor("#176DE1"));
                } else if (tag.equals("move4")) {
                    move4Layout.setBackgroundColor(Color.parseColor("#176DE1"));
                }
            }
        };

        move1Layout.setOnClickListener(moveClick);
        move2Layout.setOnClickListener(moveClick);
        move3Layout.setOnClickListener(moveClick);
        move4Layout.setOnClickListener(moveClick);

    }

    private void setBackGray() {
        move1Layout.setBackgroundColor(Color.parseColor("#C2C2C2"));
        move2Layout.setBackgroundColor(Color.parseColor("#C2C2C2"));
        move3Layout.setBackgroundColor(Color.parseColor("#C2C2C2"));
        move4Layout.setBackgroundColor(Color.parseColor("#C2C2C2"));
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.textView = (TextView) findViewById(R.id.textView);
        this.calendarTxt = (TextView) findViewById(R.id.calendarTxt);
        this.textView3 = (TextView) findViewById(R.id.textView3);
        this.move4Layout = (LinearLayout) findViewById(R.id.move4Layout);
        this.move3Layout = (LinearLayout) findViewById(R.id.move3Layout);
        this.textView2 = (TextView) findViewById(R.id.textView2);
        this.move2Layout = (LinearLayout) findViewById(R.id.move2Layout);
        this.move1Layout = (LinearLayout) findViewById(R.id.move1Layout);
    }
}
