package com.octenweb.springbootmvcsecurity.util;


import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

@Component
public class LocalDateTimeEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("In localDateEditor ");
        System.out.print(text);
        String[] date=(text.split("-"));
        LocalDate localDate=LocalDate.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
        setValue(localDate);
    }
}
