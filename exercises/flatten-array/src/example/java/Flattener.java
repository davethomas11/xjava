import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class Flattener {

    List flatten(final List nestedList) {
        if (nestedList.isEmpty()) {
            return new ArrayList<>();
        } else {
            final List result = new ArrayList();

            if (nestedList.get(0) instanceof List) {
                result.addAll(flatten((List) nestedList.get(0)));
            } else {
                result.add(nestedList.get(0));
            }

            result.addAll(flatten(nestedList.subList(1, nestedList.size())));
            result.removeAll(Collections.singleton(null));
            return result;
        }
    }

}
