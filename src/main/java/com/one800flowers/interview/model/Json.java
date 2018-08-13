package com.one800flowers.interview.model;


import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Json {
    private int id;
    private int userId;
    private String title;
    private String body;
}
