import java.lang.*;

public class playfair{

  // This is to get rid of all punctuation and spaces and uppercase everything
  public static String setup (String beans){
    beans = beans.replaceAll("[^A-Za-z]+", "");
    return beans.toUpperCase();
  }

  // Turns the Js into Is
  public static String changeToI (String beans){
    String cheese = "";
    for (int i = 0; i < beans.length(); i++){
      if (beans.charAt(i) == 'J'){
        cheese += 'I';
      }
      else cheese += beans.charAt(i);
    }
    return cheese;
  }

  // Adds the X in pairings
  public static String theList (String beans){
    String b = "";
    int i = 0;

    while (i < beans.length()-1){
      if (beans.charAt(i) != beans.charAt(i + 1)){
        b = b + beans.charAt(i) +  beans.charAt(i + 1);
        i = i + 2;
      }
      else {
        b = b + beans.charAt(i) + 'X';
        i++;
      }
    }

    if (i % 2 == 1){
      return b + beans.charAt(beans.length() -1);
    }

    return b;
  }


  // Checking the length of the string and padding it with an Z or X
  public static String padZ (String beans){
    String cheese = "";
    if (beans.length() % 2 == 1){
      if (beans.charAt(beans.length()-1) == 'Z'){
        cheese = beans + 'X';
      }
      else cheese = beans + 'Z';
    }
    else cheese = beans;
    return cheese;
  }

  public static String [] gPairs (String beans){
    String [] a = new String [beans.length()/2];
    for (int i = 0; i < beans.length()/2; i++){
      a[i] = beans.substring(i*2, i*2 + 2);
    }
    return a;
  }

  //makes the square
  public static Character [][] square (String beans){
    Character [][] square = new Character [5][5];
      int i = 0;
      for (int row = 0; row < 5; row++){
        for (int col = 0; col < 5; col++){
          square[row][col] = beans.charAt(i);
          i++;
        }
      }
      return square;
  }

  // Finds the x, y position of the letter in the 5x5 square
  public static String findPosition (Character [][] a, Character b){
    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        if(a[i][j] == b){
          return Integer.toString(i) + Integer.toString(j);
        }
      }
    }
    return "";
  }

  //SAME ROW ENCODING/DECODING
  public static String horizontalEncode(String theWay, Character [][] square, String letterPair){
    Character a = letterPair.charAt(0);
    Character b = letterPair.charAt(1);
    String c = findPosition(square, a);
    Character d = c.charAt(0);
    Character d2 = c.charAt(1);
    int e2 = Character.getNumericValue(d2);
    int e = Character.getNumericValue(d);
    //change row
    if (theWay.equals("encode")){
      if ( e == 4){
        e = 0;
      }
      else {
        e = e + 1;
      }
    }
    if (theWay.equals("decode")){
      if (e == 0){
        e = 4;
      }
      else {
        e = e - 1;
      }
    }
    String f = findPosition(square, b);
    Character g = f.charAt(1);
    int g2 = Character.getNumericValue(g);
    return Character.toString(square[e][e2]) + Character.toString(square[e][g2]);

  }

  //SAME COLUMN ENCODING/DECODING
  public static String verticalEncode(String theWay, Character [][] square, String letterPair){
    Character a = letterPair.charAt(0);
    Character b = letterPair.charAt(1);
    String c = findPosition(square, a);
    Character d2 = c.charAt(0);
    Character d = c.charAt(1);
    int e2 = Character.getNumericValue(d2);
    int e = Character.getNumericValue(d);
    //change column
  //  System.out.println(theWay.equals("encode"));
    if (theWay.equals("encode")){
      if (e == 4){
        e = 0;
      }
      else {
        e = e + 1;
      }
    }


    if (theWay.equals("decode")){
      if (e == 0){
        e = 4;
      }
      else {
        e = e - 1;
      }
    }

    String f = findPosition(square, b);
    Character g = f.charAt(0);
    int g2 = Character.getNumericValue(g);
    return Character.toString(square[e2][e]) + Character.toString(square[g2][e]);
  }

  // REGULAR ENCODING/DECODING
  public static String regularEncode(Character [][] square, String letterPair){
    Character a = letterPair.charAt(0);
    Character b = letterPair.charAt(1);
    String c = findPosition(square, a);
    Character d = c.charAt(0);
    Character d2 = c.charAt(1);
    int e = Character.getNumericValue(d);
    int e2 = Character.getNumericValue(d2);
    String f = findPosition(square, b);
    Character g = f.charAt(0);
    Character g2 = f.charAt(1);
    int h = Character.getNumericValue(g);
    int h2 = Character.getNumericValue(g2);
    return Character.toString(square[e][h2]) + Character.toString(square[h][e2]);


  }

  // Used to determine which of the encoding/decoding methods you want
  public static String determineE(Character [][] square, String letterPair){
    // the first letter of the letter pair
    Character a = letterPair.charAt(0);
    //The second letter of the letter pair
    Character b = letterPair.charAt(1);
    // same row
    if(findPosition(square, a).charAt(0) == findPosition(square, b).charAt(0)){
      return "horizontalEncode";
    }
    //same column
    if ((findPosition(square, a).charAt(1)) == findPosition(square, b).charAt(1)){
      return "verticalEncode";
    }
    return "regularEncode";
  }

  // Telling which encode/decode to do
  public static String action(String theWay, String beans, Character [][] a, String letterPair){
    if (beans == "regularEncode"){
      return regularEncode(a, letterPair);
    }
    if (beans == "horizontalEncode"){
      return horizontalEncode(theWay, a, letterPair);
    }
    if (beans == "verticalEncode"){
      return verticalEncode(theWay, a, letterPair);
    }
    return "";
  }

  public static String runThrough(String theWay, String [] jokes, Character [][] a){
    String cheese = "";
    for (int i = 0; i < jokes.length; i++){
      String beans = determineE(a, jokes[i]);
      cheese = cheese + action(theWay, beans, a, jokes[i]);
    }
    return cheese;
  }


  public static void main(String [] args){
    //the decode/encode to determine shift
    String direction = args[0];
    // the word to encode
    String beans = args[1];
    // the thing to make into a square
    String cheese = args[2];
    Character [][] pizza = square(cheese);
    String [] jokes = gPairs(padZ(theList(changeToI(setup(beans)))));
    System.out.println(runThrough(direction, jokes, pizza));
  }

}
