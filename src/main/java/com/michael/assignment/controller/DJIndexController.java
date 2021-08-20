package com.michael.assignment.controller;

import com.michael.assignment.entity.DJIndex;
import com.michael.assignment.service.DJIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * @author michaelwang on 2021-08-18
 */
@Controller
public class DJIndexController {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DJIndexService djIndexService;

    @Autowired
    public void setDjIndexService(DJIndexService djIndexService) {
        this.djIndexService = djIndexService;
    }

    @RequestMapping("/djindex/upload")
    public String uploadPage() {
        return "uploadpage";
    }

    @RequestMapping(value = "/djindex/uploaddata", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadData(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please choose a file.");
            logger.info("Empty File.");
            return "uploadpage";
        }
        try {
            InputStream inputStream = file.getInputStream();
            new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .skip(1)
                    .forEach(l -> djIndexService.save(djIndexService.convertToDJIndex(l)));
            model.addAttribute("message", "Data upload is successful.");
            logger.info("Upload Successful.");
        } catch (Exception e) {
            model.addAttribute("message", "Please choose a file with correct format.");
            logger.info("Upload NOT Successful.");
            e.printStackTrace();
        }
        return "uploadpage";
    }


    @RequestMapping("/djindex/query")
    public String queryStock(DJIndex djIndex, Model model) {
        if (djIndex.getStock().isBlank()) {
            model.addAttribute("Indices", djIndexService.getAllStocks());
            model.addAttribute("message", "All the Indices:");
        } else {
            model.addAttribute("message", "Stock " + djIndex.getStock() + "'s Indices:");
            model.addAttribute("Indices", djIndexService.getIndicesByStock(djIndex.getStock()));
        }
        return "indices";
    }

    @GetMapping("/djindex/list")
    public String getAllStocks(Model model) {
        model.addAttribute("message", "All the Indices:");
        model.addAttribute("Indices", djIndexService.getAllStocks());
        return "indices";
    }

    @RequestMapping("/djindex/add")
   public String addPage(Model model){
        model.addAttribute("djIndex", new DJIndex());
        return "index";
   }


    @PostMapping("/djindex/save")
    public String addDJIndex(DJIndex djIndex, Model model) {
        try{
            djIndexService.save(djIndex);
            model.addAttribute("message", "The Index has been saved successfully.");
        }catch (IllegalArgumentException e){
            model.addAttribute("message", "Please check the data type.");
        }
        return "index";
    }

}
