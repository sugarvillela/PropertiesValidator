package attrib.iface;

import validator.iface.IValid;

public interface PropertyEnum {
    String defKey();
    String defValue();
    IValid validator();
}
