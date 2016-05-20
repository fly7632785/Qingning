package com.jafir.qingning.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jafir.qingning.R;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

/**
 * Created by jafir on 16/4/19.
 */
public class ShowFragment extends SupportFragment{


    private Activity aty;
    private View layout;
    @BindView(id = R.id.show_toolbar)
    private Toolbar mToolbar;
    @BindView(id = R.id.fab,click = true)
    private ImageView mFab;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = getActivity();
        layout = View.inflate(aty, R.layout.frag_show,null);
        return layout;
    }

    @Override
    protected void initData() {
        super.initData();

    }


}
