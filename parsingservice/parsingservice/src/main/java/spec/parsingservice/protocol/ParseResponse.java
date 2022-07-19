package spec.parsingservice.protocol;

import java.util.ArrayList;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ParseResponse {
   private ArrayList<String> lst;
   private HttpStatus stt;
   
}
