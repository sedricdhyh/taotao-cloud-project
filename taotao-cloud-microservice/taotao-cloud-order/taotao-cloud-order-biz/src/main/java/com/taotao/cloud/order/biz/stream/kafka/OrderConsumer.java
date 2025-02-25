package com.taotao.cloud.order.biz.stream.kafka;

import com.taotao.cloud.rocketmq.channel.TaoTaoCloudSink;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.KafkaBinderMetrics;
import org.springframework.cloud.stream.binder.kafka.utils.DlqDestinationResolver;
import org.springframework.cloud.stream.binder.kafka.utils.DlqPartitionFunction;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.event.ListenerContainerIdleEvent;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

	//, @Payload String msg
	@StreamListener(value = TaoTaoCloudSink.ORDER_MESSAGE_INPUT)
	@SendTo(Processor.OUTPUT)
	public Message<?> test(Message<String> message) {
		String payload = message.getPayload();
		System.out.println("order Consumer" + payload);

		return MessageBuilder.fromMessage(message)
			.build();
	}

	//@StreamListener(value=TaoTaoCloudSink.ORDER_MESSAGE_INPUT)
	//public void in(String in, @Header(KafkaHeaders.CONSUMER) Consumer<?, ?> consumer) {
	//	System.out.println(in);
	//	consumer.pause(Collections.singleton(new TopicPartition("myTopic", 0)));
	//}

	@Bean
	public ApplicationListener<ListenerContainerIdleEvent> idleListener() {
		return event -> {
			System.out.println(event);
			if (event.getConsumer().paused().size() > 0) {
				event.getConsumer().resume(event.getConsumer().paused());
			}
		};
	}

	@Component
	public static class NoOpBindingMeters {

		NoOpBindingMeters(MeterRegistry registry) {
			registry.config().meterFilter(
				MeterFilter.denyNameStartsWith(KafkaBinderMetrics.OFFSET_LAG_METRIC_NAME));
		}
	}

	@Bean
	public DlqPartitionFunction partitionFunction() {
		return (group, record, ex) -> 0;
	}

	@Bean
	public DlqDestinationResolver dlqDestinationResolver() {
		return (rec, ex) -> {
			if ("word1".equals(rec.topic())) {
				return "topic1-dlq";
			} else {
				return "topic2-dlq";
			}
		};
	}
}
