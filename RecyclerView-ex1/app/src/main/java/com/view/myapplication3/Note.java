package com.view.myapplication3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    private Integer id;
    private String title;
    private String subTitle;
    private String min;
}
