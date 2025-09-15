package co.edu.udea.nexum.opportunity.common.domain.utils.functions;

import co.edu.udea.nexum.opportunity.common.domain.model.OrderableModel;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class OrderableHelper {

    public static <T extends OrderableModel> T saveOrdered(
            T model,
            Supplier<List<T>> orderedSupplier,
            Function<T, T> saveFunction,
            Function<T, T> updateFunction
    ) {
        List<T> orderedList = orderedSupplier.get();

        if (model.getOrder() == null || model.getOrder() >= orderedList.size()) {
            model.setOrder(orderedList.size());
            return saveFunction.apply(model);
        }

        shiftFromIndex(orderedList, model.getOrder(), +1, updateFunction);
        return saveFunction.apply(model);
    }

    public static <T extends OrderableModel> T updateOrdered(
            T oldModel,
            T patchModel,
            Supplier<List<T>> orderedSupplier,
            Function<T, T> updateFunction
    ) {
        List<T> orderedList = orderedSupplier.get();
        Integer oldOrder = oldModel.getOrder();
        Integer newOrder = patchModel.getOrder();

        if (newOrder == null || newOrder.equals(oldOrder)) {
            patchModel.setOrder(oldOrder);
            return updateFunction.apply(patchModel);
        }

        if (newOrder >= orderedList.size()) {
            newOrder = orderedList.size() - 1;
        }

        shiftRange(orderedList, oldOrder, newOrder, updateFunction);
        patchModel.setOrder(newOrder);
        return updateFunction.apply(patchModel);
    }

    public static <T extends OrderableModel> void deleteOrdered(
            T toDelete,
            List<T> orderedList,
            Consumer<T> deleteFunction,
            Function<T, T> updateFunction
    ) {
        Integer deletedOrder = toDelete.getOrder();
        deleteFunction.accept(toDelete);

        for (T item : orderedList) {
            if (item.getOrder() > deletedOrder) {
                item.setOrder(item.getOrder() - 1);
                updateFunction.apply(item);
            }
        }
    }

    private static <T extends OrderableModel> void shiftFromIndex(
            List<T> list,
            int fromIndex,
            int shift,
            Function<T, T> updateFunction
    ) {
        list.stream()
                .filter(item -> item.getOrder() >= fromIndex)
                .sorted((a, b) -> Integer.compare(b.getOrder(), a.getOrder())) // orden descendente
                .forEach(item -> {
                    item.setOrder(item.getOrder() + shift);
                    updateFunction.apply(item);
                });
    }


    private static <T extends OrderableModel> void shiftRange(
            List<T> list,
            int from,
            int to,
            Function<T, T> updateFunction
    ) {
        if (from == to) return;

        int direction = from < to ? -1 : 1;

        list.stream()
                .filter(item -> {
                    int order = item.getOrder();
                    return from < to ? (order > from && order <= to) : (order >= to && order < from);
                })
                .forEach(item -> {
                    item.setOrder(item.getOrder() + direction);
                    updateFunction.apply(item);
                });
    }

}
