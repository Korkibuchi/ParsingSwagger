package spec.parsingservice.controller;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import spec.parsingservice.protocol.ParseResponse;
import spec.parsingservice.service.ParseService;

@Slf4j
@RestController
@RequestMapping("/api/parser")
final class ParserController {
    private final ParseService parseService;

    @Autowired
    ParserController(ParseService parseService) {
        this.parseService = parseService;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity parse(final @RequestParam("data") MultipartFile data) {
        log.info("module=ParserController, method=parse, action=POST, uri=/api/parser, data.Name={}, data.Size={}",
                data.getName(), data.getSize());

        try {
            ParseResponse resp = new ParseResponse();
            resp.setLst(parseService.parse(data));
            resp.setStt(HttpStatus.OK);
           
            return  new ResponseEntity<>(resp.getLst(), resp.getStt());
        } catch(Exception e) {
            log.error("module=ParserController, method=parse, action=POST, uri=/api/parser, error.Message={}",
                    e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
