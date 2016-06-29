package com.xunao.onlyone.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.activity.CustomServiceActivity;
import com.xunao.onlyone.ui.activity.MineInfoActivity;
import com.xunao.onlyone.ui.activity.SettingActivity;
import com.xunao.onlyone.ui.base.BaseFragment;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenchao on 16/5/27.
 * cc@cchao.org
 * 我的
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.rl_actionbar)
    RelativeLayout rlActionbar;
    @BindView(R.id.img_avator)
    CircleImageView imgAvator;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_ID)
    TextView textID;
    @BindView(R.id.text_tel)
    TextView textTel;
    @BindView(R.id.rl_info)
    RelativeLayout rlInfo;
    @BindView(R.id.text_gold)
    TextView textGold;
    @BindView(R.id.text_gold_num)
    TextView textGoldNum;
    @BindView(R.id.img_recharge)
    ImageView imgRecharge;
    @BindView(R.id.rl_gold)
    RelativeLayout rlGold;
    @BindView(R.id.rl_indiana)
    RelativeLayout rlIndiana;
    @BindView(R.id.rl_winning)
    RelativeLayout rlWinning;
    @BindView(R.id.rl_bill)
    RelativeLayout rlBill;
    @BindView(R.id.rl_show)
    RelativeLayout rlShow;
    @BindView(R.id.rl_customer)
    RelativeLayout rlCustomer;
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {
        rlInfo.setOnClickListener(this);
        rlGold.setOnClickListener(this);
        rlIndiana.setOnClickListener(this);
        rlBill.setOnClickListener(this);
        rlShow.setOnClickListener(this);
        rlCustomer.setOnClickListener(this);
        rlSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_info:
                openActivity(MineInfoActivity.class);
                break;
            case R.id.rl_gold:
                break;
            case R.id.rl_indiana:
                break;
            case R.id.rl_bill:
                break;
            case R.id.rl_show:
                break;
            case R.id.rl_customer:
                openActivity(CustomServiceActivity.class);
                break;
            case R.id.rl_setting:
                openActivity(SettingActivity.class);
                break;
            default:
                break;
        }
    }
}
