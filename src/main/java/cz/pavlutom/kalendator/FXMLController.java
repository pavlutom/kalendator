package cz.pavlutom.kalendator;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class FXMLController {

    private final String[] result_pool = new String[]{
            "ljkro kruva",
            "bzzt bzzt",
            "ji ko zitra kaclu",
            "projek management",
            "ja se vam jen snaizm prdat dobry pristup k oprojektu"
    };

    @FXML
    private Label result;

    @FXML
    private TextInputControl expression;

    public void initialize() {
        result.setText("");
    }

    public void kakulate() {
        String input = expression.getText();

        AtomicInteger percent = new AtomicInteger();

        class PartialKakulator extends Task<Void> {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    result.setText("Kakulation failed");
                }
                return null;
            }

            @Override
            protected void succeeded() {
                if (percent.getAndIncrement() < 100) {
                    result.setText("Kakulating results for '%s' - %d %%".formatted(input, percent.get()));
                    new Thread(new PartialKakulator()).start();
                } else {
                    int kakulated_index = new Random().nextInt(result_pool.length);
                    String kakulated_result = result_pool[kakulated_index];

                    result.setText(kakulated_result);
                }
            }
        }

        new Thread(new PartialKakulator()).start();
    }
}