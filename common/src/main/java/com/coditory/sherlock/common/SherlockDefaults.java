package com.coditory.sherlock.common;

import java.time.Clock;
import java.time.Duration;

import static java.time.Clock.systemDefaultZone;

public final class SherlockDefaults {
  public static final Duration DEFAULT_LOCK_DURATION = Duration.ofMinutes(5);
  public static final OwnerId DEFAULT_INSTANCE_ID = OwnerId.uniqueOwnerId();
  public static final Clock DEFAULT_CLOCK = systemDefaultZone();
  public static final String DEFAULT_DB_TABLE_NAME = "locks";

  private SherlockDefaults() {
    throw new IllegalStateException("Do not instantiate utility class");
  }
}
