import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RecursiveCircles extends Application {
    private Pane centerPane;
    private TextField radiusField;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        
        // Center pane setup
        centerPane = new Pane();
        centerPane.setStyle("-fx-background-color: lightgray;");
        root.setCenter(centerPane);
        
        // Bottom pane setup
        HBox bottomPane = new HBox(10);
        Label label = new Label("Enter radius of largest circle:");
        radiusField = new TextField();
        Button startButton = new Button("Start");
        startButton.setOnAction(event -> makeCircles());
        bottomPane.getChildren().addAll(label, radiusField, startButton);
        root.setBottom(bottomPane);
        
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Recursive Circles");
        stage.setScene(scene);
        stage.show();
    }
    
    private void makeCircles() {
        centerPane.getChildren().clear(); // Clear previous circles
        try {
            double radius = Double.parseDouble(radiusField.getText().trim());
            double paneSize = Math.min(centerPane.getWidth(), centerPane.getHeight()) / 2;
            
            if (radius > paneSize) {
                showAlert("Circle too large to fit in the pane.");
            } else {
                displayCircles(centerPane.getWidth() / 2, centerPane.getHeight() / 2, radius, Color.RED);
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid number.");
        }
    }
    
    private void displayCircles(double x, double y, double radius, Color color) {
        if (radius <= 0) return; // Base case
        
        Circle circle = new Circle(x, y, radius);
        circle.setStroke(Color.BLACK);
        circle.setFill(color);
        centerPane.getChildren().add(circle);
        
        
        Color nextColor = color.equals(Color.RED) ? Color.BLUE : Color.RED;
        displayCircles(x, y, radius - 10, nextColor);
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch();
    }
}
