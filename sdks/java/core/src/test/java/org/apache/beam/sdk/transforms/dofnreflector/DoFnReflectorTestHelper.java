/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.beam.sdk.transforms.dofnreflector;

import org.apache.beam.sdk.transforms.DoFnReflectorTest.Invocations;
import org.apache.beam.sdk.transforms.DoFnWithContext;

/**
 * Test helper for DoFnReflectorTest, which needs to test package-private access
 * to DoFns in other packages.
 */
public class DoFnReflectorTestHelper {

  private static class StaticPrivateDoFn extends DoFnWithContext<String, String> {
    final Invocations invocations;

    public StaticPrivateDoFn(Invocations invocations) {
      this.invocations = invocations;
    }

    @ProcessElement
    public void process(ProcessContext c) {
      invocations.wasProcessElementInvoked = true;
    }
  }

  private class InnerPrivateDoFn extends DoFnWithContext<String, String> {
    final Invocations invocations;

    public InnerPrivateDoFn(Invocations invocations) {
      this.invocations = invocations;
    }

    @ProcessElement
    public void process(ProcessContext c) {
      invocations.wasProcessElementInvoked = true;
    }
  }

  static class StaticPackagePrivateDoFn extends DoFnWithContext<String, String> {
    final Invocations invocations;

    public StaticPackagePrivateDoFn(Invocations invocations) {
      this.invocations = invocations;
    }

    @ProcessElement
    public void process(ProcessContext c) {
      invocations.wasProcessElementInvoked = true;
    }
  }

  class InnerPackagePrivateDoFn extends DoFnWithContext<String, String> {
    final Invocations invocations;

    public InnerPackagePrivateDoFn(Invocations invocations) {
      this.invocations = invocations;
    }

    @ProcessElement
    public void process(ProcessContext c) {
      invocations.wasProcessElementInvoked = true;
    }
  }

  public static DoFnWithContext<String, String> newStaticPackagePrivateDoFn(
      Invocations invocations) {
    return new StaticPackagePrivateDoFn(invocations);
  }

  public DoFnWithContext<String, String> newInnerPackagePrivateDoFn(Invocations invocations) {
    return new InnerPackagePrivateDoFn(invocations);
  }

  public static DoFnWithContext<String, String> newStaticPrivateDoFn(Invocations invocations) {
    return new StaticPrivateDoFn(invocations);
  }

  public DoFnWithContext<String, String> newInnerPrivateDoFn(Invocations invocations) {
    return new InnerPrivateDoFn(invocations);
  }

  public DoFnWithContext<String, String> newInnerAnonymousDoFn(final Invocations invocations) {
    return new DoFnWithContext<String, String>() {
      @ProcessElement
      public void process(ProcessContext c) {
        invocations.wasProcessElementInvoked = true;
      }
    };
  }

  public static DoFnWithContext<String, String> newStaticAnonymousDoFn(
      final Invocations invocations) {
    return new DoFnWithContext<String, String>() {
      @ProcessElement
      public void process(ProcessContext c) {
        invocations.wasProcessElementInvoked = true;
      }
    };
  }
}
