/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spec.parsingservice.handler.parser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import spec.parsingservice.handler.SourceType;

public interface ParserInt {
    List<String> parse();
}
