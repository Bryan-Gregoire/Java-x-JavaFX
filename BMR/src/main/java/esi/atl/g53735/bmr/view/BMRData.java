package esi.atl.g53735.bmr.view;

import esi.atl.g53735.bmr.model.ActivityLevel;
import esi.atl.g53735.bmr.model.Gender;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Represent the data.
 *
 * @author g53735
 */
public class BMRData extends GridPane {

    private TextField sizeField;
    private TextField weightField;
    private TextField ageField;
    private final ToggleGroup genderGroup;
    private final ChoiceBox<ActivityLevel> box;

    /**
     * Constructor of BMRData.
     *
     */
    public BMRData() {

        this.setHgap(12);
        this.setVgap(12);
        this.setPadding(new Insets(8));
        this.setAlignment(Pos.CENTER_LEFT);

        Text titleLeft = new Text("Données");
        titleLeft.setUnderline(true);
        titleLeft.setFill(Color.BLACK);
        titleLeft.setFont(Font.font("System", 12));
        GridPane.setConstraints(titleLeft, 0, 0);

        Label size = new Label("Taille (cm)");
        GridPane.setConstraints(size, 0, 1);

        Label weight = new Label("Poids (kg)");
        GridPane.setConstraints(weight, 0, 2);

        Label age = new Label("Age (années)");
        GridPane.setConstraints(age, 0, 3);

        Label gender = new Label("Sexe");
        GridPane.setConstraints(gender, 0, 4);

        Label lifeStyle = new Label("Style de vie");
        GridPane.setConstraints(lifeStyle, 0, 5);

        sizeField = new TextField();
        sizeField.setPromptText("Taille en cm");
        GridPane.setConstraints(sizeField, 1, 1);
        sizeField.addEventFilter(KeyEvent.KEY_TYPED,
                new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!event.getCharacter().matches("\\d")
                        || sizeField.getText().length() > 2) {
                    event.consume();

                }
            }
        });

        weightField = new TextField();
        weightField.setPromptText("Poids en kg");
        GridPane.setConstraints(weightField, 1, 2);
        weightField.addEventFilter(KeyEvent.KEY_TYPED,
                new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!event.getCharacter().matches("\\d")
                        || weightField.getText().length() > 2) {
                    event.consume();

                }
            }
        });

        ageField = new TextField();
        ageField.setPromptText("Age en années");
        GridPane.setConstraints(ageField, 1, 3);
        ageField.addEventFilter(KeyEvent.KEY_TYPED,
                new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!event.getCharacter().matches("\\d")
                        || ageField.getText().length() > 2) {
                    event.consume();

                }
            }
        });

        HBox sexBox = new HBox(10);
        GridPane.setConstraints(sexBox, 1, 4);

        genderGroup = new ToggleGroup();

        RadioButton female = new RadioButton(Gender.FEMME.toString());
        female.setUserData(Gender.FEMME);
        female.setToggleGroup(genderGroup);
        GridPane.setConstraints(female, 0, 0);

        RadioButton male = new RadioButton(Gender.HOMME.toString());
        male.setUserData(Gender.HOMME);
        male.setToggleGroup(genderGroup);
        GridPane.setConstraints(male, 1, 0);

        box = new ChoiceBox<>();
        box.getItems().addAll(ActivityLevel.values());
        box.setValue(ActivityLevel.ACTIF);
        GridPane.setConstraints(box, 1, 5);

        sexBox.getChildren().addAll(female, male);
        this.getChildren().addAll(titleLeft, size, weight, age, gender,
                lifeStyle, sizeField, weightField, ageField, sexBox, box);
    }

    /**
     * Get the height of the height field.
     *
     * @return the height.
     */
    public int getSize() {
        return Integer.parseInt(sizeField.getText());
    }

    /**
     * Get the weight of the weight field.
     *
     * @return the weight.
     */
    public int getWeight() {
        return Integer.parseInt(weightField.getText());
    }

    /**
     * Get the age of the age field.
     *
     * @return the age.
     */
    public int getAge() {
        return Integer.parseInt(ageField.getText());
    }

    /**
     * Get the selected level of activity.
     *
     * @return the level of activity.
     */
    public ActivityLevel getActivity() {
        return box.getValue();
    }

    /**
     * Get the selected gender.
     *
     * @return the gender.
     */
    public Gender getGender() {
        return (Gender) genderGroup.getSelectedToggle().getUserData();
    }

    /**
     * Check if a data is empty.
     *
     * @return True if is empty else false.
     */
    public boolean dataEmpty() {
        return sizeField.getText().isEmpty()
                || weightField.getText().isEmpty()
                || ageField.getText().isEmpty()
                || genderGroup.getSelectedToggle() == null;
    }

    /**
     * Check if the data is realistic.
     *
     * @param alert the given alert to show.
     * @return true if data is not realistic else false.
     */
    private boolean falseData(Alert alert) {
        int size = this.getSize();
        int weight = this.getWeight();
        int age = this.getAge();
        if (size == 0 || weight == 0 || age == 0) {
            alert.showAndWait();
            return true;
        }
        return size > 300 || weight > 500 || age > 150;
    }

    /**
     * Check data is valid.
     *
     * @param alert the given alert.
     *
     * @return true if it is not valid otherwise false.
     */
    public boolean notValidData(Alert alert) {
        return this.dataEmpty() || falseData(alert);
    }

    /**
     * Clear data.
     *
     */
    public void clearData() {
        sizeField.clear();
        weightField.clear();
        ageField.clear();
        genderGroup.selectToggle(null);
    }
}
