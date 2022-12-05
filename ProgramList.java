import java.util.ArrayList;

public class ProgramList extends ProgramObject {
    ArrayList<ProgramObject> lis;

    public ProgramList() {
        lis = new ArrayList<ProgramObject>(0);
    }

    public ProgramList(ProgramObject p) {
        lis = new ArrayList<ProgramObject>(1);
        lis.add(p);
    }

    public ProgramList(ArrayList<ProgramObject> l) {
        lis = l;
    }

    public boolean isNil() {
        return lis.size() == 0;
    }

    public boolean cons(ProgramObject p) {
        return lis.add(p);
    }

    public boolean cons(ArrayList<ProgramObject> ps) {
        return lis.addAll(ps);
    }

    public boolean cons(ProgramList l) {
        return lis.addAll(l.getList());
    }

    public ProgramObject car() {
        if(lis == null || lis.size() == 0) {
            Jlisp.failGracefully("car was called on a null list", -1);
        }

        return lis.get(0);
    }

    public ProgramList cdr() {
        if(lis == null || lis.size() == 0) {
            Jlisp.failGracefully("cdr was called on a null list", -1);
        }

        lis.remove(0);
        return new ProgramList(lis);
    }

    public String toString() {
        return lis.toString();
    }

    public ProgramObjectType getType() {
        return ProgramObjectType.LIST;
    }

    public Object getValue() {
        return lis;
    }

    public ArrayList<ProgramObject> getList() {
        return lis;
    }
}
