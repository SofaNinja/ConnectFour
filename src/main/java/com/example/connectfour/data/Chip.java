package com.example.connectfour.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chip {
    private int x;
    private int y;
    private String side;
    private int value;
}