package spec.parsingservice.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spec.parsingservice.handler.SourceType;
import spec.parsingservice.handler.parser.JsonParser;
import spec.parsingservice.handler.parser.XmlParser;
import spec.parsingservice.service.ParseService;


@Service
public class ParseServiceImpl  implements ParseService{

    @Override
    public List parse(MultipartFile data) {
        
        if (data.getContentType().contains("xml"))
            return XmlParser.newInstance(data).parse();

        
        else if (data.getContentType().contains("json"))
            return JsonParser.newInstance(data).parse();


        
        return null;
    }

}
