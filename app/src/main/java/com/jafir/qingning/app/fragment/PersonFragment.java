package com.jafir.qingning.app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jafir.qingning.R;
import com.jafir.qingning.app.activity.BezierAcitvity;
import com.jafir.qingning.app.activity.MyEventActivity;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;
import org.kymjs.kjframe.utils.KJLoger;

/**
 * Created by jafir on 16/4/19.
 */
public class PersonFragment extends SupportFragment {


    private Activity aty;
    private View layout;
    @BindView(id = R.id.person_my_likes, click = true)
    private LinearLayout mLike;
    @BindView(id = R.id.person_my_guidebook, click = true)
    private LinearLayout mGuidebook;
    @BindView(id = R.id.person_my_event, click = true)
    private LinearLayout mEvent;
    @BindView(id = R.id.person_my_order, click = true)
    private LinearLayout mOrder;
    @BindView(id = R.id.person_setting, click = true)
    private LinearLayout mSetting;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = getActivity();
        layout = View.inflate(aty, R.layout.frag_person, null);
        return layout;
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.person_my_event:
                startActivity(new Intent(aty, MyEventActivity.class));
                break;
            case R.id.person_my_likes:
                break;
            case R.id.person_my_order:
                break;
            case R.id.person_my_guidebook:
                break;
            case R.id.person_setting:
                KJLoger.debug("fab");
                Snackbar.make(v, "nsnaker", Snackbar.LENGTH_LONG).show();
                startActivity(new Intent(aty, BezierAcitvity.class));
                break;
        }
    }

}
