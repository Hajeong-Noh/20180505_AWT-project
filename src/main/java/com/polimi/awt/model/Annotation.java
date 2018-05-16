package com.polimi.awt.model;

import java.time.LocalDateTime;
import java.util.List;

public class Annotation {

    private Long id;
    private Peak peak;
    private LocalDateTime creationDate;
    private boolean isValid;
    private double elevation;
    private String name;
    private List <String> localizedPeakNames;
    private boolean isAcceptedByManager;

}
