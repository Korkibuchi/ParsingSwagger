/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spec.parsingservice.provider.xml;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author User
 */
@Slf4j
public class JackosonProvider implements AutoCloseable{
    
    
    private static final JsonFactory FACTORY = new JsonFactory();
    private final JsonParser jParser;

    public JackosonProvider(InputStream data) throws IOException {
        this.jParser = FACTORY.createParser(data);
        
    }

    public JsonParser getjParser() {
        return jParser;
    }
    
    
    
    
    
    @Override
    public void close() throws Exception {
       if (jParser != null){
           jParser.close();
       }
       
    }
    
}
