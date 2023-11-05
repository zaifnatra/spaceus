package main;

import java.util.Random;
import java.util.Stack;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class mapgenerator {
    public static void main(String[] args) {

        int columns = 40; // change to make variable size
        int rows = 20; // change to make variable size
        int locationX = 0; // current pos on x axis
        int locationY = 0; // current pos on y axis
        int randomMove; // chooses where to move to create solution path
        int randomFork1, randomFork2, randomForkL, randomDir;
        int[] position = new int[2]; // where the guy is at
        int count = 0; // how many mess ups
        Stack<int[]> path = new Stack<int[]>(); // keep track of where guy has been
        int nextTo1 = 0;
        int nextTo2 = 0;
        int previousMove = 2;

        int[][] maze = new int[rows][columns]; // initialize empty arrays
        Random rand = new Random();

        for (int a = 0; a < rows; a++) {
            maze[a][0] = 2;

        }

        for (int a = 0; a < columns; a++) {
            maze[0][a] = 2;
            maze[rows - 1][a] = 2;
        }

        int start = rows / 2;
        maze[start][0] = 1;
        maze[start][1] = 1;

        locationY = start;
        locationX = 0;

        maze[locationY][locationX] = 1; // first step
        locationX = 1;

        // maze[locationY-1][0] = 2;
        // maze[locationY+1][0] = 2;

        position[0] = locationY;
        position[1] = locationX;

        while (locationX != columns - 1 && count < 1000) {
            position[0] = locationY; // old position y
            position[1] = locationX; // old position x

            randomMove = rand.nextInt(3);
            if (randomMove == previousMove) {// skip
            }
            // else if(randomMove == 0) { //moves backwards
            // locationX--;
            // nextTo1 = maze[locationY-1][locationX]; //above
            // nextTo2 = maze[locationY+1][locationX]; //below

            else if (randomMove == 1) {// moves up
                locationY--;
                nextTo1 = maze[locationY][locationX - 1]; // left
                nextTo2 = maze[locationY][locationX + 1]; // right
            } else if (randomMove == 2) {// moves forwards
                locationX++;
                nextTo1 = maze[locationY - 1][locationX]; // above
                nextTo2 = maze[locationY + 1][locationX]; // below
            } else {// moves down
                locationY++;
                nextTo1 = maze[locationY][locationX - 1];
                nextTo2 = maze[locationY][locationX + 1];
            }

            if (randomMove != previousMove) {

                if (locationY >= rows || locationY < 0 ||
                        locationX >= columns || locationX < 0 ||
                        maze[locationY][locationX] == 1 || // it's already a 1
                        maze[locationY][locationX] == 2 || // it's a wall
                        nextTo1 == 1 ||
                        nextTo2 == 1) {

                    locationX = position[1]; // goback
                    locationY = position[0]; // go back
                    count++;
                } else {
                    maze[locationY][locationX] = 1;

                    path.push(new int[] { locationY, locationX }); // puts it into stack
                }
            }

            previousMove = randomMove;

        }

        for (int c = 0; c < 1000; c++) { // loop to alternate generating a wall and a path

            randomFork1 = rand.nextInt(rows - 2) + 1;
            randomFork2 = rand.nextInt(columns - 2) + 1;
            randomForkL = 1 + rand.nextInt(15);

            for (int j = 0; j <= randomForkL; j++) { // generate random walls
                if (randomFork1 < rows && randomFork1 >= 0 && randomFork2 < columns && randomFork2 >= 0) {
                    if (maze[randomFork1][randomFork2] == 0)
                        maze[randomFork1][randomFork2] = 2;
                }
                randomDir = rand.nextInt(2);
                if (randomDir == 1)
                    randomFork1++;
                else
                    randomFork2++;

            }

            randomFork1 = rand.nextInt(rows - 2) + 1;
            randomFork2 = rand.nextInt(columns - 2) + 1;
            randomForkL = 1 + rand.nextInt(15);

            for (int k = 0; k <= randomForkL; k++) { // generates random paths
                if (randomFork1 < rows && randomFork1 >= 0 && randomFork2 < columns && randomFork2 >= 0) {
                    if (maze[randomFork1][randomFork2] == 0)
                        maze[randomFork1][randomFork2] = 1;
                }
                randomDir = rand.nextInt(2);
                if (randomDir == 1)
                    randomFork1++;
                else
                    randomFork2++;

            }

        }
        for (int z1 = 0; z1 < rows; z1++) {
            if (maze[z1][columns - 1] == 0)
                maze[z1][columns - 1] = 2;
        }

        for (int z = 0; z < rows; z++) {
            for (int y = 0; y < columns; y++) {
                if (maze[z][y] == 1) {
                    System.out.print(rand.nextInt(3) + " "); // ' ' for paths
                } else if (maze[z][y] == 2) {
                    System.out.print((rand.nextInt(3) + 4) + " "); // '#' for walls
                } else {
                    System.out.print(rand.nextInt(3) + " "); // 'O' for undefined
                }
            }
            System.out.println(" "); // skip line
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                "map1.txt"))) {
            for (int z = 0; z < rows; z++) {
                for (int y = 0; y < columns; y++) {
                    if (maze[z][y] == 1) {
                        writer.write(rand.nextInt(3) + " ");
                    } else if (maze[z][y] == 2) {
                        writer.write((rand.nextInt(3) + 4) + " ");
                    } else {
                        writer.write(rand.nextInt(3) + " ");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
