//package com.taotao.cloud.order.biz.rocketmq;
//
//import com.taotao.cloud.common.utils.JsonUtil;
//
//@RocketMQTransactionListener(
// txProducerGroup = "msgRoducerGroup",
// corePoolSize = 2,
// maximumPoolSize = 5
//)
//public class MsgMQLocalTransactionListenerImpl implements RocketMQLocalTransactionListener {
//
//    // 发送half消息成功之后，mq返回成功，回调执行本地事务操作，并返回执行事务的结果给MQ,
//    @Override
//    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
//        String type = msg.getHeaders().get("status").toString();
//        System.out.println("executeLocalTransaction: msg-"+ msg + "-arg:" + arg +"-status:"+type);
//        switch (type) {
//            case "1":
//                System.out.println("事务执行状态未知");
//                return RocketMQLocalTransactionState.UNKNOWN;
//            case "2":
//                System.out.println("事务执行状态成功");
//	            //插入订单数据
//	            String orderJson = new String(((byte[]) message.getPayload()));
//	            Order order = JsonUtil.toObject(orderJson, Order.class);
//	            orderService.save(order);
//
//	            String produceError = (String) message.getHeaders().get("produceError");
//	            if ("1".equals(produceError)) {
//		            System.err.println("============Exception：订单进程挂了，事务消息没提交");
//		            //模拟插入订单后服务器挂了，没有commit事务消息
//		            throw new RuntimeException("============订单服务器挂了");
//	            }
//
//	            return RocketMQLocalTransactionState.COMMIT;
//            case "3":
//                System.out.println("事务执行状态失败");
//                return RocketMQLocalTransactionState.ROLLBACK;
//        }
//        return RocketMQLocalTransactionState.ROLLBACK ;
//    }
//
//    //当Mq 没有收到我们返回的事务状态信息 或者 返回的事务状态为RocketMQLocalTransactionState.UNKNOWN，会
//    // 再次发送消息过来确定消息的状态
//    @Override
//    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
//        System.out.println("checkLocalTransaction:"+msg);
//	    String orderId = (String) message.getHeaders().get("orderId");
//	    System.out.println("============事务回查-orderId：" + orderId);
//	    //判断之前的事务是否已经提交：订单记录是否已经保存
//	    int count = 1;
//	    //select count(1) from t_order where order_id = ${orderId}
//	    System.out.println("============事务回查-订单已生成-提交事务消息");
//	    return count > 0 ? RocketMQLocalTransactionState.COMMIT
//		    : RocketMQLocalTransactionState.ROLLBACK;
//    }
//}
