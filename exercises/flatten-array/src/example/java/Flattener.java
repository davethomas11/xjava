import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class Flattener<T> {

    List<T> flatten(final List<ValueOrList<T>> nestedList) {
        if (nestedList.isEmpty()) {
            return new ArrayList<>();
        } else {
            final List<T> result = new ArrayList<>();

            if (nestedList.get(0).isList()) {
                result.addAll(flatten(nestedList.get(0).getList()));
            } else {
                result.add(nestedList.get(0).getValue());
            }

            result.addAll(flatten(nestedList.subList(1, nestedList.size())));
            result.removeAll(Collections.singleton((T) null));
            return result;
        }
    }

}
