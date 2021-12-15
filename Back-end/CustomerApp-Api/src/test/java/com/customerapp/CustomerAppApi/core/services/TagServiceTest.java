package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.MockFactory;
import edu.fontys.horecarobot.databaselibrary.repositories.TagRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceTest {

    @InjectMocks
    private TagService tagService;

    @Mock
    private TagRepository tagRepository;

    private final MockFactory mockFactory = new MockFactory();

    @BeforeEach
    public void setUp() {
        tagService = new TagService(tagRepository);
        MockitoAnnotations.openMocks(tagService);
    }

    @org.junit.Test
    public void getAllTags() {
        var mockTags = mockFactory.getMockTags();

        when(tagRepository.findAll()).thenReturn(mockTags);

        var actual = tagService.getAllTags();

        Assertions.assertEquals(mockTags, actual);
    }
}