package com.taotao.cloud.core.async.parallel;



import com.taotao.cloud.core.async.callback.ICallback;
import com.taotao.cloud.core.async.callback.IWorker;
import com.taotao.cloud.core.async.executor.timer.SystemClock;
import com.taotao.cloud.core.async.worker.WorkResult;
import com.taotao.cloud.core.async.wrapper.WorkerWrapper;
import java.util.Map;

public class ParWorker2 implements IWorker<String, String>, ICallback<String, String> {
    private long sleepTime = 1000;

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public String action(String object, Map<String, WorkerWrapper> allWrappers) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "result = " + SystemClock.now() + "---param = " + object + " from 2";
    }


    @Override
    public String defaultValue() {
        return "worker2--default";
    }

    @Override
    public void begin() {
        //System.out.println(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }

    @Override
    public void result(boolean success, String param, WorkResult<String> workResult) {
        if (success) {
            System.out.println("callback worker2 success--" + SystemClock.now() + "----" + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        } else {
            System.err.println("callback worker2 failure--" + SystemClock.now() + "----"  + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        }
    }

}
