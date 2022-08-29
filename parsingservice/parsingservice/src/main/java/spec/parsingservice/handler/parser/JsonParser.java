package spec.parsingservice.handler.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class JsonParser implements ParserInt, AutoCloseable{
    com.fasterxml.jackson.core.JsonParser jParser;
    public static JsonParser newInstance(MultipartFile data){
        final JsonParser parser = new JsonParser();
        final JsonFactory factory = new JsonFactory();
        try {
            parser.jParser = factory.createParser(data.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return parser;
    }
    @Override
    public List parse(){
        List list = new ArrayList();
        try {
            StringBuilder str = new StringBuilder();
            jParser.nextToken();
            while(jParser.hasCurrentToken()) {
                jParser.nextToken();
             
                if(jParser.currentToken() == JsonToken.FIELD_NAME){
                    str = new StringBuilder(jParser.getText() + ":");
                     
                }
               
                else if (jParser.currentToken() == JsonToken.VALUE_STRING){
                    str.append(jParser.getText());
                    list.add(str.toString());
                }    
              
            }
          
        } catch (IOException ex) {
            Logger.getLogger(JsonParser.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return list;
}

    @Override
    public void close() throws Exception {
        if (jParser != null){
            jParser.close();
        }
    }
}
    
    

