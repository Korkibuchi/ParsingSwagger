package spec.parsingservice.service.impl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spec.parsingservice.provider.xml.StaxProvider;
import spec.parsingservice.service.ParseService;


@Service
public class ParseServiceImpl implements ParseService {
    @Override
    public void parse(MultipartFile data) {
                  
        try {
            final StaxProvider proXml = new StaxProvider(data.getInputStream());
            final XMLStreamReader reader = proXml.getReader();
            System.out.println("hello parser1");
            int eventType = reader.getEventType();
            System.out.println(eventType);
            String s1=null, s2=null, s3=null, s4 = null;
          while (reader.hasNext()) {
              
              eventType = reader.next();
//              System.out.println(eventType);
              

            if (eventType == XMLEvent.START_ELEMENT && s1 == null) {
                s1 = reader.getName().getLocalPart();
                
                continue;}
                
            if (eventType == XMLEvent.START_ELEMENT && s1!=null && s2 == null) {
                s2 = reader.getName().getLocalPart();
                
               continue;}
            
            if (eventType == XMLEvent.START_ELEMENT && s2!=null & s3 == null) {
                s3 = reader.getName().getLocalPart();
                
                continue;
            }
            if (eventType == XMLEvent.CHARACTERS && s1 != null && s2 != null && s3 != null){
                s4 = reader.getText();
                System.out.println(s1+ "/"+ s2+"/" +s3+"/"+s4);
                s4=null;
                s3=null;
            }
//                switch (reader.getName().getLocalPart()) {
//
//                    case "staff":
//                        String id = reader.getAttributeValue(null, "id");
//                        System.out.printf("Staff id : %s%n", id);
//                        break;
//                     
//                     case "hello":
//                        eventType = reader.next();
//                        System.out.println(eventType);
//                        if (eventType == XMLEvent.CHARACTERS) {
//                            System.out.printf("Hello : %s%n", reader.getText());
//                        }
//                        break;    
//
//                    case "name":
//                        eventType = reader.next();
//                        System.out.println(eventType);
//                        if (eventType == XMLEvent.CHARACTERS) {
//                            System.out.printf("Name : %s%n", reader.getText());
//                        }
//                        break;
//
//                    case "role":
//                        eventType = reader.next();
//                        System.out.println(eventType);
//                        if (eventType == XMLEvent.CHARACTERS) {
//                            System.out.printf("Role : %s%n", reader.getText());
//                        }
//                        break;
//
//                    case "salary":
//                        String currency = reader.getAttributeValue(null, "currency");
//                        eventType = reader.next();
//                        System.out.println(eventType);
//                        if (eventType == XMLEvent.CHARACTERS) {
//                            String salary = reader.getText();
//                            System.out.printf("Salary [Currency] : %,.2f [%s]%n",
//                              Float.parseFloat(salary), currency);
//                        }
//                        break;
//
//                    case "bio":
//                        eventType = reader.next();
//                        System.out.println(eventType);
//                        if (eventType == XMLEvent.CHARACTERS) {
//                            System.out.printf("Bio : %s%n", reader.getText());
//                        }
//                        break;
//                }

            

            if (eventType == XMLEvent.END_ELEMENT) {
                // if </staff>
                if (reader.getName().getLocalPart().equals("staff")) {
                    System.out.printf("%n%s%n%n", "---");
                }
            }

        }

                
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(ParseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
                
             
            
        
    }

   
    
}
