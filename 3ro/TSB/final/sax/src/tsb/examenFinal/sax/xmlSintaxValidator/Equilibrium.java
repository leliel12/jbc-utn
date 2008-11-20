package tsb.examenFinal.sax.xmlSintaxValidator;

import java.util.Stack;
import tsb.examenFinal.util.StrOp;

/**
 *
 * todo elemento que empieza debe terminar
 */
public class Equilibrium {

    private Stack<String> stack;
    private boolean used;
    private int root;

    public Equilibrium() {
        this.stack = new Stack<String>();
        this.used = false;
    }

    public void add(final String tagToValidate, int classify) {
        String tag = tagToValidate.toLowerCase();
        if (!used) {
            used = true;
        }
        switch (classify) {
            case Classifier.TAG_INIT:
                stack.push(tag);
                break;
            case Classifier.TAG_AUTOCLOSE:
                break;
            case Classifier.TAG_END:
                if (comprobate(tag)) {
                    stack.pop();
                    if (stack.isEmpty()) {
                        root++;
                    }
                }
                break;
        }
    }

    public void clear() {
        this.stack.clear();
        used = false;
    }

    public String getCause() {
        String cause = "";
        if (!stack.isEmpty()) {
            cause = "XML not balanced\n";
        }
        if (!this.used) {
            cause += "XML doesn't have elements\n";
        }
        if (root != 1) {
            cause += "Wrong number of root elements";
        }
        return cause;
    }

    public boolean isValid() {
        return stack.empty() && this.used && root == 1;
    }

    private boolean comprobate(String tag) {
        String last = StrOp.getNameOfTag(stack.peek(), "<", ">");
        String actual = StrOp.getNameOfTag(tag, "</", ">");
        return last.equals(actual);
    }
}
