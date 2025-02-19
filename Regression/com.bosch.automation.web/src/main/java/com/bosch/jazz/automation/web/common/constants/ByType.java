/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import org.openqa.selenium.By;

/**
 * Enumerator to define the {@link By} to use for finding an element
 */
@SuppressWarnings("javadoc")
public enum ByType {

                    ID("id"),
                    XPATH("xpath"),
                    LINK_TEXT("linkText"),
                    CLASS_NAME("className"),
                    CSS_SELECTOR("cssSelector"),
                    PARTIAL_LINK_TEXT("partialLinkText"),
                    TAG_NAME("tagName"),
                    NAME("name");

  private String propertyLabel;

  ByType(final String propertyLabel) {
    this.propertyLabel = propertyLabel;
  }


  /**
   * @param name the name of the "By" to be used, must not be null
   * @return the corresponding type, never null
   * @throws IllegalArgumentException in case the given name cannot be mapped to any of the instances of this enum
   */
  public static ByType from(final String name) {
    for (ByType byType : ByType.values()) {
      if (byType.propertyLabel.equalsIgnoreCase(name)) {
        return byType;
      }
    }
    throw new IllegalArgumentException("Invalid/unsupported name given: " + name);
  }
}
