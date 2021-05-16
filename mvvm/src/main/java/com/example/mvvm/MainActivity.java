package com.example.mvvm;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.mvvm.bean.Account;
import com.example.mvvm.databinding.ActivityMainBinding;

/**
 * DataBinding使用3个步骤
 * 1.启动DataBinding
 * 2.修改布局文件为DataBinding布局
 * 3.数据绑定
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        account = new Account();
        account.setName("CiCi");
        account.setLevel(100);
        binding.setAccount(account);
        binding.setActivity(this);
    }

    public void onBtnClick(View view) {
        int level = account.getLevel();
        account.setLevel(level + 1);
        // 这里无需手动binding.setAccount(account)来更新界面，Account内部使用Observer观察者模式来更新
    }
}
