package com.example.connectfour.controllers;

import com.example.connectfour.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class GameController {
    private GameService gameService;

    @GetMapping("/game")
    public String showField(Model model) {
        model.addAttribute("field", gameService.getField().getField());
        model.addAttribute("currentSide", gameService.getSide());
        return "game";
    }

    @GetMapping("/click_chip")
    public String clickChip(@RequestParam int y) {
        gameService.checkAndAddChip(y);
        gameService.changeSide();
        if(gameService.checkWin()) {
            return "win";
        }
        return "redirect:/game";
    }
}