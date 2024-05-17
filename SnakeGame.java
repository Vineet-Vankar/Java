import java.util.ArrayList; // Importing ArrayList class
import java.util.List; // Importing List interface
import java.util.Random; // Importing Random class

import javafx.animation.AnimationTimer; // Importing AnimationTimer class for frame updates
import javafx.application.Application; // Importing Application class for JavaFX applications
import javafx.stage.Stage; // Importing Stage class for JavaFX window
import javafx.scene.Scene; // Importing Scene class for JavaFX scenes
import javafx.scene.canvas.Canvas; // Importing Canvas class for drawing
import javafx.scene.canvas.GraphicsContext; // Importing GraphicsContext class for drawing on canvas
import javafx.scene.input.KeyCode; // Importing KeyCode class for key codes
import javafx.scene.input.KeyEvent; // Importing KeyEvent class for key events
import javafx.scene.layout.VBox; // Importing VBox layout
import javafx.scene.paint.Color; // Importing Color class for colors
import javafx.scene.text.Font; // Importing Font class for text fonts

public class SnakeGame extends Application { // Main class extending Application

	// Game variables
	static int speed = 5; // Initial game speed
	static int foodcolor = 0; // Food color index
	static int width = 20; // Width of the game grid
	static int height = 20; // Height of the game grid
	static int foodX = 0; // X coordinate of the food
	static int foodY = 0; // Y coordinate of the food
	static int cornersize = 25; // Size of each grid square
	static List<Corner> snake = new ArrayList<>(); // List to store snake body parts
	static Dir direction = Dir.left; // Initial direction of the snake
	static boolean gameOver = false; // Game over flag
	static Random rand = new Random(); // Random number generator

	// Enum to define direction constants
	public enum Dir {
		left, right, up, down
	}

	// Class to define a point (corner) in the grid
	public static class Corner {
		int x; // X coordinate
		int y; // Y coordinate

		// Constructor to initialize coordinates
		public Corner(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// Start method - entry point for the JavaFX application
	public void start(Stage primaryStage) {
		try {
			newFood(); // Generate the first food

			VBox root = new VBox(); // Create a VBox layout
			Canvas c = new Canvas(width * cornersize, height * cornersize); // Create a canvas for drawing
			GraphicsContext gc = c.getGraphicsContext2D(); // Get the graphics context for the canvas
			root.getChildren().add(c); // Add the canvas to the layout

			// Create and start an animation timer for game updates
			new AnimationTimer() {
				long lastTick = 0; // Variable to track the last update time

				public void handle(long now) { // Handle method called every frame
					if (lastTick == 0) { // First frame initialization
						lastTick = now;
						tick(gc); // Update game state
						return;
					}

					// Update game state based on the speed
					if (now - lastTick > 1000000000 / speed) {
						lastTick = now;
						tick(gc); // Update game state
					}
				}
			}.start();

			Scene scene = new Scene(root, width * cornersize, height * cornersize); // Create the scene

			// Handle key press events for controlling the snake
			scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
				if (key.getCode() == KeyCode.W) {
					direction = Dir.up;
				}
				if (key.getCode() == KeyCode.A) {
					direction = Dir.left;
				}
				if (key.getCode() == KeyCode.S) {
					direction = Dir.down;
				}
				if (key.getCode() == KeyCode.D) {
					direction = Dir.right;
				}
			});

			// Add initial snake parts
			snake.add(new Corner(width / 2, height / 2));
			snake.add(new Corner(width / 2, height / 2));
			snake.add(new Corner(width / 2, height / 2));
			
			// Apply CSS stylesheet (if needed)
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// Set up the stage
			primaryStage.setScene(scene); // Set the scene
			primaryStage.setTitle("SNAKE GAME"); // Set the window title
			primaryStage.show(); // Show the stage
		} catch (Exception e) { // Catch and print any exceptions
			e.printStackTrace();
		}
	}

	// Method to update the game state
	public static void tick(GraphicsContext gc) {
		if (gameOver) { // Check if game is over
			gc.setFill(Color.RED); // Set color to red
			gc.setFont(new Font("", 50)); // Set font size
			gc.fillText("GAME OVER", 100, 250); // Display game over text
			return; // Exit the method
		}

		// Move the snake
		for (int i = snake.size() - 1; i >= 1; i--) {
			snake.get(i).x = snake.get(i - 1).x; // Move body part to the position of the previous part
			snake.get(i).y = snake.get(i - 1).y; // Move body part to the position of the previous part
		}

		// Update the head position based on the direction
		switch (direction) {
		case up:
			snake.get(0).y--;
			if (snake.get(0).y < 0) { // Check for collision with the top wall
				gameOver = true;
			}
			break;
		case down:
			snake.get(0).y++;
			if (snake.get(0).y >= height) { // Check for collision with the bottom wall
				gameOver = true;
			}
			break;
		case left:
			snake.get(0).x--;
			if (snake.get(0).x < 0) { // Check for collision with the left wall
				gameOver = true;
			}
			break;
		case right:
			snake.get(0).x++;
			if (snake.get(0).x >= width) { // Check for collision with the right wall
				gameOver = true;
			}
			break;
		}

		// Check if the snake eats the food
		if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
			snake.add(new Corner(-1, -1)); // Add a new part to the snake
			newFood(); // Generate new food
		}

		// Check for collision with itself
		for (int i = 1; i < snake.size(); i++) {
			if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
				gameOver = true; // Game over if head collides with body
			}
		}

		// Draw the background
		gc.setFill(Color.BLACK); // Set color to black
		gc.fillRect(0, 0, width * cornersize, height * cornersize); // Fill the background

		// Draw the score
		gc.setFill(Color.WHITE); // Set color to white
		gc.setFont(new Font("", 30)); // Set font size
		gc.fillText("Score: " + (speed - 6), 10, 30); // Display score

		// Choose a random color for the food
		Color cc = Color.WHITE;

		switch (foodcolor) {
		case 0:
			cc = Color.PURPLE;
			break;
		case 1:
			cc = Color.LIGHTBLUE;
			break;
		case 2:
			cc = Color.YELLOW;
			break;
		case 3:
			cc = Color.PINK;
			break;
		case 4:
			cc = Color.ORANGE;
			break;
		}
		gc.setFill(cc); // Set food color
		gc.fillOval(foodX * cornersize, foodY * cornersize, cornersize, cornersize); // Draw the food

		// Draw the snake
		for (Corner c : snake) {
			gc.setFill(Color.LIGHTGREEN); // Set color for snake body
			gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 1, cornersize - 1); // Draw snake body part
			gc.setFill(Color.GREEN); // Set color for snake border
			gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 2, cornersize - 2); // Draw snake body border
		}
	}

	// Method to generate new food
	public static void newFood() {
		start: while (true) {
			foodX = rand.nextInt(width); // Generate random X position for food
			foodY = rand.nextInt(height); // Generate random Y position for food

			for (Corner c : snake) { // Check if the food is on the snake
				if (c.x == foodX && c.y == foodY) {
					continue start; // Generate new coordinates if food is on the snake
				}
			}
			foodcolor = rand.nextInt(5); // Choose a random color for the food
			speed++; // Increase the speed
			break; // Exit the loop
		}
	}

	// Main method to launch the application
	public static void main(String[] args) {
		launch(args); // Launch the JavaFX application
	}
}
