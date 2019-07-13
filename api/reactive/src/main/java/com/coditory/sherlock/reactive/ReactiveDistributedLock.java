package com.coditory.sherlock.reactive;

import com.coditory.sherlock.reactive.driver.LockResult;
import com.coditory.sherlock.reactive.driver.ReleaseResult;

import java.time.Duration;
import java.util.concurrent.Flow.Publisher;

/**
 * A lock for distributed environment consisting of multiple application instances. Acquire a
 * distributed lock when only one application instance should execute a specific action.
 *
 * @see ReactiveSherlock
 */
public interface ReactiveDistributedLock {
  /**
   * @return the lock id
   */
  String getId();

  /**
   * Try to acquire the lock. Lock is acquired for a configured duration. After that times it
   * expires and is ready to be acquired by other instance.
   *
   * @return {@link LockResult#SUCCESS}, if lock was acquired
   */
  Publisher<LockResult> acquire();

  /**
   * Try to acquire the lock for a given duration. After that times it expires and is ready to be
   * acquired by other instance.
   *
   * @param duration - how much time must pass for the acquired lock to expire
   * @return {@link LockResult#SUCCESS}, if lock was acquired
   */
  Publisher<LockResult> acquire(Duration duration);

  /**
   * Try to acquire the lock without expiring date. It is potentially dangerous. Lookout for a
   * situation where the lock owning instance goes down with out releasing the lock.
   *
   * @return {@link LockResult#SUCCESS}, if lock was acquired
   */
  Publisher<LockResult> acquireForever();

  /**
   * Release the lock
   *
   * @return {@link ReleaseResult#SUCCESS}, if lock was released in this call. If lock could not be
   * released or was released by a different instance then {@link ReleaseResult#FAILURE} is
   * returned.
   */
  Publisher<ReleaseResult> release();
}
