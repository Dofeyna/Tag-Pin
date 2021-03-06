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

import com.google.gdata.util.common.xml.XmlNamespace;
import com.google.gdata.data.BaseEntry;
import com.google.gdata.data.ExtensionDescription;
import com.google.gdata.data.ExtensionProfile;
import com.google.gdata.data.Link;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.ExtendedProperty;
import com.google.gdata.data.extensions.Im;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.Organization;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gdata.data.extensions.PostalAddress;
import com.google.gdata.data.extensions.StructuredPostalAddress;
import com.google.gdata.data.extensions.Where;

import java.util.List;

/**
 * Describes a person entry.
 *
 * @param <E> concrete entry type
 * 
 */
public abstract class BasePersonEntry<E extends BasePersonEntry> extends
    BaseEntry<E> {

  /**
   * Default mutable constructor.
   */
  public BasePersonEntry() {
    super();
  }

  /**
   * Constructs a new instance by doing a shallow copy of data from an existing
   * {@link BaseEntry} instance.
   *
   * @param sourceEntry source entry
   */
  public BasePersonEntry(BaseEntry<?> sourceEntry) {
    super(sourceEntry);
  }

  @Override
  public void declareExtensions(ExtensionProfile extProfile) {
    if (extProfile.isDeclared(BasePersonEntry.class)) {
      return;
    }
    super.declareExtensions(extProfile);
    extProfile.declare(BasePersonEntry.class, BillingInformation.class);
    extProfile.declare(BasePersonEntry.class, Birthday.class);
    extProfile.declare(BasePersonEntry.class,
        CalendarLink.getDefaultDescription(false, true));
    extProfile.declare(BasePersonEntry.class, DirectoryServer.class);
    extProfile.declare(BasePersonEntry.class,
        new ExtensionDescription(Email.class, new XmlNamespace("gd",
        "http://schemas.google.com/g/2005"), "email", false, true, false));
    extProfile.declare(BasePersonEntry.class, Event.getDefaultDescription(false,
        true));
    new Event().declareExtensions(extProfile);
    extProfile.declare(BasePersonEntry.class,
        new ExtensionDescription(ExtendedProperty.class, new XmlNamespace("gd",
        "http://schemas.google.com/g/2005"), "extendedProperty", false, true,
        false));
    extProfile.declare(BasePersonEntry.class,
        ExternalId.getDefaultDescription(false, true));
    extProfile.declare(BasePersonEntry.class, Gender.class);
    extProfile.declare(BasePersonEntry.class, Hobby.getDefaultDescription(false,
        true));
    extProfile.declare(BasePersonEntry.class, Im.getDefaultDescription(false,
        true));
    extProfile.declare(BasePersonEntry.class, Initials.class);
    extProfile.declare(BasePersonEntry.class, Jot.getDefaultDescription(false,
        true));
    extProfile.declare(BasePersonEntry.class,
        Language.getDefaultDescription(false, true));
    extProfile.declare(BasePersonEntry.class, MaidenName.class);
    extProfile.declare(BasePersonEntry.class, Mileage.class);
    extProfile.declare(BasePersonEntry.class, Name.class);
    new Name().declareExtensions(extProfile);
    extProfile.declare(BasePersonEntry.class, Nickname.class);
    extProfile.declare(BasePersonEntry.class, Occupation.class);
    extProfile.declare(BasePersonEntry.class,
        Organization.getDefaultDescription(false, true));
    new Organization().declareExtensions(extProfile);
    extProfile.declare(BasePersonEntry.class,
        new ExtensionDescription(PhoneNumber.class, new XmlNamespace("gd",
        "http://schemas.google.com/g/2005"), "phoneNumber", false, true,
        false));
    extProfile.declare(BasePersonEntry.class,
        new ExtensionDescription(PostalAddress.class, new XmlNamespace("gd",
        "http://schemas.google.com/g/2005"), "postalAddress", false, true,
        false));
    extProfile.declare(BasePersonEntry.class, Priority.class);
    extProfile.declare(BasePersonEntry.class,
        Relation.getDefaultDescription(false, true));
    extProfile.declare(BasePersonEntry.class, Sensitivity.class);
    extProfile.declare(BasePersonEntry.class, ShortName.class);
    extProfile.declare(BasePersonEntry.class,
        StructuredPostalAddress.getDefaultDescription(false, true));
    new StructuredPostalAddress().declareExtensions(extProfile);
    extProfile.declare(BasePersonEntry.class, Subject.class);
    extProfile.declare(BasePersonEntry.class,
        UserDefinedField.getDefaultDescription(false, true));
    extProfile.declare(BasePersonEntry.class,
        Website.getDefaultDescription(false, true));
    extProfile.declare(BasePersonEntry.class,
        new ExtensionDescription(Where.class, new XmlNamespace("gd",
        "http://schemas.google.com/g/2005"), "where", false, false, false));
    new Where().declareExtensions(extProfile);
  }

  /**
   * Returns the billing information.
   *
   * @return billing information
   */
  public BillingInformation getBillingInformation() {
    return getExtension(BillingInformation.class);
  }

  /**
   * Sets the billing information.
   *
   * @param billingInformation billing information or <code>null</code> to reset
   */
  public void setBillingInformation(BillingInformation billingInformation) {
    if (billingInformation == null) {
      removeExtension(BillingInformation.class);
    } else {
      setExtension(billingInformation);
    }
  }

  /**
   * Returns whether it has the billing information.
   *
   * @return whether it has the billing information
   */
  public boolean hasBillingInformation() {
    return hasExtension(BillingInformation.class);
  }

  /**
   * Returns the birthday.
   *
   * @return birthday
   */
  public Birthday getBirthday() {
    return getExtension(Birthday.class);
  }

  /**
   * Sets the birthday.
   *
   * @param birthday birthday or <code>null</code> to reset
   */
  public void setBirthday(Birthday birthday) {
    if (birthday == null) {
      removeExtension(Birthday.class);
    } else {
      setExtension(birthday);
    }
  }

  /**
   * Returns whether it has the birthday.
   *
   * @return whether it has the birthday
   */
  public boolean hasBirthday() {
    return hasExtension(Birthday.class);
  }

  /**
   * Returns the calendar links.
   *
   * @return calendar links
   */
  public List<CalendarLink> getCalendarLinks() {
    return getRepeatingExtension(CalendarLink.class);
  }

  /**
   * Adds a new calendar link.
   *
   * @param calendarLink calendar link
   */
  public void addCalendarLink(CalendarLink calendarLink) {
    getCalendarLinks().add(calendarLink);
  }

  /**
   * Returns whether it has the calendar links.
   *
   * @return whether it has the calendar links
   */
  public boolean hasCalendarLinks() {
    return hasRepeatingExtension(CalendarLink.class);
  }

  /**
   * Returns the directory server.
   *
   * @return directory server
   */
  public DirectoryServer getDirectoryServer() {
    return getExtension(DirectoryServer.class);
  }

  /**
   * Sets the directory server.
   *
   * @param directoryServer directory server or <code>null</code> to reset
   */
  public void setDirectoryServer(DirectoryServer directoryServer) {
    if (directoryServer == null) {
      removeExtension(DirectoryServer.class);
    } else {
      setExtension(directoryServer);
    }
  }

  /**
   * Returns whether it has the directory server.
   *
   * @return whether it has the directory server
   */
  public boolean hasDirectoryServer() {
    return hasExtension(DirectoryServer.class);
  }

  /**
   * Returns the email addresses.
   *
   * @return email addresses
   */
  public List<Email> getEmailAddresses() {
    return getRepeatingExtension(Email.class);
  }

  /**
   * Adds a new email address.
   *
   * @param emailAddress email address
   */
  public void addEmailAddress(Email emailAddress) {
    getEmailAddresses().add(emailAddress);
  }

  /**
   * Returns whether it has the email addresses.
   *
   * @return whether it has the email addresses
   */
  public boolean hasEmailAddresses() {
    return hasRepeatingExtension(Email.class);
  }

  /**
   * Returns the events.
   *
   * @return events
   */
  public List<Event> getEvents() {
    return getRepeatingExtension(Event.class);
  }

  /**
   * Adds a new event.
   *
   * @param event event
   */
  public void addEvent(Event event) {
    getEvents().add(event);
  }

  /**
   * Returns whether it has the events.
   *
   * @return whether it has the events
   */
  public boolean hasEvents() {
    return hasRepeatingExtension(Event.class);
  }

  /**
   * Returns the extended properties.
   *
   * @return extended properties
   */
  public List<ExtendedProperty> getExtendedProperties() {
    return getRepeatingExtension(ExtendedProperty.class);
  }

  /**
   * Adds a new extended property.
   *
   * @param extendedProperty extended property
   */
  public void addExtendedProperty(ExtendedProperty extendedProperty) {
    getExtendedProperties().add(extendedProperty);
  }

  /**
   * Returns whether it has the extended properties.
   *
   * @return whether it has the extended properties
   */
  public boolean hasExtendedProperties() {
    return hasRepeatingExtension(ExtendedProperty.class);
  }

  /**
   * Returns the external ids.
   *
   * @return external ids
   */
  public List<ExternalId> getExternalIds() {
    return getRepeatingExtension(ExternalId.class);
  }

  /**
   * Adds a new external id.
   *
   * @param externalId external id
   */
  public void addExternalId(ExternalId externalId) {
    getExternalIds().add(externalId);
  }

  /**
   * Returns whether it has the external ids.
   *
   * @return whether it has the external ids
   */
  public boolean hasExternalIds() {
    return hasRepeatingExtension(ExternalId.class);
  }

  /**
   * Returns the gender.
   *
   * @return gender
   */
  public Gender getGender() {
    return getExtension(Gender.class);
  }

  /**
   * Sets the gender.
   *
   * @param gender gender or <code>null</code> to reset
   */
  public void setGender(Gender gender) {
    if (gender == null) {
      removeExtension(Gender.class);
    } else {
      setExtension(gender);
    }
  }

  /**
   * Returns whether it has the gender.
   *
   * @return whether it has the gender
   */
  public boolean hasGender() {
    return hasExtension(Gender.class);
  }

  /**
   * Returns the hobbies.
   *
   * @return hobbies
   */
  public List<Hobby> getHobbies() {
    return getRepeatingExtension(Hobby.class);
  }

  /**
   * Adds a new hobby.
   *
   * @param hobby hobby
   */
  public void addHobby(Hobby hobby) {
    getHobbies().add(hobby);
  }

  /**
   * Returns whether it has the hobbies.
   *
   * @return whether it has the hobbies
   */
  public boolean hasHobbies() {
    return hasRepeatingExtension(Hobby.class);
  }

  /**
   * Returns the instant messaging addresses.
   *
   * @return instant messaging addresses
   */
  public List<Im> getImAddresses() {
    return getRepeatingExtension(Im.class);
  }

  /**
   * Adds a new instant messaging address.
   *
   * @param imAddress instant messaging address
   */
  public void addImAddress(Im imAddress) {
    getImAddresses().add(imAddress);
  }

  /**
   * Returns whether it has the instant messaging addresses.
   *
   * @return whether it has the instant messaging addresses
   */
  public boolean hasImAddresses() {
    return hasRepeatingExtension(Im.class);
  }

  /**
   * Returns the initials.
   *
   * @return initials
   */
  public Initials getInitials() {
    return getExtension(Initials.class);
  }

  /**
   * Sets the initials.
   *
   * @param initials initials or <code>null</code> to reset
   */
  public void setInitials(Initials initials) {
    if (initials == null) {
      removeExtension(Initials.class);
    } else {
      setExtension(initials);
    }
  }

  /**
   * Returns whether it has the initials.
   *
   * @return whether it has the initials
   */
  public boolean hasInitials() {
    return hasExtension(Initials.class);
  }

  /**
   * Returns the jots.
   *
   * @return jots
   */
  public List<Jot> getJots() {
    return getRepeatingExtension(Jot.class);
  }

  /**
   * Adds a new jot.
   *
   * @param jot jot
   */
  public void addJot(Jot jot) {
    getJots().add(jot);
  }

  /**
   * Returns whether it has the jots.
   *
   * @return whether it has the jots
   */
  public boolean hasJots() {
    return hasRepeatingExtension(Jot.class);
  }

  /**
   * Returns the languages.
   *
   * @return languages
   */
  public List<Language> getLanguages() {
    return getRepeatingExtension(Language.class);
  }

  /**
   * Adds a new language.
   *
   * @param language language
   */
  public void addLanguage(Language language) {
    getLanguages().add(language);
  }

  /**
   * Returns whether it has the languages.
   *
   * @return whether it has the languages
   */
  public boolean hasLanguages() {
    return hasRepeatingExtension(Language.class);
  }

  /**
   * Returns the maiden name.
   *
   * @return maiden name
   */
  public MaidenName getMaidenName() {
    return getExtension(MaidenName.class);
  }

  /**
   * Sets the maiden name.
   *
   * @param maidenName maiden name or <code>null</code> to reset
   */
  public void setMaidenName(MaidenName maidenName) {
    if (maidenName == null) {
      removeExtension(MaidenName.class);
    } else {
      setExtension(maidenName);
    }
  }

  /**
   * Returns whether it has the maiden name.
   *
   * @return whether it has the maiden name
   */
  public boolean hasMaidenName() {
    return hasExtension(MaidenName.class);
  }

  /**
   * Returns the mileage.
   *
   * @return mileage
   */
  public Mileage getMileage() {
    return getExtension(Mileage.class);
  }

  /**
   * Sets the mileage.
   *
   * @param mileage mileage or <code>null</code> to reset
   */
  public void setMileage(Mileage mileage) {
    if (mileage == null) {
      removeExtension(Mileage.class);
    } else {
      setExtension(mileage);
    }
  }

  /**
   * Returns whether it has the mileage.
   *
   * @return whether it has the mileage
   */
  public boolean hasMileage() {
    return hasExtension(Mileage.class);
  }

  /**
   * Returns the name.
   *
   * @return name
   */
  public Name getName() {
    return getExtension(Name.class);
  }

  /**
   * Sets the name.
   *
   * @param name name or <code>null</code> to reset
   */
  public void setName(Name name) {
    if (name == null) {
      removeExtension(Name.class);
    } else {
      setExtension(name);
    }
  }

  /**
   * Returns whether it has the name.
   *
   * @return whether it has the name
   */
  public boolean hasName() {
    return hasExtension(Name.class);
  }

  /**
   * Returns the nickname.
   *
   * @return nickname
   */
  public Nickname getNickname() {
    return getExtension(Nickname.class);
  }

  /**
   * Sets the nickname.
   *
   * @param nickname nickname or <code>null</code> to reset
   */
  public void setNickname(Nickname nickname) {
    if (nickname == null) {
      removeExtension(Nickname.class);
    } else {
      setExtension(nickname);
    }
  }

  /**
   * Returns whether it has the nickname.
   *
   * @return whether it has the nickname
   */
  public boolean hasNickname() {
    return hasExtension(Nickname.class);
  }

  /**
   * Returns the occupation.
   *
   * @return occupation
   */
  public Occupation getOccupation() {
    return getExtension(Occupation.class);
  }

  /**
   * Sets the occupation.
   *
   * @param occupation occupation or <code>null</code> to reset
   */
  public void setOccupation(Occupation occupation) {
    if (occupation == null) {
      removeExtension(Occupation.class);
    } else {
      setExtension(occupation);
    }
  }

  /**
   * Returns whether it has the occupation.
   *
   * @return whether it has the occupation
   */
  public boolean hasOccupation() {
    return hasExtension(Occupation.class);
  }

  /**
   * Returns the organizations.
   *
   * @return organizations
   */
  public List<Organization> getOrganizations() {
    return getRepeatingExtension(Organization.class);
  }

  /**
   * Adds a new organization.
   *
   * @param organization organization
   */
  public void addOrganization(Organization organization) {
    getOrganizations().add(organization);
  }

  /**
   * Returns whether it has the organizations.
   *
   * @return whether it has the organizations
   */
  public boolean hasOrganizations() {
    return hasRepeatingExtension(Organization.class);
  }

  /**
   * Returns the phone numbers.
   *
   * @return phone numbers
   */
  public List<PhoneNumber> getPhoneNumbers() {
    return getRepeatingExtension(PhoneNumber.class);
  }

  /**
   * Adds a new phone number.
   *
   * @param phoneNumber phone number
   */
  public void addPhoneNumber(PhoneNumber phoneNumber) {
    getPhoneNumbers().add(phoneNumber);
  }

  /**
   * Returns whether it has the phone numbers.
   *
   * @return whether it has the phone numbers
   */
  public boolean hasPhoneNumbers() {
    return hasRepeatingExtension(PhoneNumber.class);
  }

  /**
   * Returns the postal addresses.
   *
   * @return postal addresses
   */
  public List<PostalAddress> getPostalAddresses() {
    return getRepeatingExtension(PostalAddress.class);
  }

  /**
   * Adds a new postal address.
   *
   * @param postalAddress postal address
   */
  public void addPostalAddress(PostalAddress postalAddress) {
    getPostalAddresses().add(postalAddress);
  }

  /**
   * Returns whether it has the postal addresses.
   *
   * @return whether it has the postal addresses
   */
  public boolean hasPostalAddresses() {
    return hasRepeatingExtension(PostalAddress.class);
  }

  /**
   * Returns the priority.
   *
   * @return priority
   */
  public Priority getPriority() {
    return getExtension(Priority.class);
  }

  /**
   * Sets the priority.
   *
   * @param priority priority or <code>null</code> to reset
   */
  public void setPriority(Priority priority) {
    if (priority == null) {
      removeExtension(Priority.class);
    } else {
      setExtension(priority);
    }
  }

  /**
   * Returns whether it has the priority.
   *
   * @return whether it has the priority
   */
  public boolean hasPriority() {
    return hasExtension(Priority.class);
  }

  /**
   * Returns the relations.
   *
   * @return relations
   */
  public List<Relation> getRelations() {
    return getRepeatingExtension(Relation.class);
  }

  /**
   * Adds a new relation.
   *
   * @param relation relation
   */
  public void addRelation(Relation relation) {
    getRelations().add(relation);
  }

  /**
   * Returns whether it has the relations.
   *
   * @return whether it has the relations
   */
  public boolean hasRelations() {
    return hasRepeatingExtension(Relation.class);
  }

  /**
   * Returns the sensitivity.
   *
   * @return sensitivity
   */
  public Sensitivity getSensitivity() {
    return getExtension(Sensitivity.class);
  }

  /**
   * Sets the sensitivity.
   *
   * @param sensitivity sensitivity or <code>null</code> to reset
   */
  public void setSensitivity(Sensitivity sensitivity) {
    if (sensitivity == null) {
      removeExtension(Sensitivity.class);
    } else {
      setExtension(sensitivity);
    }
  }

  /**
   * Returns whether it has the sensitivity.
   *
   * @return whether it has the sensitivity
   */
  public boolean hasSensitivity() {
    return hasExtension(Sensitivity.class);
  }

  /**
   * Returns the short name.
   *
   * @return short name
   */
  public ShortName getShortName() {
    return getExtension(ShortName.class);
  }

  /**
   * Sets the short name.
   *
   * @param shortName short name or <code>null</code> to reset
   */
  public void setShortName(ShortName shortName) {
    if (shortName == null) {
      removeExtension(ShortName.class);
    } else {
      setExtension(shortName);
    }
  }

  /**
   * Returns whether it has the short name.
   *
   * @return whether it has the short name
   */
  public boolean hasShortName() {
    return hasExtension(ShortName.class);
  }

  /**
   * Returns the structured postal addresses.
   *
   * @return structured postal addresses
   */
  public List<StructuredPostalAddress> getStructuredPostalAddresses() {
    return getRepeatingExtension(StructuredPostalAddress.class);
  }

  /**
   * Adds a new structured postal address.
   *
   * @param structuredPostalAddress structured postal address
   */
  public void addStructuredPostalAddress(StructuredPostalAddress
      structuredPostalAddress) {
    getStructuredPostalAddresses().add(structuredPostalAddress);
  }

  /**
   * Returns whether it has the structured postal addresses.
   *
   * @return whether it has the structured postal addresses
   */
  public boolean hasStructuredPostalAddresses() {
    return hasRepeatingExtension(StructuredPostalAddress.class);
  }

  /**
   * Returns the subject.
   *
   * @return subject
   */
  public Subject getSubject() {
    return getExtension(Subject.class);
  }

  /**
   * Sets the subject.
   *
   * @param subject subject or <code>null</code> to reset
   */
  public void setSubject(Subject subject) {
    if (subject == null) {
      removeExtension(Subject.class);
    } else {
      setExtension(subject);
    }
  }

  /**
   * Returns whether it has the subject.
   *
   * @return whether it has the subject
   */
  public boolean hasSubject() {
    return hasExtension(Subject.class);
  }

  /**
   * Returns the user defined fields.
   *
   * @return user defined fields
   */
  public List<UserDefinedField> getUserDefinedFields() {
    return getRepeatingExtension(UserDefinedField.class);
  }

  /**
   * Adds a new user defined field.
   *
   * @param userDefinedField user defined field
   */
  public void addUserDefinedField(UserDefinedField userDefinedField) {
    getUserDefinedFields().add(userDefinedField);
  }

  /**
   * Returns whether it has the user defined fields.
   *
   * @return whether it has the user defined fields
   */
  public boolean hasUserDefinedFields() {
    return hasRepeatingExtension(UserDefinedField.class);
  }

  /**
   * Returns the websites.
   *
   * @return websites
   */
  public List<Website> getWebsites() {
    return getRepeatingExtension(Website.class);
  }

  /**
   * Adds a new website.
   *
   * @param website website
   */
  public void addWebsite(Website website) {
    getWebsites().add(website);
  }

  /**
   * Returns whether it has the websites.
   *
   * @return whether it has the websites
   */
  public boolean hasWebsites() {
    return hasRepeatingExtension(Website.class);
  }

  /**
   * Returns the person location.
   *
   * @return person location
   */
  public Where getWhere() {
    return getExtension(Where.class);
  }

  /**
   * Sets the person location.
   *
   * @param where person location or <code>null</code> to reset
   */
  public void setWhere(Where where) {
    if (where == null) {
      removeExtension(Where.class);
    } else {
      setExtension(where);
    }
  }

  /**
   * Returns whether it has the person location.
   *
   * @return whether it has the person location
   */
  public boolean hasWhere() {
    return hasExtension(Where.class);
  }

  /**
   * Returns the link to edit contact photo.
   *
   * @return Link to edit contact photo or {@code null} for none.
   */
  public Link getContactEditPhotoLink() {
    return getLink(ContactLink.Rel.EDIT_CONTACT_PHOTO, ContactLink.Type.IMAGE);
  }

  /**
   * Returns the link that provides the contact photo.
   *
   * @return Link that provides the contact photo or {@code null} for none.
   */
  public Link getContactPhotoLink() {
    return getLink(ContactLink.Rel.CONTACT_PHOTO, ContactLink.Type.IMAGE);
  }

  @Override
  protected void validate() {
  }

  @Override
  public String toString() {
    return "{BasePersonEntry " + super.toString() + "}";
  }

}
