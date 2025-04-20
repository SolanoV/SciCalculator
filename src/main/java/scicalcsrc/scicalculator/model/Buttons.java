package scicalcsrc.scicalculator.model;

abstract class Buttons {//abstract class for the Buttons
    private String buttonValue;
    private int buttonValueLength;

    Buttons(){//default constructor
        this.buttonValue="";
        this.buttonValueLength=0;
    }
    Buttons(String buttonValue){
        this.buttonValue=buttonValue;
        this.buttonValueLength=buttonValue.length();
    }
    String getButtonValue(){
        return buttonValue;
    }

    abstract int getbuttonLength(String buttonValue);//abstract method

}//SOLANO, IGNACIO
