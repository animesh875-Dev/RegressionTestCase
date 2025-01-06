package com.bosch.jazz.automation.web.common.constants;

/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
@SuppressWarnings("javadoc")
public final class PlansEnum {

  public enum PlanTypes {
                         SPRINT_BACKLOG("Sprint Backlog"),
                         RELEASE_BACKLOG("Release Backlog"),
                         PRODUCT_BACKLOG("Product Backlog"),
                         CROSS_PROJECT_PLAN("Cross-Project Plan"),
                         PHASE_PLAN("Phase Plan"),
                         RELEASE_PLAN("Release Plan");

    private final String name;

    private PlanTypes(String s) {
      name = s;
    }

    public boolean equalsName(String otherName) {
      return name.equals(otherName);
    }

    @Override
    public String toString() {
      return this.name;
    }
  }
}
