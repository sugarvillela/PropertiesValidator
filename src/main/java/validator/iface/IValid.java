package validator.iface;

import attrib.iface.IValidProps;

public interface IValid {
    boolean isGood(String text);
    boolean isGood(IValidProps requester, String text);
}
