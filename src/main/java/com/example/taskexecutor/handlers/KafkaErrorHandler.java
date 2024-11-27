package com.example.taskexecutor.handlers;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.RecordDeserializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

public class KafkaErrorHandler implements CommonErrorHandler {

    private final Logger logger = LoggerFactory.getLogger(KafkaErrorHandler.class);

    @Override
    public boolean handleOne(Exception exception, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        return handle(exception, consumer);
    }

    @Override
    public void handleOtherException(Exception exception, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener) {
        handle(exception, consumer);
    }

    private boolean handle(Exception exception, Consumer<?, ?> consumer) {
        if (exception instanceof RecordDeserializationException ex) {
            logger.error("Cannot deserialize kafka record:", exception);
            consumer.seek(ex.topicPartition(), ex.offset() + 1L);
            consumer.commitSync();
            logger.warn("Partition was changed");
            return true;
        } else {
            logger.error("Kafka consumer exception:", exception);
            return false;
        }
    }
}
