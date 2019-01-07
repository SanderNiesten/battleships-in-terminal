import java.util.Scanner; // import Scanner for collecting user input
import java.util.Random;  // import Random for NextInt() to let the computer choose 5 location on the matrix

public class Main {

    public static class Battleship {

        //create 10x10 playing field
        private static int[][] grid = new int[10][10];

        public static void main(String[] args) {
            displayGrid();
            userShips();
            computerShips();
            shootOnTheGrid();
        }

        /*
        Display the playing field where with the usage of numbers certain positions are shown on the board.
        Shows placement of player ships '@', shots of player 'P', shots of computer 'C', hits of players 'X', hits of computer '!', and '?' for testing
        the position of the placement of the computer ships.
         */
        private static void displayGrid() {
            System.out.println("@ = P-Ships | P = P-Shots | C = C-Shots | X = C-Ships sunk | ! = P-Ships sunk");
            System.out.println();
            System.out.println("      0 1 2 3 4 5 6 7 8 9");

            for (int row = 0; row < grid.length; row++) {
                System.out.print("  " + row + " | ");
                for (int col = 0; col < grid[row].length; col++) {
                    if (grid[row][col] == 1) {
                        System.out.print("@ ");
                    } /*else if (grid[row][col] == 2) {
	                	System.out.print("? ");
	                }*/ else if (grid[row][col] == 3) {
                        System.out.print("P ");
                    } else if (grid[row][col] == 4) {
                        System.out.print("C ");
                    } else if (grid[row][col] == 5) {
                        System.out.print("X ");
                    } else if (grid[row][col] == 6) {
                        System.out.print("! ");
                    } else {
                        System.out.print("  ");
                    }
                }
                System.out.println("| " + row);
            }
            System.out.println("      0 1 2 3 4 5 6 7 8 9 ");
            System.out.println();
        }

        //take input of the player for placing five ships, and checks the input for not accepted input or double placement on the same spot.
        private static void userShips() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Place your ships!");
            int shipNumber = 1;

            for (int i = 0; i < 5; i++) {
                try {
                    System.out.print("Enter a number between 0 and 9 for the X-axis for ship " + shipNumber + ": ");
                    int userX = scanner.nextInt();
                    if(userX > 9 || userX < 0)
                        throw new Exception();

                    System.out.print("Enter a number between 0 and 9 for the Y-axis for ship " + shipNumber + ": ");
                    int userY = scanner.nextInt();
                    if(userY > 9 || userY < 0)
                        throw new Exception();
                    System.out.println("------------------------------------------------------------------------------");

                    if (grid[userY][userX] != 1) {
                        grid[userY][userX] = 1;
                        shipNumber++;
                    } else {
                        System.out.println("******************************************************************************");
                        System.out.println("You already placed a ship on these coordinates.");
                        System.out.println("******************************************************************************");

                        i--;
                    }
                }
                catch(Exception e) {
                    System.out.println("Error, wrong input!");
                    scanner.nextLine();
                    i--;
                }

            }
        }

        //places 5 computer ships on random spots and makes sure the computer can't place ships on the same spot
        private static void computerShips() {
            System.out.println();
            for (int i = 0; i < 5; i++) {
                Random randomGenerator = new Random();
                int computerX = randomGenerator.nextInt(9);
                int computerY = randomGenerator.nextInt(9);

                if(grid[computerX][computerY] != 1 && grid[computerX][computerY] != 2 ) {
                    grid[computerX][computerY] = 2;
                } else {
                    i--;
                }
            }
            System.out.println("Computer has deployed his 5 ships.");
            System.out.println();
        }

        /*
         Takes x and y input to shoot on the grid. Shows messages for hit, mis or wrong input. Also makes the computer
         shoot on the grid, by randomizing it's x and y coordinates and makes sure the computer won't shoot at the same spot
         twice.
        */
        private static void shootOnTheGrid() {
            displayGrid();
            Scanner scanner = new Scanner(System.in);
            int shipsPlayer = 5;
            int shipsComputer = 5;
            boolean player = true;


            while (shipsPlayer > 0 && shipsComputer > 0) {
                if (player) {
                    for (int i = 0; i < 1; i++) {
                        try {
                            System.out.print("Enter a number between 0 and 9 on the X-axis to aim: ");
                            int userX = scanner.nextInt();
                            if(userX > 9 || userX < 0)
                                throw new Exception();

                            System.out.print("Enter a number between 0 and 9 on the Y-axis to aim and FIRE: ");
                            int userY = scanner.nextInt();
                            if(userY > 9 || userY < 0)
                                throw new Exception();
                            System.out.println("------------------------------------------------------------------------------");

                            if (grid[userY][userX] == 2) {
                                grid[userY][userX] = 5;
                                shipsComputer --;

                                System.out.println("                         _");
                                System.out.println("Hit, you sank a ship!   |_|_____                                               ");
                                System.out.println("The computer now has " + shipsComputer + "   \\\\___// 's left!");
                                System.out.println();
                                System.out.println("------------------------------------------------------------------------------");
                            } else if (grid[userY][userX] == 1) {
                                System.out.println("You can't shoot your own ship. Aim better!");
                                throw new Exception();
                            } else {
                                grid[userY][userX] = 3;
                                System.out.println("You missed!");
                                System.out.println("------------------------------------------------------------------------------");
                            }
                        }
                        catch(Exception e) {
                            scanner.nextLine();
                            System.out.println("Error, wrong input!");
                            i--;
                        }
                    }
                } else {
                    for(int i = 0; i < 1; i++) {
                        Random randomGenerator = new Random();
                        int computerX = randomGenerator.nextInt(9);
                        int computerY = randomGenerator.nextInt(9);

                        if(grid[computerY][computerX] == 1) {
                            grid[computerY][computerX] = 6;
                            shipsPlayer --;
                            System.out.println();
                            System.out.println("The computer fired at X-coordinate: '" + computerX + "' and Y-coordinate: '"+ computerY);
                            System.out.println("The computer sank your ship. You have " + shipsPlayer + " ships left.");
                            System.out.println("   *__");
                            System.out.println("    \\ \\___ ");
                            System.out.println("  *  \\_\\__\\* ");
                            System.out.println("   ˜˜˜     ˜˜˜    ");

                            System.out.println("------------------------------------------------------------------------------");
                        } else if (grid[computerY][computerX] == 2 || grid[computerY][computerX] == 4){
                            i--;
                        } else {
                            System.out.println("The computer fired at X-coordinate: '" + computerX + "' and Y-coordinate: '"+ computerY +  "' and missed.");
                            System.out.println("------------------------------------------------------------------------------");
                            grid[computerY][computerX] = 4;
                        }
                    }
                    displayGrid();
                }
                player = !player;
            }


            //checks if computer or the player won and shows corresponding text.
            if (shipsComputer == 0) {
                System.out.println("Victory, you beat the computer!");
            } else {
                System.out.println("You lost, better luck next time!");
            }
        }
    }

}
