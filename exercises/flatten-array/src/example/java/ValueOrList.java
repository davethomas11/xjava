import java.util.List;

class ValueOrList<E> {

    private final E value;

    private final List<ValueOrList<E>> list;

    private final Type type;

    ValueOrList(final E value) {
        this.value = value;
        this.list = null;
        this.type = Type.VALUE;
    }

    ValueOrList(final List<ValueOrList<E>> list) {
        this.value = null;
        this.list = list;
        this.type = Type.LIST;
    }

    boolean isValue() {
        return Type.VALUE.equals(type);
    }

    boolean isList() {
        return Type.LIST.equals(type);
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

    private enum Type {
        VALUE, LIST
    }

}