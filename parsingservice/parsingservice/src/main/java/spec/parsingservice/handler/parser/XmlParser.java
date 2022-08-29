
package spec.parsingservice.handler.parser;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spec.parsingservice.handler.context.Contextable;
import spec.parsingservice.service.impl.ParseServiceImpl;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service

public class XmlParser implements  Contextable, AutoCloseable {

    private XMLStreamReader reader;

    public static XmlParser newInstance(MultipartFile data){
        final XmlParser parser= new XmlParser();
        final XMLInputFactory FACTORY = XMLInputFactory.newInstance();
        try {
            parser.reader= FACTORY.createXMLStreamReader(data.getInputStream());
        } catch (XMLStreamException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return parser;}


    public List parse() {
        List lst = new ArrayList();
        try {
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
        
         
        } catch (XMLStreamException ex) {
            Logger.getLogger(ParseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
                
             
            
        
    }


    @Override
    public void close() throws Exception {
        if (reader != null){
            reader.close();
        }
    }
}
