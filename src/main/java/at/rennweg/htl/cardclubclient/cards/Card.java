package at.rennweg.htl.cardclubclient.cards;

import at.rennweg.htl.cardclubclient.logic.GameCore;

public class Card {
    private String number;
    private String color;
    private String texture;
    private boolean isSpecial;

    public Card(String number, String color, boolean isSpecial) {
        final boolean checkStandardColor = !color.equals("red") && !color.equals("green")
                && !color.equals("blue") && !color.equals("yellow");

        if (isSpecial) {
            if (!number.equals("draw2") && !number.equals("reverse")
                    && !number.equals("skip") && !number.equals("wild")
                    && !number.equals("wildDraw4") && !(GameCore.cardsSwitchingInPlayingDirectory && number.equals("1"))) {
                throw new IllegalArgumentException("Number must be draw2, reverse, skip, "
                        + "wild or wildDraw4");
            }

            if (number.equals("wild") || number.equals("wildDraw4")) {
                if (!color.equals("black")) {
                    throw new IllegalArgumentException("Color must be black");
                }
            } else {
                if (checkStandardColor) {
                    throw new IllegalArgumentException("Color must be red, green, blue or yellow");
                }
            }
        } else {
            try {
                int numberInt = Integer.parseInt(number);

                if ((numberInt < 0) || (numberInt > 9)) {
                    throw new IllegalArgumentException("Number must be between 0 and 9");
                }

                if (checkStandardColor) {
                    throw new IllegalArgumentException("Color must be red, green, blue or yellow");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Number is not a number");
            }
        }

        this.number = number;
        this.color = color;
        this.isSpecial = isSpecial;
        this.texture = "img/" + color + "/" + color + "_" + number + ".png";
    }

    public String getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTexture() {
        return texture;
    }

    public boolean isSpecial() {
        return this.isSpecial;
    }
}
