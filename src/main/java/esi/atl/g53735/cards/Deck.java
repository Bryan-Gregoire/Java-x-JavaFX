package esi.atl.g53735.cards;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Utilisateur
 */
public class Deck {

    private ArrayList<Card> list;

    public Deck() {
        this.list = new ArrayList<>();
        for (Color color : Color.values()) {
            for (Value value : Value.values()) {
                this.list.add(new Card(color, value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(list);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void hit() {
        int choosenCard = (int) (Math.random() * ((list.size())));
        list.remove(list.get(choosenCard));
    }

    public int size() {
        return this.list.size();
    }

    public String toString() {
        return list.toString();
    }
}