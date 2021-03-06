/* Copyright (c) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.google.gdata.data.contacts;

import com.google.gdata.data.ExtensionDescription;
import com.google.gdata.data.ValueConstruct;

/**
 * Contact's birth date.
 *
 * 
 */
@ExtensionDescription.Default(
    nsAlias = ContactsNamespace.GCONTACT_ALIAS,
    nsUri = ContactsNamespace.GCONTACT,
    localName = Birthday.XML_NAME)
public class Birthday extends ValueConstruct {

  /** XML element name */
  static final String XML_NAME = "birthday";

  /** XML "when" attribute name */
  private static final String WHEN = "when";

  /**
   * Default mutable constructor.
   */
  public Birthday() {
    this(null);
  }

  /**
   * Constructor (mutable or immutable).
   *
   * @param when immutable birth date or <code>null</code> for a mutable birth
   *     date
   */
  public Birthday(String when) {
    super(ContactsNamespace.GCONTACT_NS, XML_NAME, WHEN, when);
    setRequired(false);
  }

  /**
   * Returns the birth date.
   *
   * @return birth date
   */
  public String getWhen() {
    return getValue();
  }

  /**
   * Sets the birth date.
   *
   * @param when birth date or <code>null</code> to reset
   */
  public void setWhen(String when) {
    setValue(when);
  }

  /**
   * Returns whether it has the birth date.
   *
   * @return whether it has the birth date
   */
  public boolean hasWhen() {
    return hasValue();
  }

  /**
   * Returns the extension description, specifying whether it is required, and
   * whether it is repeatable.
   *
   * @param required   whether it is required
   * @param repeatable whether it is repeatable
   * @return extension description
   */
  public static ExtensionDescription getDefaultDescription(boolean required,
      boolean repeatable) {
    ExtensionDescription desc =
        ExtensionDescription.getDefaultDescription(Birthday.class);
    desc.setRequired(required);
    desc.setRepeatable(repeatable);
    return desc;
  }

  @Override
  public String toString() {
    return "{Birthday when=" + getValue() + "}";
  }

}
