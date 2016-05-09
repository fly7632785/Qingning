package com.jafir.qingning.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.jafir.qingning.R;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

/**
 * Created by jafir on 16/4/19.
 */
public class EventFragment extends SupportFragment{


    private Activity aty;
    private View layout;
    @BindView(id = R.id.event_toolbar)
    private Toolbar mToolbar;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = getActivity();
        layout = View.inflate(aty, R.layout.frag_event,null);
        return layout;
    }

    @Override
    protected void initData() {
        super.initData();

    }
    

}
