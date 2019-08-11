package in.anees.tdd.mvp.rx.scheduler;

import io.reactivex.Scheduler;

/**
 * Created by Anees Thyrantakath on 2019-08-11.
 */
public interface SchedulerProvider {
    Scheduler mainScheduler();
    Scheduler backgroundScheduler();
}
