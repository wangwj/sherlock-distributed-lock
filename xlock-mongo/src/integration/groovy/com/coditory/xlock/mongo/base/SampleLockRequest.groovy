package com.coditory.xlock.mongo.base


import com.coditory.xlock.common.LockId
import com.coditory.xlock.common.LockInstanceId
import com.coditory.xlock.common.ServiceInstanceId
import com.coditory.xlock.common.driver.LockRequest
import groovy.transform.CompileStatic

import java.time.Duration

@CompileStatic
class SampleLockRequest {
  private static final Map<String, ?> DEFAULT_PROPERTIES = [
      lockId           : LockId.of("sample-lock-id"),
      lockInstanceId   : LockInstanceId.of("sample-lock-instance-id"),
      serviceInstanceId: ServiceInstanceId.of("sample-service-instance-id"),
      duration         : Duration.ofMinutes(5)
  ]

  static LockRequest sampleLockRequest(Map<String, ?> customProperties = [:]) {
    Map<String, ?> properties = DEFAULT_PROPERTIES + customProperties
    return new LockRequest(
        properties.lockId as LockId,
        properties.lockInstanceId as LockInstanceId,
        properties.serviceInstanceId as ServiceInstanceId,
        properties.duration as Duration
    )
  }
}
