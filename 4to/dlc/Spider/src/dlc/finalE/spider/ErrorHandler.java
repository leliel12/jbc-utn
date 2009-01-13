/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dlc.finalE.spider;

/**
 *
 * @author juan
 */
public interface ErrorHandler {

    public void warning(SpiderException ex);

    public void error(SpiderException ex);

    public void fatalError(SpiderException ex);

    public void fileHandlerNotFound(SpiderException ex);

}
