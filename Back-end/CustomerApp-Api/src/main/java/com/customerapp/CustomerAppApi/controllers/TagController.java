package com.customerapp.CustomerAppApi.controllers;

import com.customerapp.CustomerAppApi.core.interfaces.ITagService;
import edu.fontys.horecarobot.databaselibrary.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag/")
public class TagController {

    private final ITagService tagService;

    @Autowired
    public TagController(ITagService tagService) {
        this.tagService = tagService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("all")
    public List<Tag> getAllTables()
    {
        return tagService.getAllTags();
    }
}
