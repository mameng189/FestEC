package com.matt.latter.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.matt.latter.delegates.LatterDelegate;
import com.matt.latter.ec.R;
import com.matt.latter.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by mamen on 2017/9/16.
 */

public class SignInDelegate extends LatterDelegate{


    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()){
            SignHandler.onSignIn("",mISignListener);
            Toast.makeText(getContext(),"登录成功",Toast.LENGTH_LONG).show();
        }
    }
    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat(){

    }
    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink(){
        start(new SignUpDelegate());
    }

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }
    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();
        boolean isPass = true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }
        if (password.isEmpty()||password.length()<6){
            mPassword.setError("请填写至少6位密码");
            isPass = false;
        }else{
            mPassword.setError(null);
        }
        return  isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
