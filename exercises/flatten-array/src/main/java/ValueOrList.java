import java.util.List;

public class ValueOrList<E> {

    private final E value;

    private final List<ValueOrList<E>> list;

    public ValueOrList(final E value) {
        this.value = value;
        this.list = null;
    }

    public ValueOrList(final List<ValueOrList<E>> list) {
        this.value = null;
        this.list = list;
    }

    boolean isValue() {
        return value != null;
    }

    boolean isList() {
        return list != null;
    }

    E getValue() {
        if (!isValue()) {
            throw new IllegalStateException();
        }

        return value;
    }

    List<ValueOrList<E>> getList() {
        if (!isList()) {
            throw new IllegalStateException();
        }

        return list;
    }

}