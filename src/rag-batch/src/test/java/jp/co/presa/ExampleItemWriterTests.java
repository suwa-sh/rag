package jp.co.presa;

import junit.framework.TestCase;
import me.suwash.rag.ExampleItemWriter;

public class ExampleItemWriterTests extends TestCase {

	private ExampleItemWriter writer = new ExampleItemWriter();
	
	public void testWrite() throws Exception {
		writer.write(null); // nothing bad happens
	}

}
