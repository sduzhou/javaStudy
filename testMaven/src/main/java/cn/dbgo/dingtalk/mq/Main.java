package cn.dbgo.dingtalk.mq;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class Main {
	
	public Main() throws Exception{
		
		QueueConsumer consumer = new QueueConsumer("queue");
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		
		Producer producer = new Producer("queue");
		
		for (int i = 0; i < 100000; i++) {
			HashMap message = new HashMap();
			message.put("message number", i);
			producer.sendMessage(message);
			System.out.println("Message Number "+ i +" sent.");
		}
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/mq")
	public void main(String[] args) throws Exception{
	  new Main();
	}
}