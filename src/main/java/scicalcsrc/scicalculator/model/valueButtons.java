package scicalcsrc.scicalculator.model;
import scicalcsrc.scicalculator.model.characterButtons;

public class valueButtons {
    private characterButtons B1;
    private characterButtons B2;
    private characterButtons B3;
    private characterButtons B4;
    private characterButtons B5;
    private characterButtons B6;
    private characterButtons B7;
    private characterButtons B8;
    private characterButtons B9;
    private characterButtons B0;
    private characterButtons Bdot;
    private characterButtons Bcomma;
    private characterButtons Bmultiply;
    private characterButtons Bdivide;
    private characterButtons Baddition;
    private characterButtons Bsubtraction;
    private characterButtons Bmodulo;
    private characterButtons Babs;
    private characterButtons Bfrct;
    private characterButtons Bsqrt;
    private characterButtons Bexp;
    private characterButtons Bsqrd;
    private characterButtons BlogN;
    private characterButtons BAns;
    private characterButtons Bsin;
    private characterButtons Bcos;
    private characterButtons Btan;
    private characterButtons BleftPar;
    private characterButtons BrightPar;
    private characterButtons BcustomRoot;
    private characterButtons Binverse;
    private characterButtons Blog10;
    private characterButtons Bln;

    public valueButtons() {//declares objects of buttons and instantiates their values and shifted values
        this.B1=new characterButtons("1","D");
        this.B2=new characterButtons("2","E");
        this.B3=new characterButtons("3","F");
        this.B4=new characterButtons("4","A");
        this.B5=new characterButtons("5","B");
        this.B6=new characterButtons("6","C");
        this.B7=new characterButtons("7","π");
        this.B8=new characterButtons("8","e");
        this.B9=new characterButtons("9","i");
        this.B0=new characterButtons("0","x");
        this.Bdot=new characterButtons(".","y");
        this.Bcomma=new characterButtons(",","z");
        this.Bmultiply=new characterButtons("*","*");
        this.Bdivide=new characterButtons("/","/");
        this.Baddition=new characterButtons("+","+");
        this.Bsubtraction=new characterButtons("-","-");
        this.Bmodulo=new characterButtons("%","%");
        this.Babs=new characterButtons("||","||");
        this.Bfrct=new characterButtons("(/)","arcsin()");
        this.Bsqrt=new characterButtons("√()","arccos()");
        this.Bexp=new characterButtons("^()","arctan()");
        this.Bsqrd=new characterButtons("^(2)","^(2)");
        this.BlogN=new characterButtons("log(,)","log(,)");
        this.BAns=new characterButtons("Ans","Ans");
        this.Bsin=new characterButtons("sin()","csc()");
        this.Bcos=new characterButtons("cos()","sec()");
        this.Btan=new characterButtons("tan()","cot()");
        this.BleftPar=new characterButtons("(","(");
        this.BrightPar=new characterButtons(")",")");
        this.BcustomRoot=new characterButtons("√(,)","√(,)");
        this.Binverse=new characterButtons("^(-1)","^(-1)");
        this.Blog10=new characterButtons("log(10,)","log(10,)");
        this.Bln=new characterButtons("ln()","()");


    }
    //gets the String value of the buttons
    public String getoneButton(){return B1.getButtonValueString();}
    public String gettwoButton(){return B2.getButtonValueString();}
    public String getthreeButton(){return B3.getButtonValueString();}
    public String getfourButton(){return B4.getButtonValueString();}
    public String getfiveButton(){return B5.getButtonValueString();}
    public String getsixButton(){return B6.getButtonValueString();}
    public String getsevenButton(){return B7.getButtonValueString();}
    public String geteightButton(){return B8.getButtonValueString();}
    public String getnineButton(){return B9.getButtonValueString();}
    public String getzeroButton(){return B0.getButtonValueString();}
    public String getdotButton(){return Bdot.getButtonValueString();}
    public String getcommaButton(){return Bcomma.getButtonValueString();}
    public String getmultiplyButton(){return Bmultiply.getButtonValueString();}
    public String getdivideButton(){return Bdivide.getButtonValueString();}
    public String getadditionButton(){return Baddition.getButtonValueString();}
    public String getsubtractionButton(){return Bsubtraction.getButtonValueString();}
    public String getmoduloButton(){return Bmodulo.getButtonValueString();}
    public String getAbsButton(){return Babs.getButtonValueString();}
    public String getfrctButton(){return Bfrct.getButtonValueString();}
    public String getsqrtButton(){return Bsqrt.getButtonValueString();}
    public String getexpButton(){return Bexp.getButtonValueString();}
    public String getsqrdButton(){return Bsqrd.getButtonValueString();}
    public String getlogNButton(){return BlogN.getButtonValueString();}
    public String getAnsButton(){return BAns.getButtonValueString();}
    public String getsinButton(){return Bsin.getButtonValueString();}
    public String getcosButton(){return Bcos.getButtonValueString();}
    public String gettanButton(){return Btan.getButtonValueString();}
    public String getleftParButton(){return BleftPar.getButtonValueString();}
    public String getrightParButton(){return BrightPar.getButtonValueString();}
    public String getcustomRootButton(){return BcustomRoot.getButtonValueString();}
    public String getinverseButton(){return Binverse.getButtonValueString();}
    public String getlog10Button(){return Blog10.getButtonValueString();}
    public String getlnButton(){return Bln.getButtonValueString();}

}//SOLANO
