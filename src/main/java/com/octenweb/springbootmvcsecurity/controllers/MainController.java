package com.octenweb.springbootmvcsecurity.controllers;

import com.octenweb.springbootmvcsecurity.dao.DocumentDAO;
import com.octenweb.springbootmvcsecurity.entity.Document;
import com.octenweb.springbootmvcsecurity.entity.TransitionalDocument;
import com.octenweb.springbootmvcsecurity.util.LocalDateTimeEditor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;


@Controller
@AllArgsConstructor
public class MainController {
    private DocumentDAO documentDAO;
    //    @Autowired
    private LocalDateTimeEditor localDateTimeEditor;


    @GetMapping("/")
    public String home(Model model) {
//        model.addAttribute("xxx", "hi! man!");
        model.addAttribute("document", new TransitionalDocument());

        return "homeTemplate";
    }

    @InitBinder("transitionalDocument")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, localDateTimeEditor);

    }

    @PostMapping("/saveDocument")
    public String saveUser(TransitionalDocument transitionalDocument) throws IOException {

        System.out.println("I am here");
        System.out.println(transitionalDocument.getName());
        System.out.println(transitionalDocument.getDate());
        System.out.println(transitionalDocument.getType());
         Document document=new Document();
         document.setName(transitionalDocument.getName());
         document.setDate(transitionalDocument.getDate());
         document.setSize(transitionalDocument.getMultipartFile().getSize());
         document.setType(transitionalDocument.getType());
         //todo change to use .builder;

        String path = System.getProperty("user.home") + File.separator + "IdeaProjects" + File.separator + transitionalDocument.getMultipartFile().getOriginalFilename();
        transitionalDocument.getMultipartFile().transferTo(new File(path));


        documentDAO.save(document);
//        System.out.println(transitionalDocument.getMultipartFile().getSize());

//        File file = transitionalDocument.getMultipartFile();
//        System.out.println(file);
//        FileInputStream inputStream = new FileInputStream(file);
//
//
//        String name = file.getName();
//        System.out.println(name + "!!!!!!!!!");
//        FileOutputStream fileOutputStream = new FileOutputStream(new File(System.getProperty("user.home") + "/xxx/" + name));
//        IOUtils.copy(inputStream, fileOutputStream);

        return "next";
    }


    @GetMapping("/findDocumentByName")
    public String findDocumentByName(int id) {
        documentDAO.findById(id).toString();
        return "DASDA";
    }

    @GetMapping("/showById")
    public String showById(@PathVariable int id, Model model) {
        Document document = documentDAO.findById(id).get();
        model.addAttribute("idDocument", document);
        return "redirect:/";


//    @PostMapping("/editUser")
//    public String editDocument(Document document, Model model) {
//        System.out.println(document);
//        documentDAO.save(document);
//
//        model.addAttribute("someDocument", document);
//        List<Document> all = documentDAO.findAll();
//        model.addAttribute("documents", all);
//
//        return "next";

    }


}
