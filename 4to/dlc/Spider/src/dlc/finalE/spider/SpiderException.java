/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

/**
 *
 * @author juan
 */
public class SpiderException extends Exception {

    public static final byte WARNING = 0;
    public static final byte ERROR = 1;
    public static final byte FATAL_ERROR = 2;
    public static final byte FILE_HANDLER_NOT_FOUND = 3;
    private enum type {1,2,3};
    private byte exceptionType;

    public SpiderException(byte exceptionType) {
        super(getExceptionTypeName(exceptionType));
        this.setExceptionType(exceptionType);
    }

    public SpiderException(String msg, byte exceptionType) {
        super(getExceptionTypeName(exceptionType) + " - " + msg);
        this.setExceptionType(exceptionType);
    }

    /**
     * @return the exceptionType
     */
    public byte getExceptionType() {
        return exceptionType;
    }

    /**
     * @param exceptionType the exceptionType to set
     */
    private void setExceptionType(byte exceptionType) {
        this.exceptionType = exceptionType;
    }

    public static String getExceptionTypeName(byte exType) {
        String toReturn;
        switch (exType) {
            case WARNING:
                toReturn = "Warning";
                break;
            case ERROR:
                toReturn = "Error";
                break;
            case FATAL_ERROR:
                toReturn = "Fatal Error";
                break;
            case FILE_HANDLER_NOT_FOUND:
                toReturn="File Handler Not Found";
                break;
            default:
                toReturn = "Unknow Type";
        }
        return toReturn;
    }
}
