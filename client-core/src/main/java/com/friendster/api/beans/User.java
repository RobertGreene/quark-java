package com.friendster.api.beans;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class User {

	@Element(name = "uid", required = false)
	private String uid;

	@Element(name = "first_name", required = false, data = false)
	private String firstName;

	@Element(name = "last_name", required = false)
	private String lastName;

	@Element(name = "url", required = false)
	private String url;

	@Element(name = "primary_photo_url", required = false)
	private String primaryPhotoUrl;

	@Element(name = "level", required = false)
	private String level;

	@Element(name = "location", required = false)
	private Location location;

	@Element(name = "hometown", required = false)
	private String hometown;

	@Element(name = "user_type", required = false)
	private String userType;

	@Element(name = "fan_profile_type", required = false)
	private String fanProfileType;

	@Element(name = "fan_profile_category", required = false)
	private String fanProfileCategory;

	@Element(name = "relationship_status", required = false)
	private String relationshipStatus;

	@Element(name = "gender", required = false)
	private String gender;

	@Element(name = "member_since", required = false)
	private MemberSince memberSince;

	@ElementList(entry = "interest", name = "interested_in", required = false)
	private List<String> interest;

	@Element(name = "occupation", required = false)
	private String occupation;

	@Element(name = "hobbies_and_interests", required = false)
	private String hobbiesAndInterests;

	@Element(name = "affiliations", required = false)
	private String affiliations;

	@ElementList(entry = "college", name = "college_list", required = false)
	private List<College> college;

	@ElementList(entry = "school", name = "school_list", required = false)
	private List<School> school;

	@Element(name = "school_other", required = false)
	private String schoolOther;

	@Element(name = "favorites", required = false)
	private String favorites;

	@Element(name = "about_me", required = false)
	private String aboutMe;

	@Element(name = "want_to_meet", required = false)
	private String wantToMeet;

	@Element(name = "birthday", required = false)
	private String birthday;

	@Element(name = "fb_id", required = false)
	private String fbId;

	@Element(name = "admin", required = false)
	private String admin;

	@Element(name = "guest", required = false)
	private String guest;
	
	@Element(name = "guild_id", required = false)
	private String guildId;
	
	@Element(name = "guild_name", required = false)
	private String guildName;

	public String getUid() {
		return uid;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUrl() {
		return url;
	}

	public String getPrimaryPhotoUrl() {
		return primaryPhotoUrl;
	}

	public String getLevel() {
		return level;
	}

	public Location getLocation() {
		return location;
	}

	public String getHometown() {
		return hometown;
	}

	public String getUserType() {
		return userType;
	}

	public String getFanProfileType() {
		return fanProfileType;
	}

	public String getFanProfileCategory() {
		return fanProfileCategory;
	}

	public String getRelationshipStatus() {
		return relationshipStatus;
	}

	public String getGender() {
		return gender;
	}

	public MemberSince getMemberSince() {

		return memberSince;
	}

	public List<String> getInterest() {
		return interest;
	}

	public String getOccupation() {
		return occupation;
	}

	public String getHobbiesAndInterests() {
		return hobbiesAndInterests;
	}

	public String getAffiliations() {
		return affiliations;
	}

	public List<College> getCollege() {
		return college;
	}

	public List<School> getSchool() {
		return school;
	}

	public String getSchoolOther() {
		return schoolOther;
	}

	public String getFavorites() {
		return favorites;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public String getWantToMeet() {
		return wantToMeet;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getFbId() {
		return fbId;
	}

	public String getAdmin() {
		return admin;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPrimaryPhotoUrl(String primaryPhotoUrl) {
		this.primaryPhotoUrl = primaryPhotoUrl;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setFanProfileType(String fanProfileType) {
		this.fanProfileType = fanProfileType;
	}

	public void setFanProfileCategory(String fanProfileCategory) {
		this.fanProfileCategory = fanProfileCategory;
	}

	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setMemberSince(MemberSince memberSince) {
		this.memberSince = memberSince;
	}

	public void setInterest(List<String> interest) {
		this.interest = interest;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public void setHobbiesAndInterests(String hobbiesAndInterests) {
		this.hobbiesAndInterests = hobbiesAndInterests;
	}

	public void setAffiliations(String affiliations) {
		this.affiliations = affiliations;
	}

	public void setCollege(List<College> college) {
		this.college = college;
	}

	public void setSchool(List<School> school) {
		this.school = school;
	}

	public void setSchoolOther(String schoolOther) {
		this.schoolOther = schoolOther;
	}

	public void setFavorites(String favorites) {
		this.favorites = favorites;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public void setWantToMeet(String wantToMeet) {
		this.wantToMeet = wantToMeet;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setFbId(String fbId) {
		this.fbId = fbId;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public String getGuildId() {
		return guildId;
	}

	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}
}
