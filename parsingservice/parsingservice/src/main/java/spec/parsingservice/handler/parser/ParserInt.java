/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spec.parsingservice.handler.parser;

import java.util.ArrayList;
import org.springframework.web.multipart.MultipartFile;
import spec.parsingservice.handler.SourceType;

public interface ParserInt {
    public ArrayList<String> parse(MultipartFile data, Enum<SourceType> type);
}
