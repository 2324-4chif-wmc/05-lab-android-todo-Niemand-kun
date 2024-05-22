package at.htl.todo.util.immer;

import java.util.function.Consumer;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.todo.util.mapper.Mapper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

@Singleton
public class Immer<T> {
    final Mapper<T> mapper;

    @Inject
    public Immer(Class<? extends T> type) {
        mapper = new Mapper<T>(type);
    }

    public void produce(final T readonlyState, Consumer<T> recipe, Consumer<T> resultConsumer) {
        var nextState = mapper.clone(readonlyState);
        var threadSafe = BehaviorSubject.createDefault(nextState);
        threadSafe.observeOn(AndroidSchedulers.mainThread()).subscribe(model -> {
            recipe.accept(model);
            resultConsumer.accept(model);
        });
    }
}
