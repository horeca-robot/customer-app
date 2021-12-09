package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.ITagService;
import edu.fontys.horecarobot.databaselibrary.models.Tag;
import edu.fontys.horecarobot.databaselibrary.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagService implements ITagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
