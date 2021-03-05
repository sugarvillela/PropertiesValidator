package attrib.iface;

import java.util.Properties;

public interface IValidProps {
    boolean put(String keyValPair, Properties properties);
    Properties getDefaults();

    void receive(String key, String val);
}
