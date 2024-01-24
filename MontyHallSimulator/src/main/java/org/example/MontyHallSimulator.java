package org.example;
import org.apache.commons.lang3.RandomUtils;

import java.util.HashMap;
import java.util.Map;

public class MontyHallSimulator {

    public static void main(String[] args) {
        int numTests = 1000;
        Map<Integer, Boolean> results = new HashMap<>();

        for (int i = 1; i <= numTests; i++) {
            boolean win = simulateMontyHall();
            results.put(i, win);
        }

        // Вывести статистику по победам и поражениям
        int wins = (int) results.values().stream().filter(Boolean::valueOf).count();
        int losses = numTests - wins;
        System.out.println("Победы: " + wins);
        System.out.println("Поражения: " + losses);
    }

    private static boolean simulateMontyHall() {
        // Генерация случайного номера двери, за которой находится автомобиль
        int carDoor = RandomUtils.nextInt(1, 4);

        // Игрок выбирает дверь
        int playerChoice = RandomUtils.nextInt(1, 4);

        // Ведущий открывает одну из дверей с козлом
        int goatDoor = getGoatDoor(carDoor, playerChoice);

        // Игрок может остаться при своем выборе (stay) или сменить дверь (switch)
        // Давайте рассмотрим оба варианта и вернем результат (true - победа, false - поражение)
        return simulateStayStrategy(playerChoice, carDoor) || simulateSwitchStrategy(playerChoice, goatDoor);
    }

    private static int getGoatDoor(int carDoor, int playerChoice) {
        int goatDoor;
        do {
            goatDoor = RandomUtils.nextInt(1, 4);
        } while (goatDoor == carDoor || goatDoor == playerChoice);
        return goatDoor;
    }

    private static boolean simulateStayStrategy(int playerChoice, int carDoor) {
        return playerChoice == carDoor;
    }

    private static boolean simulateSwitchStrategy(int playerChoice, int goatDoor) {
        // Игрок меняет свой выбор на дверь, которую ведущий не открыл
        int newChoice;
        do {
            newChoice = RandomUtils.nextInt(1, 4);
        } while (newChoice == playerChoice || newChoice == goatDoor);
        return newChoice == carDoor;
    }
}
