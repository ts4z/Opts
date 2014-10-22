package net.ts4z.opts;

import com.linkedin.parseq.Task;

import java.util.function.Function;

/**
 * @author tshowalt
 */
public class TaskOpt<T> implements Opt<T> {
  Task<T> _task;

  TaskOpt(Task<T> task) {
    _task = task;
  }

  public <R> Opt<R> map(Function<? super T, ? extends R> fn) {
    if (_task.isFailed()) {
      return Opt.empty();
    } else {
      return Opt.of(fn.apply(_task.get()));
    }
  }
  public T orElse(T alternate) {
    if (_task.isFailed()) {
      return alternate;
    } else {
      return _task.get();
    }
  }
  public boolean isPresent() { return !_task.isFailed(); }

  public T get() { return _task.get(); }
}
