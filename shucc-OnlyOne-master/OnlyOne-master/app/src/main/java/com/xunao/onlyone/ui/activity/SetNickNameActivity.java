package com.xunao.onlyone.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.xunao.onlyone.App;
import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;
import com.xunao.onlyone.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenchao on 16/6/14.
 * cc@cchao.org
 * 修改昵称
 */
public class SetNickNameActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.edit_input)
    EditText editInput;
    @BindView(R.id.img_clean)
    ImageView imgClean;

    @Override
    protected int bindLayout() {
        return R.layout.activity_set_nick_name;
    }

    @Override
    protected void initData() {
        setImgBack(imgBack);
        editInput.setText(App.getSpUtil().getNickName());
        editInput.requestFocus();
    }

    @Override
    protected void bindEvent() {
        imgClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editInput.setText("");
            }
        });
        editInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    imgClean.setVisibility(View.GONE);
                    hideKeyboard(editInput);
                } else if (imgClean.getVisibility() != View.VISIBLE) {
                    imgClean.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick(R.id.btn_save) void save() {
        if (!TextUtils.isEmpty(editInput.getText().toString())) {
            App.getSpUtil().saveNickName(editInput.getText().toString());
            imgClean.setVisibility(View.GONE);
        }
        hideKeyboard(editInput);
        ToastUtil.Infotoast(this, getResources().getString(R.string.save_success));
    }
}
