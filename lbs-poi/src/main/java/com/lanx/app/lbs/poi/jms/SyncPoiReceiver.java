package com.lanx.app.lbs.poi.jms;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanx.base.jms.activemq.ActiveMQJMSReceiver;

/**
 * 同步操作poi的ActiveMQ队列
 * 
 * @author Ramboo
 * @date 2013-09-27
 */
public class SyncPoiReceiver extends ActiveMQJMSReceiver{

    private static final Log logger = LogFactory.getLog(SyncPoiReceiver.class);

	@Autowired
	JmsHelper jmsHelper;
	
	@Override
	public <M extends ActiveMQMessage> void onMessage(M m) throws JMSException {
		long start = System.currentTimeMillis();
		
		ObjectMessage objMsg = (ObjectMessage) m;
		
		jmsHelper.process(objMsg);

		if(logger.isInfoEnabled())
			logger.info(SyncPoiReceiver.class + "objMsg -> " + objMsg + " onMessage consume " + (System.currentTimeMillis() - start) + " ms");
	}
}
