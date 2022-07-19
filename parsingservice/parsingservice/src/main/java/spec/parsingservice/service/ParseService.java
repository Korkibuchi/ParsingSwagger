package spec.parsingservice.service;

import java.util.ArrayList;
import org.springframework.web.multipart.MultipartFile;

public interface ParseService {
    ArrayList parse(MultipartFile data);
}
