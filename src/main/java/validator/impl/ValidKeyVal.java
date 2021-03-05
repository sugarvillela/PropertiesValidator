package validator.impl;

import attrib.iface.IValidProps;
import validator.iface.IValid;

public class ValidKeyVal implements IValid {
    private static ValidKeyVal instance;

    public static ValidKeyVal initInstance(){
        return (instance == null)? (instance = new ValidKeyVal()): instance;
    }

    private ValidKeyVal(){}

    @Override
    public boolean isGood(String text) {
        String[] tok;
        if(
            text != null &&
            (tok = text.split("=")).length == 2 &&
            !tok[0].isEmpty() &&
            !tok[1].isEmpty()
        ){
            return true;
        }
        return false;
    }

    @Override
    public boolean isGood(IValidProps requester, String text) {
        String[] tok;
        if(
            text != null &&
            (tok = text.split("=")).length == 2 &&
            !tok[0].isEmpty() &&
            !tok[1].isEmpty()
        ){
            requester.receive(tok[0], tok[1]);
            return true;
        }
        return false;
    }
}
