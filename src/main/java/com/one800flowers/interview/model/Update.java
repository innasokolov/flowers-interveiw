package com.one800flowers.interview.model;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Update {
    private int element;
    private String title;
    private String body;
}
