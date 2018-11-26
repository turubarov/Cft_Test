import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public class Main {

	public static void main(String[] args) throws IOException, TransformerException {

		ClassLoader cl = Main.class.getClassLoader();
		String srcDir = args[0];
		String dstDir = args[1];
		File srcFile = new File(srcDir);
		File dstFile = new File(dstDir);
		if (!srcFile.exists()) {
			System.err.println("Source directory does not exist");
			return;
		}
		if (!dstFile.exists()) {
			System.err.println("Directory for results does not exist");
			return;
		}
		if (!srcFile.isDirectory()) {
			System.err.println("Source place is not folder");
			return;
		}
		if (!dstFile.isDirectory()) {
			System.err.println("Distanation place is not folder");
			return;
		}
		
		InputStream is = cl.getResourceAsStream("transport.xslt");
		StreamSource streamSource = new StreamSource(is);
		TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(streamSource);
        
        for (File cur : srcFile.listFiles()) {
        	transformer.transform(new StreamSource(cur), 
			 		  new StreamResult(new File(dstDir+"/"+cur.getName())));
        }
	}

}
