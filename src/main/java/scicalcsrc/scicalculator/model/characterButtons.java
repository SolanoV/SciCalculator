package scicalcsrc.scicalculator.model;

public class characterButtons extends Buttons{
    private String shiftedValue;
    private int shiftedValueLength;
    private Calculator c=new Calculator();

    public characterButtons(){
        this.shiftedValue="";
        this.shiftedValueLength=0;
    }
    public characterButtons(String buttonValue){
        super(buttonValue);
    }
    public characterButtons(String buttonValue, String shiftedValue) {
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
    @Override
    int getbuttonLength(String buttonValue) {
        return buttonValue.length();
    }
}
