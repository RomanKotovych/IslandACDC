package com.javarush.island.kotovych.controllers;

import com.javarush.island.kotovych.organisms.animals.Animal;
import com.javarush.island.kotovych.game.GameScene;
import com.javarush.island.kotovych.game.Square;

import java.util.Arrays;

public class ReproductionController implements Controller{

    GameScene gameScene;

    public ReproductionController(GameScene gameScene){
        this.gameScene = gameScene;
    }

    @Override
    public void execute() {
        for(int y = 0; y < gameScene.getField().length; y++){
            Square[] col = gameScene.getField()[y];
            Arrays.stream(col)
                    .parallel()
                    .forEach(square -> {
                        square.getOrganismList().parallelStream()
                                .forEach(i -> {
                                    if(i instanceof Animal) {
                                        ((Animal) i).reproduce(square);
                                    }
                                });
                    });
        }
    }

    @Override
    public void run() {
        Thread.currentThread().setName(this.getClass().getSimpleName());
        execute();
    }
}
