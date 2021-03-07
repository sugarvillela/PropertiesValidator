package validator;

/** If resources are scarce, a switch for messaging would be better;
 * Instantiating all messages up front violates the 'plan for the common case' rule */

public enum ERR_TYPE {
    NONE            ("", false),
    NOT_KEY_VAL     ("Expected 'key=value' format"),
    UNKNOWN_KEY     ("Unknown key"),
    INVALID_BOOL    ("Expected true/false boolean"),
    INVALID_INT     ("Expected integer value"),
    INVALID_STRING  ("Expected non-empty text"),
    INVALID_VAL     ("Invalid value"),
    FILE_ERROR
    ;

    private final String message;
    private final boolean isErr;

    ERR_TYPE() {
        this.message = null;
        this.isErr = true;
    }
    ERR_TYPE(String message, boolean isErr) {
        this.message = message;
        this.isErr = isErr;
    }
    ERR_TYPE(String message) {
        this.message = message;
        this.isErr = true;
    }
    
    public String message(){
        return (message == null)? this.toString() : message;
    }
    public boolean isErr(){
        return isErr;
    }
}
