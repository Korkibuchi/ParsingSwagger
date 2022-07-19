package spec.parsingservice.handler.parser;

import java.io.IOException;
import spec.parsingservice.handler.context.Contextable;
import spec.parsingservice.provider.xml.StaxProvider;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import spec.parsingservice.handler.SourceType;
import spec.parsingservice.service.impl.ParseServiceImpl;


@Repository

public class XmlParser implements  Contextable, ParserInt {

    @Override
    public ArrayList<String> parse(MultipartFile data, Enum<SourceType> type) {
        ArrayList<String> lst = new ArrayList();          
        try {
            final StaxProvider proXml = new StaxProvider(data.getInputStream());
            final XMLStreamReader reader = proXml.getReader();
            
            Stack<String> st = new Stack<>();
        boolean flag = false, flagtext= false;
        
        StringBuilder attString, finString;
        int evt, att;
        while (reader.hasNext()) {
            
            evt = reader.next();
            
            if (evt == XMLEvent.START_ELEMENT ){
                   att = reader.getAttributeCount();
                   if (att>0){
                       attString= new StringBuilder('/'+ reader.getName().getLocalPart());
                       attString.append('[');
                       for (int i = 0; i < att; i++) {
                           attString.append(reader.getAttributeName(i)+":"+reader.getAttributeValue(i));
                           if (i+1!=att)attString.append(',');
                       }
                       attString.append(']');
                       st.push(attString.toString());
                   }      
                   else st.push("/"+reader.getName().getLocalPart());
                   flag=true;
            }
            if (evt == XMLEvent.END_ELEMENT) {
                    st.pop();
                    if (!flag)lst.add("-----");
                    flagtext=false;
                    flag = false;
                }
            if (reader.hasText() && reader.getText().trim().length() > 0 && evt != XMLEvent.COMMENT) {
                    finString = new StringBuilder();
                    if (!flagtext) {for (String string : st) {
                        finString.append(string);}
                        finString.append(':');
                }
                       finString.append(reader.getText().trim());
                    lst.add(finString.toString());
                    flagtext= true;
                }
            }   
        
         
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(ParseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
                
             
            
        
    }

    

    

    
}
