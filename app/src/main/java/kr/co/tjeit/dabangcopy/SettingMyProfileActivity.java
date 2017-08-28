package kr.co.tjeit.dabangcopy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.dabangcopy.util.ContextUtil;

public class SettingMyProfileActivity extends BaseActivity {

    private android.widget.Button logoutBtn;
    private Button imageChangeBtn;

    final int REQ_CODE_SELECT_IMAGE = 100;
    final int REQ_CODE_CAPTURE_IMAGE = 200;
    private de.hdodenhof.circleimageview.CircleImageView profileImageView;
    private android.widget.TextView loginUserTxt;
    private TextView changeNameBtn;
    private android.widget.EditText changeNameEdt;
    private EditText changePhoneNumEdt;
    private TextView changePhoneNumBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_my_profile);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        final String[] items = {"사진 찍기", "카메라 롤에서 선택", "사진 삭제", "취소"};
        imageChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 아이템이 선택되면 할 일
                        if (which == 0) {
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePictureIntent, REQ_CODE_CAPTURE_IMAGE);
                        } else if (which == 1) {
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
                        } else if (which == 2) {
                            profileImageView.setImageResource(R.drawable.dabang_app_logo);
                        }
                    }
                });
                alert.show();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("로그아웃");
                alert.setMessage("로그아웃 하시겠습니까?");
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContextUtil.logoutProcess(mContext);
                        finish();
                        MainActivity.activity.finish();
                    }
                });
                alert.setNegativeButton("취소", null);
                alert.show();
            }
        });

        changeNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtil.setLoginUserName(mContext, changeNameEdt.getText().toString());
                Toast.makeText(mContext, "닉네임이 설정되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        changePhoneNumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextUtil.setLoginUserPhoneNum(mContext, changePhoneNumEdt.getText().toString());
                Toast.makeText(mContext, "번호가 설정되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQ_CODE_CAPTURE_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                // 결과를 intent가 가진 모든 데이터를 받아옴 : getExtras > Bundle 변수
                Bundle extra = data.getExtras();
                Bitmap profileBitmap = (Bitmap) extra.get("data");
                profileImageView.setImageBitmap(profileBitmap);
            }
        } else if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                // 갤러리를 통해 받아온 것 > 선택된 사진이 어디에 있는지 위치 정보
                // 경로를 찾아가서 해당 사진 파일을 bitmap으로 받아와야함
                // mediaStore 클래스가 사진 파일 > 비트맵으로 변환해서 가져옴

                // try 내부 > 언제 에러가 발생할지 모르는 부분(예외 발생 가능 지점)
                try {
                    // uri 통해서 사진파일로 찾아감
                    // 사진파일 있으면 비트맵으로 변환 (변환을 해주는 객체 : getContentResolver())
                    // 예외처리 필요
                    Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    //배치해놓은 ImageView에 set
                    profileImageView.setImageBitmap(image_bitmap);
                } catch (IOException e) {
                    // 예외가 실제로 발생하면 발생하는 경우
                    // 앱이 죽지않고 실행상태를 유지하도록 대처하는 부분
                    e.printStackTrace();
                    // 어떤 예외가 발생했는지 로그로 기록
                }
            }
        }
    }

    public String getPath(Uri uri) {
        // uri가 null일경우 null반환
        if( uri == null ) {
            return null;
        }
        // 미디어스토어에서 유저가 선택한 사진의 URI를 받아온다.
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // URI경로를 반환한다.
        return uri.getPath();
    }

    @Override
    public void setValues() {
        loginUserTxt.setText(ContextUtil.getLoginUser(mContext).getLoginId());
        changeNameEdt.setText(ContextUtil.getLoginUser(mContext).getName());
        changePhoneNumEdt.setText(ContextUtil.getLoginUser(mContext).getPhoneNum());
    }

    @Override
    public void bindViews() {
        this.logoutBtn = (Button) findViewById(R.id.logoutBtn);
        this.changePhoneNumBtn = (TextView) findViewById(R.id.changePhoneNumBtn);
        this.changePhoneNumEdt = (EditText) findViewById(R.id.changePhoneNumEdt);
        this.changeNameBtn = (TextView) findViewById(R.id.changeNameBtn);
        this.changeNameEdt = (EditText) findViewById(R.id.changeNameEdt);
        this.imageChangeBtn = (Button) findViewById(R.id.imageChangeBtn);
        this.loginUserTxt = (TextView) findViewById(R.id.loginUserTxt);
        this.profileImageView = (CircleImageView) findViewById(R.id.profileImageView);
    }
}
