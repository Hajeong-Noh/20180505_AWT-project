package com.polimi.awt.controller;

import com.polimi.awt.repository.AnnotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AnnotationController {
    @Autowired
    private AnnotationRepository annotationRepository;
}
