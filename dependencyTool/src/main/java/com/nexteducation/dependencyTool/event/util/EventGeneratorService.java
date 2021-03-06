package com.nexteducation.dependencyTool.event.util;

import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexteducation.dependencyTool.util.ERPApp;
import com.nexteducation.dependencyTool.util.LogMessage;
import com.nexteducation.lib.event.annotations.EventableEntity;
import com.nexteducation.lib.event.enums.EventActionType;
import com.nexteducation.lib.event.enums.EventType;
import com.nexteducation.lib.event.payloads.Payload;
import com.nexteducation.nextevent.service.core.ThriftEventProducerService;
import com.nexteducation.nextevent.service.impl.ThriftEventProducerServiceImpl;
import com.nexteducation.nextevent.thrift.event.Event;

@Component
public class EventGeneratorService {

	@Autowired
	private transient ThriftEventProducerService thriftEventProducerService;

	public boolean isEventableEntityObject(final Object obj, final EventActionType actionType) {
		final Class<? extends Object> clazz = obj.getClass();
		if (clazz.isAnnotationPresent(EventableEntity.class)) {
			final EventableEntity eventableEntity=clazz.getAnnotation(EventableEntity.class);
			final EventActionType[] actionTypes=eventableEntity.actionTypes();
			if(actionTypes==null || actionTypes.length<=0){
				LogMessage.log("actionTypes field is not assign in @EventableEntity annotation. For Class "+clazz.getName());
				return false;
			}
			boolean flag=false;
			for (final EventActionType eventActionType : actionTypes) {
				if(eventActionType==EventActionType.All){
					flag = true;
				}
				if(actionType==eventActionType){
					flag = true;
				}
			}
			if(flag){
				return true;
			}
		}
		return false;
	}

	private void produceEvent(final Object obj, final EventActionType actionType, final Payload payload) throws Exception {
		final Class<? extends Object> clazz = obj.getClass();
		final ObjectMapper objectMapper=new ObjectMapper();
		if (clazz.isAnnotationPresent(EventableEntity.class)) {
			final EventableEntity eventableEntity=clazz.getAnnotation(EventableEntity.class);
			String[] topics = eventableEntity.topics();

			final EventType type = eventableEntity.type();
			final Event pushEvent = new Event(); //It sets default values to id,createdOn,persistent,targetType,priority,eventExpirationTime,encoding,version
			pushEvent.setCreatedBy(-1); // service.getCurrentSessionUser().getId()
			pushEvent.setCreatedOn(new Date().getTime()+"");
			pushEvent.setEventAction(actionType.name()); // like Insert, Update
			pushEvent.setEventSourceIp(InetAddress.getLocalHost().getHostAddress());
			pushEvent.setEventSourceAppName("NextERP");

			final List<String> tags = new ArrayList<String>();
			if(type==EventType.OBJECT_EVENT){
				tags.add("object");
				pushEvent.setTags(tags);
			}else if(type == EventType.ACTION_EVENT){
				tags.add("association");
				pushEvent.setTags(tags);
			}
			pushEvent.setPayload(objectMapper.writeValueAsString(payload));
			pushEvent.setPayloadType(clazz.getName()); // To Identify Payload Class

			final Properties properties = new Properties();
			final InputStream in = this.getClass().getClassLoader().getResourceAsStream("nextevent.properties");
			properties.load(in);
			ThriftEventProducerServiceImpl.init(properties);

			if(topics==null || topics.length<=0){
				topics=new String[]{obj.getClass().getSimpleName()};
			}
			for(final String topic:topics){
				pushEvent.setTarget(topic); // Kafka Topic Name
				pushEvent.setEventType(topic); // like Branch, Student
				pushEvent.setTargetPartition("kafka.producer.DefaultPartitioner");
				thriftEventProducerService.prepareEventMessage().pushEventV1(pushEvent).produce();
				LogMessage.log(actionType.name()+" event fired on "+obj.getClass().getName()+" object to topic:"+topic);
			}

		}
	}

	public void generateUpdateEvent(final Object entity, final Payload payload) {
		LogMessage.log("In EventGeneratorService.generateUpdateEvent()");
		if(isEventableEntityObject(entity,EventActionType.Update)){
			try{
				produceEvent(entity,EventActionType.Update,payload);
			}catch(final Exception e){
				LogMessage.log(e.getMessage(), e);
			}
		}
	}
	public void generateInsertEvent(final Object entity, final Payload payload) {
		LogMessage.log("In EventGeneratorService.generateInsertEvent()");
		if(isEventableEntityObject(entity,EventActionType.Insert)){
			try{
				produceEvent(entity,EventActionType.Insert,payload);
			}catch(final Exception e){
				LogMessage.log(e.getMessage(), e);
			}
		}
	}
	public void generateDeleteEvent(final Object entity, final Payload payload) {
		LogMessage.log("In EventGeneratorService.generateDeleteEvent()");
		if(isEventableEntityObject(entity,EventActionType.Delete)){
			try{
				produceEvent(entity,EventActionType.Delete,payload);
			}catch(final Exception e){
				LogMessage.log(e.getMessage(), e);
			}
		}
	}

	public void generateLoadEvent(final Object entity, final Payload payload) {
		LogMessage.log("In EventGeneratorService.generateLoadEvent()");
		if(isEventableEntityObject(entity,EventActionType.Load)){
			try{
				produceEvent(entity,EventActionType.Load,payload);
			}catch(final Exception e){
				LogMessage.log(e.getMessage(), e);
			}
		}
	}

	public void generateEvent(final Object payload,final String payloadType,final String eventActionType,final EventType eventType,final String[] topics){
		LogMessage.log("In EventGeneratorService.generateEvent()");
		if(payload==null){
			LogMessage.log("Can not generate event because payload found null.");
			return;
		}
		if(ERPApp.isEmptyString(payloadType)){
			LogMessage.log("Can not generate event because payloadType found empty.");
			return;
		}
		if(ERPApp.isEmptyString(eventActionType)){
			LogMessage.log("Can not generate event because eventActionType found empty.");
			return;
		}
		if(ERPApp.isEmptyString(eventActionType)){
			LogMessage.log("Can not generate event because eventType found empty.");
			return;
		}
		if(topics==null || topics.length<=0){
			LogMessage.log("Can not generate event because topics found null or empty.");
			return;
		}
		try {
			produceNormalEvent(payload, payloadType, eventActionType, eventType, topics);
		} catch (final Exception e) {
			LogMessage.log(e.getMessage(), e);
		}
	}

	private void produceNormalEvent(final Object payload, final String payloadType, final String eventActionType, final EventType eventType,
			final String[] topics) throws Exception{
		final ObjectMapper objectMapper=new ObjectMapper();

		final Event pushEvent = new Event(); //It sets default values to id,createdOn,persistent,targetType,priority,eventExpirationTime,encoding,version
		pushEvent.setCreatedBy(-1); // service.getCurrentSessionUser().getId()
		pushEvent.setCreatedOn(new Date().getTime()+"");
		pushEvent.setEventAction(eventActionType); // like Insert, Update
		pushEvent.setEventSourceIp(InetAddress.getLocalHost().getHostAddress());
		pushEvent.setEventSourceAppName("NextERP");

		final List<String> tags = new ArrayList<String>();
		if(eventType==EventType.OBJECT_EVENT){
			tags.add("object");
			pushEvent.setTags(tags);
		}else if(eventType == EventType.ACTION_EVENT){
			tags.add("association");
			pushEvent.setTags(tags);
		}
		pushEvent.setPayload(objectMapper.writeValueAsString(payload));
		pushEvent.setPayloadType(payloadType); // To Identify Payload Class

		final Properties properties = new Properties();
		final InputStream in = this.getClass().getClassLoader().getResourceAsStream("nextevent.properties");
		properties.load(in);
		ThriftEventProducerServiceImpl.init(properties);
		for(final String topic:topics){
			pushEvent.setTarget(topic); // Kafka Topic Name
			pushEvent.setEventType(topic); // like Branch, Student
			pushEvent.setTargetPartition("kafka.producer.DefaultPartitioner");
			thriftEventProducerService.prepareEventMessage().pushEventV1(pushEvent).produce();
			LogMessage.log(eventActionType+" event fired on "+payloadType+" object to topic:"+topic);
		}
	}
}
