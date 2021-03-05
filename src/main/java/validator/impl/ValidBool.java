package validator.impl;

import attrib.iface.IValidProps;
import validator.iface.IValid;

public class ValidBool implements IValid {
    private static ValidBool instance;

    public static ValidBool initInstance(){
        return (instance == null)? (instance = new ValidBool()): instance;
    }

    private ValidBool(){}

    @Override
    public boolean isGood(String text) {
        return "true".equalsIgnoreCase(text) || "false".equalsIgnoreCase(text);
    }

    @Override
    public boolean isGood(IValidProps requester, String text) {
        return this.isGood(text);
    }
}
