package com.jafir.qingning.app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.activity.BezierAcitvity;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;
import org.kymjs.kjframe.utils.KJLoger;

/**
 * Created by jafir on 16/4/19.
 */
public class PersonFragment extends SupportFragment{


     private Activity aty;
    private View layout;
    @BindView(id = R.id.fab,click = true)
    private ImageView mFab;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = getActivity();
        layout = View.inflate(aty, R.layout.frag_person,null);
        return layout;
    }

    @Override
    protected void initData() {
        super.initData();

    }
    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        KJLoger.debug("dianjile");
        if(v.getId() == R.id.fab) {
            KJLoger.debug("fab");
            Snackbar.make(v,"nsnaker",Snackbar.LENGTH_LONG).show();
            startActivity(new Intent(aty, BezierAcitvity.class));
        }
    }

}
