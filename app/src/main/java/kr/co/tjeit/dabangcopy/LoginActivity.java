package kr.co.tjeit.dabangcopy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import kr.co.tjeit.dabangcopy.util.ContextUtil;

public class LoginActivity extends BaseActivity {

    private android.widget.EditText idEdtTxt;
    private android.widget.EditText pwEdtTxt;
    private android.widget.CheckBox autoCheckBox;
    private android.widget.Button loginBtn;
    private com.facebook.login.widget.LoginButton loginbutton;
    private android.widget.Button faceBookLoginBtn;

    //    페이스북 로그인
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        setupEvents();
        setValues();
        callbackManager = CallbackManager.Factory.create();
        loginbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setupEvents() {

        faceBookLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
            }
        });

//        1. 아이디 입력 상태에서 로그인 버튼 누르면 메인 액티비티로 intent
//        2. 프로필 설정화면에서 나타나는 이메일을 이곳에서 입력하는 id로 표시
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idEdtTxt.getText().length() > 0) {
                    Intent intent = new Intent(mContext, MainActivity.class);
//                    ContextUtil.setLoginUserInfo(idEdtTxt.getText().toString());
                    ContextUtil.setLoginUserId(mContext, idEdtTxt.getText().toString());
                    ContextUtil.setLoginUser(mContext, "A사용자", "011-222-3333", idEdtTxt.getText().toString(), "tmpURL");
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(mContext, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        autoCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ContextUtil.setAutoLogin(mContext, isChecked);
            }
        });
    }

    @Override
    public void setValues() {
        boolean autoLogin = ContextUtil.getAutoLogin(mContext);
        autoCheckBox.setChecked(autoLogin);
        if (autoLogin) {
            idEdtTxt.setText(ContextUtil.getLoginUserId(mContext));
        }
    }

    @Override
    public void bindViews() {
        this.faceBookLoginBtn = (Button) findViewById(R.id.faceBookLoginBtn);
        this.loginbutton = (LoginButton) findViewById(R.id.login_button);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.autoCheckBox = (CheckBox) findViewById(R.id.autoCheckBox);
        this.pwEdtTxt = (EditText) findViewById(R.id.pwEdtTxt);
        this.idEdtTxt = (EditText) findViewById(R.id.idEdtTxt);
    }
}
