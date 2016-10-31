package com.jafir.qingning.app.activity;

import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.adapter.BaseRecyclerAdapter;
import com.jafir.qingning.model.bean.ContactPerson;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;

/**
 * Created by jafir on 16/7/19.
 */
public class ContactActivity extends BaseActivity {


    @BindView(id = R.id.recyclerview)
    private RecyclerView recyclerView;
    @BindView(id = R.id.button, click = true)
    private Button button;
    @BindView(id = R.id.progressbar)
    private ProgressBar progressBar;

    private ArrayList<ContactPerson> list = new ArrayList<>();
    private MyContactAdapter contactAdapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_contact);
    }


    @Override
    public void initData() {
        super.initData();


        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        contactAdapter = new MyContactAdapter();
        contactAdapter.setData(list);
        recyclerView.setAdapter(contactAdapter);


    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        new MyAsycTask().execute();

    }

    class MyAsycTask extends AsyncTask<Void, Void, ArrayList<ContactPerson>> {

        @Override
        protected ArrayList<ContactPerson> doInBackground(Void... params) {

            return readContact();


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected void onPostExecute(ArrayList<ContactPerson> contactPersons) {
            super.onPostExecute(contactPersons);
            KJLoger.debug(contactPersons.toString());
            contactAdapter.updateData(contactPersons);


            progressBar.setVisibility(View.GONE);
        }
    }

    private ArrayList<ContactPerson> readContact() {

        ArrayList<ContactPerson> list = new ArrayList<>();
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        //moveToNext方法返回的是一个boolean类型的数据
        while (cursor.moveToNext()) {
            //读取通讯录的姓名
            String name = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //读取通讯录的号码
            String number = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            ContactPerson c = new ContactPerson();
            c.setName(name);
            c.setPhone(number);
            list.add(c);
        }
        cursor.close();

        return list;
    }


    class MyContactAdapter extends BaseRecyclerAdapter<ContactPerson> {

        @Override
        public RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType) {

            View view = View.inflate(aty, R.layout.item_contact_recycler, null);
            view.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new MyViewHolder(view);
        }

        @Override
        public void bindMyViewHolder(RecyclerView.ViewHolder holder, int position) {

            MyViewHolder holder1 = (MyViewHolder) holder;

            ContactPerson contactPerson = mDatas.get(position);

            holder1.name.setText(contactPerson.getName());
            holder1.phone.setText(contactPerson.getPhone());


        }


        class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView name;
            public TextView phone;

            public MyViewHolder(View itemView) {
                super(itemView);

                name = (TextView) itemView.findViewById(R.id.name);
                phone = (TextView) itemView.findViewById(R.id.phone);


            }
        }
    }
}
