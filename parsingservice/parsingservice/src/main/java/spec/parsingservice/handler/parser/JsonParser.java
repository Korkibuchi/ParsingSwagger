package spec.parsingservice.handler.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import spec.parsingservice.handler.SourceType;



@Repository

public class JsonParser implements ParserInt{
  
    @Override
    public ArrayList<String> parse(MultipartFile data, Enum<SourceType> type){
        ArrayList list = new ArrayList();
        try {      
            com.fasterxml.jackson.core.JsonParser jParser = new JsonFactory()
                    .createParser(data.getInputStream());
            
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

  
    }
    
    

