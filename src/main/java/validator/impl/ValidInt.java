package validator.impl;

import attrib.iface.IValidProps;
import validator.iface.IValid;

public class ValidInt implements IValid {
    private static ValidInt instance;

    public static ValidInt initInstance(){
        return (instance == null)? (instance = new ValidInt()): instance;
    }

    private ValidInt(){}

    @Override
    public boolean isGood(String text) {
        try{
            Integer.parseInt(text);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean isGood(IValidProps requester, String text) {
        return this.isGood(text);
    }
}
