package kr.co.tjeit.dabangcopy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Locale;

import io.apptik.widget.MultiSlider;

public class RoomFilterActivity extends BaseActivity {

    private Button okBtn;
    private ToggleButton monthPayToggleBtn;
    private ToggleButton charterToggleBtn;
    private ToggleButton oneRoomBtn;
    private ToggleButton twoRoomBtn;
    private ToggleButton threeRoomBtn;
    private io.apptik.widget.MultiSlider depositSlider;
    private android.widget.TextView depositSetTxt;
    int thumb1, thumb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_filter);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("monthPay", monthPayToggleBtn.isChecked());
                intent.putExtra("charter", charterToggleBtn.isChecked());
                intent.putExtra("oneRoom", oneRoomBtn.isChecked());
                intent.putExtra("twoRoom", twoRoomBtn.isChecked());
                intent.putExtra("threeRoom", threeRoomBtn.isChecked());

                // 멀티 슬라이더의 최소, 최대값
                intent.putExtra("min", thumb1);
                intent.putExtra("max", thumb2);

               setResult(RESULT_OK, intent);
                finish();
            }
        });

        depositSlider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                if (thumbIndex == 0) {
                    thumb1 = value*500;
                } else {
                    thumb2 = value*500;
                }

                String minStr;
                int minUk = thumb1 / 10000;
                int minThousands = thumb1 % 10000;
                if (minUk == 0) {
                    minStr = String.format(Locale.KOREA, "%d", minThousands);
                } else {
                    if (minThousands == 0) {
                        minStr = String.format(Locale.KOREA, "%d억", minUk);
                    } else {
                        minStr = String.format(Locale.KOREA, "%d억%d", minUk, minThousands);
                    }
                }

                String maxStr;
                int maxUk = thumb2 / 10000;
                int maxThousands = thumb2 % 10000;
                if (maxUk == 0) {
                    maxStr = String.format(Locale.KOREA, "%d", maxThousands);
                } else {
                    if (maxThousands == 0) {
                        maxStr = String.format(Locale.KOREA, "%d억", maxUk);
                    } else {
                        maxStr = String.format(Locale.KOREA, "%d억%d", maxUk, maxThousands);
                    }
                }

                String deposit = String.format(Locale.KOREA, "%s - %s", minStr, maxStr);
                depositSetTxt.setText(deposit);
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.threeRoomBtn = (ToggleButton) findViewById(R.id.threeRoomBtn);
        this.twoRoomBtn = (ToggleButton) findViewById(R.id.twoRoomBtn);
        this.oneRoomBtn = (ToggleButton) findViewById(R.id.oneRoomBtn);
        this.depositSlider = (MultiSlider) findViewById(R.id.depositSlider);
        this.depositSetTxt = (TextView) findViewById(R.id.depositSetTxt);
        this.charterToggleBtn = (ToggleButton) findViewById(R.id.charterToggleBtn);
        this.monthPayToggleBtn = (ToggleButton) findViewById(R.id.monthPayToggleBtn);
        this.okBtn = (Button) findViewById(R.id.okBtn);
    }
}
