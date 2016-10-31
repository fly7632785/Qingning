package com.jafir.qingning.app.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.jafir.qingning.R;
import com.jafir.qingning.app.util.CommonValidate;
import com.jafir.qingning.model.bean.Result;
import com.jafir.qingning.model.entity.LoginEntity;
import com.jafir.qingning.model.entity.RegitsterEntity;
import com.jafir.qingning.net.impl.MyHttpClient;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.utils.KJLoger;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends BaseActivity {


    // UI references.
    @BindView(id = R.id.register_phone)
    private AutoCompleteTextView mPhoneView;
    @BindView(id = R.id.register_password)
    private EditText mPasswordView;
    @BindView(id = R.id.register_name)
    private EditText mNickname;
    @BindView(id = R.id.register_progress)
    private View mProgressView;
    @BindView(id = R.id.register_form)
    private View mLoginFormView;

    @Override
    public void initData() {
        super.initData();

        mNickname.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.register || id == EditorInfo.IME_NULL) {
                    attemptRegister();
                    return true;
                }
                return false;
            }
        });

        TextView mEmailSignInButton = (TextView) findViewById(R.id.register_sign_up);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

    }


    private void attemptRegister() {

        // Reset errors.
        mPhoneView.setError(null);
        mPasswordView.setError(null);
        mNickname.setError(null);

        // Store values at the time of the login attempt.
        String phone = mPhoneView.getText().toString();
        String password = mPasswordView.getText().toString();
        String nickname = mNickname.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !CommonValidate.PasswordValidate(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid phone address.
        if (TextUtils.isEmpty(phone)) {
            mPhoneView.setError(getString(R.string.error_field_required));
            focusView = mPhoneView;
            cancel = true;
        } else if (!CommonValidate.PhoneValidate(phone)) {
            mPhoneView.setError(getString(R.string.error_invalid_email));
            focusView = mPhoneView;
            cancel = true;
        }
        // Check for a valid nickname .
        if (TextUtils.isEmpty(nickname)) {
            mNickname.setError(getString(R.string.error_field_required));
            focusView = mNickname;
            cancel = true;
        } else if (!CommonValidate.NickNameValidate(nickname)) {
            mNickname.setError(getString(R.string.error_invalid_nickname));
            focusView = mNickname;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            //注册
            requestRegister(phone, nickname, password);
        }
    }

    class registerException extends RuntimeException {
        private String message;

        public registerException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

    private void requestRegister(final String phone, String nickname, final String password) {
        MyHttpClient.getInstance()
                .getApiService()
                .registerRx(phone, nickname, password)
                .flatMap(new Func1<Result<RegitsterEntity>, Observable<Result<LoginEntity>>>() {
                    @Override
                    public Observable<Result<LoginEntity>> call(Result<RegitsterEntity> regitsterEntityResult) {
                        KJLoger.debug("flat");
                        if (regitsterEntityResult.getRcode() != 1000) {
//                          不成功的话抛异常 然后捕获
                            throw new registerException(regitsterEntityResult.getMessage());
                        }
                        return MyHttpClient.getInstance().getApiService().loginRx(phone, password);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<LoginEntity>>() {
                    @Override
                    public void onNext(Result<LoginEntity> userResult) {
//                              登录判断
                        KJLoger.debug("response:" + userResult.toString());
                        showProgress(false);
                        ViewInject.toast(userResult.getMessage());
                        if (userResult.getRcode() == 1000) {
                            //成功
                            //请求登录 然后 跳转mainactivity
                            startActivity(new Intent(aty, MainActivity.class));
                            aty.finish();

                        }
                    }

                    @Override
                    public void onCompleted() {
                        KJLoger.debug("completed:");
                    }

                    @Override
                    public void onError(Throwable e) {
                        KJLoger.debug("error:" + e);
                        if (e instanceof registerException) {
                            showProgress(false);
                            ViewInject.toast(e.getMessage());

                        }
                    }
                });

    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    @Override
    public void setRootView() {
        setContentView(R.layout.aty_register);
    }
}

