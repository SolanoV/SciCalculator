package scicalcsrc.scicalculator.model;

abstract class Buttons {
    private String buttonValue;
    private int buttonValueLength;

    Buttons(){
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

    abstract int getbuttonLength(String buttonValue);

}
