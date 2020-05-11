package com.example.basictech.process.client;

import com.example.basictech.process.MaApplication;

/**
 * Copyright (C), 2016-2020
 * FileName: AppInit
 * Author: zhengwei
 * Date: 2020-05-11 19:12
 * Description: 主进程application，注册到AndroidManifest文件中
 */
public class AppInit extends MaApplication {
    @Override
    public boolean needMultipleProcess() {
        return true;
    }

    @Override
    protected void initializeLogic() {
        registerApplicationLogic(":webbrowser", 1, WebApplication.class);
        registerApplicationLogic(":bgmusic", 2, BgMusicApplication.class);
    }
}
