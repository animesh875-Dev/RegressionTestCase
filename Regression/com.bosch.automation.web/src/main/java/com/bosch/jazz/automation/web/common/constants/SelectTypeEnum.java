/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import org.openqa.selenium.WebElement;

/**
 * Describes by which criteria a {@link WebElement} shall be selected if some text for the criteria is given.
 */
@SuppressWarnings("javadoc")
public enum SelectTypeEnum {
                            SELECT_BY_INDEX,
                            SELECT_BY_VALUE,
                            SELECT_BY_VISIBILE_TEXT,
}