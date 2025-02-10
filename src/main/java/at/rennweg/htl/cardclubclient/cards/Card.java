package at.rennweg.htl.cardclubclient.cards;

/**
 * Card class<br>
 * Represents a class
 *
 * @author Bernd Reither, Lisa-Marie Hörmann, Raven Burkard
 */
public class Card {
    /**
     * The number
     */
    private String number;
    /**
     * The color
     */
    private String color;
    /**
     * The texture
     */
    private String texture;
    /**
     * Is the card a special one
     */
    private boolean isSpecial;

    /**
     * Constructor for a card
     *
     * @param number The number
     * @param color The color
     * @param isSpecial Is the card special
     * @throws IllegalArgumentException Number must be draw2, reverse, skip, wild or wildDraw4
     * @throws IllegalArgumentException Color must be black
     * @throws IllegalArgumentException Color must be red, green, blue or yellow
     * @throws IllegalArgumentException Number must be between 0 and 9
     * @throws IllegalArgumentException Number is not a number
     */
    public Card(String number, String color, boolean isSpecial) {
        final int maxNum = 9;

        final boolean checkStandardColor = !color.equals("red") && !color.equals("green")
                && !color.equals("blue") && !color.equals("yellow");

        if (isSpecial) {
            if (!number.equals("draw2") && !number.equals("reverse")
                    && !number.equals("skip") && !number.equals("wild")
                    && !number.equals("wildDraw4")) {
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

                if ((numberInt < 0) || (numberInt > maxNum)) {
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

    /**
     * Get the number
     *
     * @return Number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Get the color
     *
     * @return Color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the color
     *
     * @param color The color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Get the texture (path)
     *
     * @return The texture (path)
     */
    public String getTexture() {
        return texture;
    }

    /**
     * Is the card a special one
     *
     * @return True/false whether the card is special or not
     */
    public boolean isSpecial() {
        return this.isSpecial;
    }
}
