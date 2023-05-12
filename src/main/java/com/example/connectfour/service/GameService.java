package com.example.connectfour.service;

import com.example.connectfour.data.Chip;
import com.example.connectfour.data.Field;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Data
@Service
public class GameService {
    @Getter
    private Field field = new Field();
    private String side = "red";

    @PostConstruct
    public void init() {
        field = new Field(fillFieldWithNulls());
    }

    private Chip[][] fillFieldWithNulls () {
        Chip[][] tempField = new Chip[7][6];
        for (int i = 0; i < tempField.length; i++) {
            for (int j = 0; j < tempField[i].length; j++) {
                tempField[i][j] = new Chip(i,j,null,0);
            }
        }
        return tempField;
    }

    public void changeSide() {
        if (side.equals("red")) side = "blue";
        else side = "red";
    }

    public void checkAndAddChip(int y) {
        int value;
        for (int x = 6; x >= 0; x--) {
            if (field.getField()[x][y].getSide() == null) {
                field.getField()[x][y].setSide(side);
                if(side.equals("red")) value = 1;
                else value = -1;
                field.getField()[x][y].setValue(value);
                break;
            }
        }
        System.out.println(side);
    }

    public boolean checkWin() {
        // Перевірка рядків
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 3; y++) {
                int sum = 0;
                for (int i = 0; i < 4; i++) {
                    sum += field.getField()[x][y+i].getValue();
                }
                if (Math.abs(sum) == 4) {
                    return true;
                }
            }
        }

        // Перевірка стовбців
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 4; x++) {
                int sum = 0;
                for (int i = 0; i < 4; i++) {
                    sum += field.getField()[x+i][y].getValue();
                }
                if (Math.abs(sum) == 4) {
                    return true;
                }
            }
        }

        // Перевірка діагоналі ліво-верх до право-низ
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                int sum = 0;
                for (int i = 0; i < 4; i++) {
                    sum += field.getField()[x+i][y+i].getValue();
                }
                if (Math.abs(sum) == 4) {
                    return true;
                }
            }
        }

        // Перевірка діагоналі ліво-низ до право-верх
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y < 6; y++) {
                int sum = 0;
                for (int i = 0; i < 4; i++) {
                    sum += field.getField()[x+i][y-i].getValue();
                }
                if (Math.abs(sum) == 4) {
                    return true;
                }
            }
        }
        return false;
    }
}