package com.sfeir.websockets.poc.shared;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class JSONTest {

	@Test
	public void testMessageReadWrite() {
		News news = new News(new Date(), "D�but des matchs de la 3�me journ�e.");
		Message m = new Message(news);
		Message copy = Message.read(Message.write(m));
		assertEquals(m.getType(), copy.getType());
		assertEquals(m.getNews(), copy.getNews());
	}
	
}
