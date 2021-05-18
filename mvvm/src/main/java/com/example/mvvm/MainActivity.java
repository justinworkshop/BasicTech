package com.example.mvvm;

import android.net.Network;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.mvvm.bean.Account;
import com.example.mvvm.databinding.ActivityMainBinding;
import com.example.mvvm.livedata.NetworkLiveData;
import com.example.mvvm.viewmodel.AccountViewModel;
import com.example.mvvm.viewmodel.TestViewModel;

/**
 * DataBinding使用3个步骤
 * 1.启动DataBinding
 * 2.修改布局文件为DataBinding布局
 * 3.数据绑定
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Account account;
    private AccountViewModel accountViewModel;
    private TestViewModel testViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        account = new Account();
        account.setName("CiCi");
        account.setLevel(100);
        binding.setAccount(account);
        binding.setActivity(this);

        accountViewModel = new AccountViewModel();
        binding.setAccountViewModel(accountViewModel);

        // 使用LiveData
        testViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(TestViewModel.class);
        binding.setLifecycleOwner(this);
        MutableLiveData<String> nameEvent = testViewModel.getNameEvent();
        nameEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, "onChanged: " + s, Toast.LENGTH_SHORT).show();
            }
        });

        // 使用自定义LiveData监听网络变化
        NetworkLiveData.getInstance(this).observe(this, new Observer<Network>() {
            @Override
            public void onChanged(Network network) {
                Toast.makeText(MainActivity.this, "网络变化: " + network.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onBtnClick(View view) {
        int level = account.getLevel();
        account.setLevel(level + 1);
        // 这里无需手动binding.setAccount(account)来更新界面，Account内部使用Observer观察者模式来更新
    }
}
