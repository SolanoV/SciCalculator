package scicalcsrc.scicalculator.model;

public class characterButtons extends Buttons{//class for making the buttons' values and shifted values
    private String shiftedValue;
    private int shiftedValueLength;
    private Calculator c=new Calculator();

    public characterButtons(){//default
        this.shiftedValue="";
        this.shiftedValueLength=0;
    }
    public characterButtons(String buttonValue){//if there's only one value and no shifted value
        super(buttonValue);
    }
    public characterButtons(String buttonValue, String shiftedValue) {//if there's both values
        super(buttonValue);
        this.shiftedValue=shiftedValue;
        this.shiftedValueLength=shiftedValue.length();
    }
    public String getButtonValueString(){
        if(c.getShift()==true) {
            return shiftedValue;
        }
        return super.getButtonValue();
    }
    public int callGetButtonLength(String buttonValue){
        return getbuttonLength(buttonValue);
    }
    @Override//abstract method, this is needed to get the length of button (was intented for dynamic backspacing) used for something else
    int getbuttonLength(String buttonValue) {
        return buttonValue.length();
    }
}//SOLANO, IGNACIO
