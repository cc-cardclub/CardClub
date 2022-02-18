package at.rennweg.htl.cardclubclient.cards;

public class Card {
    private int number;
    private String color;
    private String texture;

    public Card(int number, String color) {
        if ((number < 0) || (number > 9)) {
            throw new IllegalArgumentException("Number must be between 0 and 9");
        }

        if (!color.equals("red") && !color.equals("green")
                && !color.equals("blue") && !color.equals("yellow")) {
            throw new IllegalArgumentException("Color must be red, green, blue or yellow");
        }

        this.number = number;
        this.color = color;
        // this.texture = "img/" + color + "_" + number + ".png";
        this.texture = "img/cardClubCard.png";
    }

    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public String getTexture() {
        return texture;
    }
}
