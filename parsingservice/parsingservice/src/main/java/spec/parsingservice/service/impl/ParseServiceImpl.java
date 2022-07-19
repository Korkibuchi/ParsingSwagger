package spec.parsingservice.service.impl;
import com.fasterxml.jackson.core.JsonParser;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spec.parsingservice.handler.SourceType;
import spec.parsingservice.handler.parser.XmlParser;
import spec.parsingservice.handler.parser.ParserInt;
import spec.parsingservice.service.ParseService;


@Service
public class ParseServiceImpl  implements ParseService{
  
    

    @Override
    public ArrayList parse(MultipartFile data) {
        
        if (data.getContentType().contains("xml"))
            return selectParser(data, SourceType.XML);
        
        else if (data.getContentType().contains("json"))
            return selectParser(data, SourceType.JSON);    
        
        return null;
    }
    
    public ArrayList selectParser(MultipartFile data ,Enum<SourceType> type){
        if (type == SourceType.XML){
        ParserInt xml = new XmlParser();
        ArrayList lst = xml.parse(data, SourceType.XML);
        return lst;        
        }
        if (type == SourceType.JSON) {
        ParserInt json = new spec.parsingservice.handler.parser.JsonParser();
        ArrayList lst = json.parse(data, SourceType.JSON);
                
            
            return lst;
        }
        return null;
    }
}
