package com.jafir.qingning.app.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.fragment.EventFragment;
import com.jafir.qingning.app.fragment.GuideBookFragment;
import com.jafir.qingning.app.fragment.PersonFragment;
import com.jafir.qingning.app.fragment.RentFragment;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.KJActivityStack;
import org.kymjs.kjframe.ui.SupportFragment;
import org.kymjs.kjframe.utils.DensityUtils;
import org.kymjs.kjframe.utils.KJLoger;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by jafir on 16/4/18.
 */
public class MainActivity extends BaseActivity {


    /**
     * tab
     */
    //tab图片宽高
    private static final float IMG_WIDTH = 21;
    private static final float IMG_HEIGHT = 21;
    private static final String TAG = MainActivity.class.getSimpleName();

    //tab
    @BindView(id = R.id.tab_menu_rent)
    private TextView mTvRent;
    @BindView(id = R.id.tab_menu_event)
    private TextView mTvEvent;
    @BindView(id = R.id.tab_menu_show)
    private TextView mTvShow;
    @BindView(id = R.id.tab_menu_person)
    private TextView mTvPerson;
    //tab layout
    @BindView(id = R.id.ly_tab_menu_rent, click = true)
    private LinearLayout mMenuRent;
    @BindView(id = R.id.ly_tab_menu_event, click = true)
    private LinearLayout mMenuEvent;
    @BindView(id = R.id.ly_tab_menu_show, click = true)
    private LinearLayout mMenuShow;
    @BindView(id = R.id.ly_tab_menu_person, click = true)
    private LinearLayout mMenuPerson;


    private RentFragment mRentFragment;
    private EventFragment mEventFragment;
    private GuideBookFragment mGuideBookFragment;
    private PersonFragment mPersonFragment;

    private SupportFragment mCurrentFragment;



    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

//        KJLoger.debug("还有activity："+ActivityManager.getScreenManager().getActivityStack().toString());

        mRentFragment = new RentFragment();
        mEventFragment = new EventFragment();
        mGuideBookFragment = new GuideBookFragment();
        mPersonFragment = new PersonFragment();

        //初始化
        changeFragment(R.id.main_contain, mRentFragment);
        mCurrentFragment = mRentFragment;

        //设置tab中图片的大小
        setImgSize(mTvEvent);
        setImgSize(mTvRent);
        setImgSize(mTvShow);
        setImgSize(mTvPerson);

        //初始化选中中间图标
        mMenuRent.setSelected(true);
//
//        Observable.just("hellow world","hwllo jafir").subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                ViewInject.toast(s);
//            }
//        });
//
//
//        Observable.from(new String[]{"1","2","3"})
//                .subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                KJLoger.debug(s);
//            }
//        });
//
//
//        Observable ob = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                KJLoger.debug("call:"+subscriber);
//
//                String string = "11111";
//                subscriber.onNext(string);
//                subscriber.onCompleted();
//
//            }
//        });
//        //map转化 数据对象 类型  用map
////        ob.map(new Func1<Integer,Drawable>() {
////            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
////            @Override
////            public Drawable call(Integer redID) {
////                return  getTheme().getDrawable(redID);
////            }
////        });
//
//        Subscriber s = new Subscriber() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Object o) {
//                KJLoger.debug("subscriber:"+o);
//            }
//        };
//
//        Observer o = new Observer() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Object o) {
//                KJLoger.debug("observer:"+o);
//            }
//        };
//
//
//        ob.doOnNext(new Action1<String>() {
//            @Override
//            public void call(String string) {
//                string=string+"doOnNext";
//                KJLoger.debug("doonnext....："+string);
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String string) {
//                KJLoger.debug("action....："+string);
//            }
//        });
////        ob.subscribe(s);
////        ob.subscribe(o);
//
//
//        //aciton1 有参没有返回值
//        ob.subscribe(new Action1() {
//            @Override
//            public void call(Object o) {
//
//            }
//        });
//
//
//       /*不能直接放action0*/
////        ob.subscribe(aciton0)
//
//
//
//        final int resId = 0;
//        Observable.create(new Observable.OnSubscribe<Drawable>(){
//            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void call(Subscriber<? super Drawable> subscriber) {
//                Drawable drawable  = getTheme().getDrawable(resId);
//                subscriber.onNext(drawable);
//                subscriber.onCompleted();
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Drawable>() {
//
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Drawable drawable) {
//                //dosomethings
//            }
//        });
//
//
//        //。map可以 有很多次，每次都能 执行以下 类型变换的操作
//        //比如 我们可以通过resId把图片获取到 然后转化为Drawable返回
//        //这种链式的模式，就是可以轻易的不断地叠加 更多复杂的操作，扩展性很强
//        ob.map(new Func1<String, String>() {
//            @Override
//            public String call(String o) {
//                return o;
//            }
//        });
//
//
//        ob.map(new Func1<String,Observable<Drawable>>() {
//            @Override
//            public Observable<Drawable> call(String o) {
//
//                return ApiManager.getXXX(o);
//            }
//        });
//
//
//        ob.flatMap(new Func1<String,Observable<Drawable>>() {
//            @Override
//            public Observable<Drawable> call(String o) {
//
//                return ApiManager.getXXX(o);
//            }
//        });


//        test();
    }

    public void test (){

        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        Student[] students = new Student[]{student1,student2,student3};
        Course course1 = new Course();
        course1.setName("course1111");
        Course course2 = new Course();
        course2.setName("course2");
        Course course3 = new Course();
        course3.setName("course3");
        Course[] courses = new Course[]{course1,course2,course3};
        student1.setCourses(courses);
        student2.setCourses(courses);
        student3.setCourses(courses);
        student1.setName("stu1");
        student2.setName("stu2");
        student3.setName("stu3");
        Subscriber<Course> subscriber = new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
//                Log.d("debug", course.getName());
                KJLoger.debug(course.getName());
                System.out.print(course.getName());
            }
        };
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(subscriber);


    }



    class Student {
        private String name;
        private Course[] courses;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Course[] getCourses() {
            return courses;
        }

        public void setCourses(Course[] courses) {
            this.courses = courses;
        }
    }


    class Course{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }





    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ly_tab_menu_rent:
                resetSelected();
                mMenuRent.setSelected(true);
                //改变内容
                changeFragment(R.id.main_contain, mRentFragment);
                //设置现在的fragment
                mCurrentFragment = mRentFragment;
                break;
            case R.id.ly_tab_menu_event:
                resetSelected();
                mMenuEvent.setSelected(true);
                //改变内容
                changeFragment(R.id.main_contain, mEventFragment);
                //设置现在的fragment
                mCurrentFragment = mEventFragment;
                break;
            case R.id.ly_tab_menu_show:
                resetSelected();
                mMenuShow.setSelected(true);
                //改变内容
                changeFragment(R.id.main_contain, mGuideBookFragment);
                //设置现在的fragment
                mCurrentFragment = mGuideBookFragment;
                break;
            case R.id.ly_tab_menu_person:
                resetSelected();
                mMenuPerson.setSelected(true);
                //改变内容
                changeFragment(R.id.main_contain, mPersonFragment);
                //设置现在的fragment
                mCurrentFragment = mPersonFragment;
                break;
        }
    }

    /**
     * 设置tab里面每个图片的大小
     *
     * @param textView
     */
    private void setImgSize(TextView textView) {
        Drawable d = textView.getCompoundDrawables()[1];
        d.setBounds(0, 0, DensityUtils.dip2px(this, IMG_WIDTH), DensityUtils.dip2px(this, IMG_HEIGHT));
        textView.setCompoundDrawables(null, d, null, null);
    }

    /**
     * 重置所有选中状态
     */
    private void resetSelected() {
        mMenuRent.setSelected(false);
        mMenuEvent.setSelected(false);
        mMenuShow.setSelected(false);
        mMenuPerson.setSelected(false);
    }


    /**
     * 退出应用
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(aty).setTitle("您要退出么？")
                .setNegativeButton("我才不呢", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("退，必须退", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KJActivityStack.create().appExit(aty);
                    }
                }).create().show();
    }


    @Override
    public void setRootView() {
        setContentView(R.layout.aty_main);
    }


}
