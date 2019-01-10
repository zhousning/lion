package app.works;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import app.models.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

public class ExportDoc {
    
    private Configuration configuration;
    private String encoding;
    
    public ExportDoc(String encoding) {
        this.encoding = encoding;
        configuration = new Configuration(Configuration.VERSION_2_3_22);
        configuration.setDefaultEncoding(encoding);
        configuration.setClassForTemplateLoading(this.getClass(), "/app/works");
    }
    
    public Template getTemplate(String name) throws Exception {
        return configuration.getTemplate(name);
    }
    
    public String getImageStr(String image) throws IOException {
        InputStream is = new FileInputStream(image);
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] data = new byte[is.available()];
        is.read(data); is.close();
        return encoder.encode(data);
    }
    
    public Map<String, Object> getDataMap() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        User user = new User();
        user.setId(3);
        user.setPassword("123");
        dataMap.put("user", user);
        try {
            dataMap.put("image", getImageStr("D:\\logo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataMap;
    }
    
    public void exportDoc(String doc, Map<String, Object> map, String template) throws Exception {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(doc), encoding));
        getTemplate(template).process(map, writer);
    }
    
    public static void main(String[] args) throws Exception {
    	ExportDoc maker = new ExportDoc("UTF-8");
    	//maker.exportDoc("D:\\test13.doc", maker.getDataMap());
    }
}