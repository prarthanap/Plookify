/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.player;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author prarthana
 */
public class Search {
    
    private void searchFunction(KeyEvent evt) {
       // System.out.println("hi");

        FilteredList<Tracks> filteredData = new FilteredList<>(data, e -> true);

        searchField.setOnKeyReleased(e -> {

            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Tracks>) tracks -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (tracks.getID().contains(newValue)) {
                        return true;
                    } else if (tracks.getTrackName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;

                    } else if (tracks.getArtist().toLowerCase().contains(lowerCaseFilter)) {
                        return true;

                    } else if (tracks.getGenre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;

                    }

                    return false;

                });
            });
        });

        SortedList<Tracks> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

    }
    
    
    
}
