package com.scaler.TicTacToe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Move {
    private Cell cell;
    private Player player;
}
