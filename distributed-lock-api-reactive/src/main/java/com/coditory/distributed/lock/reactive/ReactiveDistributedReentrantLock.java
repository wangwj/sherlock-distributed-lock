package com.coditory.distributed.lock.reactive;

import com.coditory.distributed.lock.common.InstanceId;
import com.coditory.distributed.lock.common.LockId;
import com.coditory.distributed.lock.common.LockRequest;
import com.coditory.distributed.lock.reactive.driver.LockResult;
import com.coditory.distributed.lock.reactive.driver.ReactiveDistributedLockDriver;
import com.coditory.distributed.lock.reactive.driver.UnlockResult;

import java.time.Duration;
import java.util.concurrent.Flow.Publisher;

import static com.coditory.distributed.lock.common.util.Preconditions.expectNonNull;

class ReactiveDistributedReentrantLock implements ReactiveDistributedLock {
  private final LockId lockId;
  private final InstanceId instanceId;
  private final Duration duration;
  private final ReactiveDistributedLockDriver driver;

  ReactiveDistributedReentrantLock(
      LockId lockId,
      InstanceId instanceId,
      Duration duration,
      ReactiveDistributedLockDriver driver) {
    this.lockId = expectNonNull(lockId);
    this.instanceId = expectNonNull(instanceId);
    this.duration = expectNonNull(duration);
    this.driver = expectNonNull(driver);
  }

  @Override
  public String getId() {
    return lockId.getValue();
  }

  @Override
  public Publisher<LockResult> lock() {
    return tryLock(duration);
  }

  @Override
  public Publisher<LockResult> lock(Duration duration) {
    expectNonNull(duration, "Expected non null duration");
    return tryLock(duration);
  }

  @Override
  public Publisher<LockResult> lockInfinitely() {
    return tryLock(null);
  }

  private Publisher<LockResult> tryLock(Duration duration) {
    LockRequest lockRequest = new LockRequest(lockId, instanceId, duration);
    return driver.lockOrRelock(lockRequest);
  }

  @Override
  public Publisher<UnlockResult> unlock() {
    return driver.unlock(lockId, instanceId);
  }
}
