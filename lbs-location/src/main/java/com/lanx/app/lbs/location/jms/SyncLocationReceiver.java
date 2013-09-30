package com.lanx.app.lbs.location.jms;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanx.base.jms.activemq.ActiveMQJMSReceiver;

/**
 * 同步操作location的ActiveMQ队列
 * 
 * @author Ramboo
 * @date 2013-09-16
 */
public class SyncLocationReceiver extends ActiveMQJMSReceiver{

    private static final Log logger = LogFactory.getLog(SyncLocationReceiver.class);

	@Autowired
	JmsHelper jmsHelper;
	
	@Override
	public <M extends ActiveMQMessage> void onMessage(M m) throws JMSException {
		long start = System.currentTimeMillis();
		
		ObjectMessage objMsg = (ObjectMessage) m;
		
		jmsHelper.process(objMsg);

		if(logger.isInfoEnabled())
			logger.info(SyncLocationReceiver.class + "objMsg -> " + objMsg + " onMessage consume " + (System.currentTimeMillis() - start) + " ms");
	}
}
