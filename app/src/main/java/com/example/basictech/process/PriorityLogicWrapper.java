package com.example.basictech.process;

/**
 * Copyright (C), 2016-2020
 * FileName: PriorityLogicWrapper
 * Author: zhengwei
 * Date: 2020-05-11 18:59
 * Description:
 */
public class PriorityLogicWrapper implements Comparable<PriorityLogicWrapper> {
    public int priority = 0;
    public Class<? extends BaseApplicationLogic> logicClass = null;
    public BaseApplicationLogic instance;

    public PriorityLogicWrapper(int priority, Class<? extends BaseApplicationLogic> logicClass) {
        this.priority = priority;
        this.logicClass = logicClass;
    }

    @Override
    public int compareTo(PriorityLogicWrapper o) {
        return o.priority - this.priority;
    }
}
