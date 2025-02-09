/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.sys.biz.job
 * Date: 2020/6/16 14:43
 * Author: shuigedeng
 */
package com.taotao.cloud.sys.biz.timetask.xxljob;

import com.taotao.cloud.common.utils.log.LogUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * UserJobHandler
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 11:54:43
 */
@Component
public class UserJobHandler {

	@XxlJob("TJobHandler")
	public ReturnT<String> throwJobHandler(String param) throws Exception {
		XxlJobHelper.log("XXL-JOB, throwwwwwwwwwwwwww");

		LogUtil.info("=============xxljob throwwwwwwwwwwwwwwwwwwwwwwwwww");

		throw new Exception("XXL-JOB, throwwwwwwwwwwwwww");
	}

	@XxlJob("TestJobHandler")
	public ReturnT<String> testJobHandler(String param) throws Exception {
		XxlJobHelper.log("XXL-JOB, successsssssssssss");

		LogUtil.info("=============xxljob succcccccccccccccc");

		return ReturnT.SUCCESS;
	}

    @XxlJob("UserJobHandler")
    public ReturnT<String> userJobHandler(String param) throws Exception {
	    XxlJobHelper.log("XXL-JOB, Hello World.");
	    XxlJobHelper.log("XXL-JOB, Hello World.1");
	    XxlJobHelper.log("XXL-JOB, Hello World.2");
	    XxlJobHelper.log("XXL-JOB, Hello World.3");
	    XxlJobHelper.log("XXL-JOB, Hello World.4");

	    LogUtil.info("=============xxljob eeeeeeeeeeeeeeeeeeeee");

        //for (int i = 0; i < 5; i++) {
	    //    XxlJobHelper.log("beat at:" + i);
        //    System.out.println("XXL-JOB测试-----" + i);
        //    TimeUnit.SECONDS.sleep(2);
        //}

		//throw new RuntimeException("XXL-JOB测试异常");

        return ReturnT.FAIL;
    }
}
