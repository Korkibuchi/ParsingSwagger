package spec.parsingservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ParseService {
    List parse(MultipartFile data);
}
