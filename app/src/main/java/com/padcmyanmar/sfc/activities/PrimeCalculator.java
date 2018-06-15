package com.padcmyanmar.sfc.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.padcmyanmar.sfc.R;

import org.jetbrains.annotations.Contract;

import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PrimeCalculator extends BaseActivity {
    @BindView(R.id.btmClick)
    Button btmClick;

    @BindView(R.id.etPrimeEnter)
    EditText etPrime;

    @BindView(R.id.tvAnswer)
    TextView tvAnswer;

    int number = 5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_calculator);
        ButterKnife.bind(this,this);
    }

    @OnClick(R.id.btmClick)
    public void Calculate(){
        if(etPrime.getText()!=null) {
            number = Integer.parseInt(String.valueOf(etPrime.getText()));
        }
        executePrime(number);
    }


    private void executePrime(final int number) {
        Single<String> primeSingle = Single.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return calculatePrime(number);
            }
        });
        primeSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        tvAnswer.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Contract(pure = true)
    private String calculatePrime(int number) {
        int i =0;
        String answer = "Prime numbers are: ";
        for( i = 0;i<number+1;i++){
            if(number==2 || isPirme(i)){
                answer = answer +i+" , ";
            }
        }
        return answer;
    }

    private boolean isPirme(int number) {
        if (number % 2 == 0)
            return false;
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0)
                return false;
        }
        return true;
    }


}
