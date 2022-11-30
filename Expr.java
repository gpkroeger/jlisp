import java.util.ArrayList;

public abstract class Expr {
    public abstract TokType getType();
    public abstract String getValue();
    public abstract ArrayList<Expr> getChildren();
    public abstract boolean isList();
}
