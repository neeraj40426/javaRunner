package com.example.javaquiz;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("topics")
    public List<TopicInfo> addTopicsToModel() {
        return List.of(
                new TopicInfo("2D Arrays","2d_arrays","#6f42c1", "🗂️"),
                new TopicInfo("Applets","applets", "#007bff", "🖥️"),
                new TopicInfo("Basic Programming Constructs","basic_programming_construct","#28a745", "⚙️"),
                new TopicInfo("Data Conversion","data_conversion","#fd7e14", "🔄"),
                new TopicInfo("Delegation Event Model","delegate_event_model","#20c997", "🎯"),
                new TopicInfo("Exception Handling","exception_handling","#dc3545", "⚠️"),
                new TopicInfo("Functions","functions","#6610f2", "🧩"),
                new TopicInfo("Graphical User Interface (AWT)","awt", "#17a2b8", "🖌️"),
                new TopicInfo("Inheritance","inheritance", "#ffc107", "🌳"),
                new TopicInfo("Input/Output","input_output", "#0d6efd", "📂"),
                new TopicInfo("Interfaces","interfaces", "#6c757d", "🔗"),
                new TopicInfo("Java Database Connectivity","java_database_connectivity", "#198754", "🗃️"),
                new TopicInfo("Multithreading","multithreading", "#fd7e14", "🔀"),
                new TopicInfo("Object Oriented Programming","object_oriented_programming", "#0dcaf0", "🏛️"),
                new TopicInfo("Packages","packages", "#6f42c1", "📦"),
                new TopicInfo("Single Dimension Arrays","1d_arrays", "#20c997", "📁"),
                new TopicInfo("Strings","strings", "#dc3545", "🔤")
        );
    }
}
