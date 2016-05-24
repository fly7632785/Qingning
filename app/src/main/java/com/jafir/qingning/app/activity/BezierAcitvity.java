package com.jafir.qingning.app.activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jafir.qingning.R;
import com.jafir.qingning.model.bean.Chehang;
import com.jafir.qingning.net.api.TestBean;

import org.kymjs.kjframe.utils.KJLoger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jafir on 16/5/19.
 */
public class BezierAcitvity extends BaseActivity {
    @Override
    public void setRootView() {
        setContentView(R.layout.bezier);
    }


    @Override
    public void initData() {
        super.initData();
        //test gson
        Gson gson = new Gson();

        Chehang c = new Chehang();
        c.setName("chehang");
        c.setDesc("desc");
        c.setZuci("1111");


        List<Chehang> chelist = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Chehang cc = new Chehang();
            cc.setName("chehang");
            cc.setDesc("desc");
            cc.setZuci("1111");
            chelist.add(cc);
        }

        String  gsonst = gson.toJson(c);
        KJLoger.debug("che:"+gsonst);

        String  gsonslist = gson.toJson(chelist);
        KJLoger.debug("chelist:"+gsonslist);

        List<Chehang> chehangs = gson.fromJson(gsonslist,List.class);
        List<Chehang> chehangss = gson.fromJson(gsonslist,new TypeToken<List<Chehang>>(){}.getType());


        KJLoger.debug("chehangs:"+chehangs);
        KJLoger.debug("chehangss:"+chehangss);


        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        String[] strings = gson.fromJson(jsonArray, String[].class);
        for (String s :
                strings) {
            KJLoger.debug(s);
        }

        List<String> ddd = gson.fromJson(jsonArray, List.class);
        KJLoger.debug(ddd.toString());

        //上面会泛型擦除 要用下面的
        List<String> ss = gson.fromJson(jsonArray,new TypeToken<List<String>>(){}.getType());
        KJLoger.debug(ss.toString());





        //test1

        String t1 = "{\"code\":9999,\"message\":\"000\",\"data\":{\"name\":\"zss\",\"age\":\"23\"}}";
        String t2 = "{\"code\":9999,\"message\":\"000\",\"data\":[]}";


//        Result r1 = gson.fromJson(t1, Result.class);
//        KJLoger.debug(r1.toString());
//        Result r2 = gson.fromJson(t2, Result.class);
//        KJLoger.debug(r2.toString());
//
//        if(r2.data.contains("[")){
//            //空
//            KJLoger.debug("[]");
//        }else if(r2.data.contains("{")){
//            KJLoger.debug("{}");
//
//        }


        String mm = "{\"name\":\"name1111\",\"age\":\"1\",\"email\":\"533@qq.com\",\"time\":\"123123123123\",\"date\":\"2016-12-1 22:1:2\"}";

        TestBean testBean = new TestBean();
        testBean.setName("name");
        testBean.setAge(111);
        testBean.setEmail(null);
        testBean.setDate(new Date());
        testBean.setTime(123123123123l);
        Gson gson1 = new GsonBuilder()
                .serializeNulls()
//                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        TestBean tb = gson1.fromJson(mm, TestBean.class);
        KJLoger.debug("tb:"+tb);
        String gs = gson1.toJson(testBean);
        KJLoger.debug("gs:"+gs);




    }
}
