package spec.parsingservice.protocol;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ParseResponse {
   private List lst;
   private HttpStatus stt;
   
}
