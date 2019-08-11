package in.anees.tdd.mvp.rx;

import in.anees.tdd.mvp.rx.scheduler.SchedulerProvider;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Anees Thyrantakath on 2019-08-11.
 */
public class SchedulerProviderImpl implements SchedulerProvider {
    @Override
    public Scheduler mainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler backgroundScheduler() {
        return Schedulers.io();
    }
}
