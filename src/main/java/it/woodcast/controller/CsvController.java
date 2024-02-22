package it.woodcast.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import it.woodcast.logic.ResourcesFacade;
import it.woodcast.resources.Customer;
import it.woodcast.resources.csv.WorkingCalendarCsv;
import it.woodcast.services.ResourceServices;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/csv")
class CsvController {
    @Autowired
    ResourcesFacade resourcesFacade;

    @RequestMapping(path = "/employee", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file");
        }

        try {
            String fileName = file.getOriginalFilename();

            CsvToBean<WorkingCalendarCsv> csvToBean = new CsvToBeanBuilder(convertToReader(file))
                    .withType(WorkingCalendarCsv.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<WorkingCalendarCsv> workingCalendarCsvs = csvToBean.stream().toList();

            resourcesFacade.saveAllVacation(workingCalendarCsvs);
            return ResponseEntity.ok().body("File uploaded successfully: " + fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }

    private  Reader convertToReader(MultipartFile multipartFile) throws IOException {

        InputStreamReader inputStreamReader = new InputStreamReader(multipartFile.getInputStream());
        return new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return inputStreamReader.read(cbuf, off, len);
            }

            @Override
            public void close() throws IOException {
                inputStreamReader.close();
            }
        };
    }
}