package com.vcg.community.mapping;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(indexName = "index_v4",type = "resource",shards = 12,replicas = 1)
public class Photo  implements Serializable {

	private static final long serialVersionUID = 1L;

	//图片ID
	private String id;

	//图片标题
	private String title;

	//图片描述
	private String description;

	//licenceId
	private String licenceId;

	//上传者ID
	private String uploaderId;

	//忽略，上传时间
	private Date createdDate;

	//拍摄时间
	private Date uploadedDate;

	//图片地址
	private String url;

	//图片类型
	private int resourceType;

	//图片ID
	private String resourceId;

	//分类ID
	private String categoryId;

	//上传者名字
	private String uploaderName;

	//图片宽度
	@Field(type =FieldType.Integer)
	private int width;

	//图片高度
	private int height;

	//图片来源 andriod ,web,ios
	private String origin;

	//原始ID
	private int originId;

	//热度值
	@Field(type =FieldType.Double)
	private double rating ;

	@Field(type =FieldType.Double)
	private double rating2 ;

	//最高热度值时间
	private Date ratingMaxDate;

	//最高热度值
	@Field(type =FieldType.Double)
	private double ratingMax ;

	@Field(type =FieldType.Double)
	private double ratingMax2 ;

	//图片私有性质
	@Field(type =FieldType.Integer )
	private int privacy;

	//refer
	private String refer;

	//图片类型
	private String openState;

	//是否有封面
	private int hasCover;

	//组图里面的id
	private List<JSONObject> photos;

	//组图数量
	private int photoCount;

	private Date profileSortTime;

	//图片状态
	@Field(type =FieldType.Integer )
	private int state;

	//入库时间
	private Date createdTime;

	//图片关键词标签
	private List<String> tag;

	//部落标签
	private List<String> tribeTags;

	//大赛标签
	private List<String> contestTags;

	//地理位置信息
	@GeoPointField
	private GeoCoordinates geoCoordinates;

	//天气信息
	private String weather;

	//地理位置信息,字符串信息
	private String location;

	//相机的exif信息
	private JSONObject exifInfo;

	@Field(type =FieldType.Nested )
	private List<Keyword> keywords;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLicenceId() {
		return licenceId;
	}

	public void setLicenceId(String licenceId) {
		this.licenceId = licenceId;
	}

	public String getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(String uploaderId) {
		this.uploaderId = uploaderId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getResourceType() {
		return resourceType;
	}

	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getUploaderName() {
		return uploaderName;
	}

	public void setUploaderName(String uploaderName) {
		this.uploaderName = uploaderName;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getOriginId() {
		return originId;
	}

	public void setOriginId(int originId) {
		this.originId = originId;
	}

	public Date getRatingMaxDate() {
		return ratingMaxDate;
	}

	public void setRatingMaxDate(Date ratingMaxDate) {
		this.ratingMaxDate = ratingMaxDate;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getRatingMax() {
		return ratingMax;
	}

	public void setRatingMax(double ratingMax) {
		this.ratingMax = ratingMax;
	}

	public int getPrivacy() {
		return privacy;
	}

	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getOpenState() {
		return openState;
	}

	public void setOpenState(String openState) {
		this.openState = openState;
	}

	public int getHasCover() {
		return hasCover;
	}

	public void setHasCover(int hasCover) {
		this.hasCover = hasCover;
	}

	public List<JSONObject> getPhotos() {
		return photos;
	}

	public void setPhotos(List<JSONObject> photos) {
		this.photos = photos;
	}

	public int getPhotoCount() {
		return photoCount;
	}

	public void setPhotoCount(int photoCount) {
		this.photoCount = photoCount;
	}

	public Date getProfileSortTime() {
		return profileSortTime;
	}

	public void setProfileSortTime(Date profileSortTime) {
		this.profileSortTime = profileSortTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public List<String> getTag() {
		return tag;
	}

	public void setTag(List<String> tag) {
		this.tag = tag;
	}

	public List<String> getTribeTags() {
		return tribeTags;
	}

	public void setTribeTags(List<String> tribeTags) {
		this.tribeTags = tribeTags;
	}

	public List<String> getContestTags() {
		return contestTags;
	}

	public void setContestTags(List<String> contestTags) {
		this.contestTags = contestTags;
	}

	public GeoCoordinates getGeoCoordinates() {
		return geoCoordinates;
	}

	public void setGeoCoordinates(GeoCoordinates geoCoordinates) {
		this.geoCoordinates = geoCoordinates;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public JSONObject getExifInfo() {
		return exifInfo;
	}

	public void setExifInfo(JSONObject exifInfo) {
		this.exifInfo = exifInfo;
	}

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

	public double getRating2() {
		return rating2;
	}

	public void setRating2(double rating2) {
		this.rating2 = rating2;
	}

	public double getRatingMax2() {
		return ratingMax2;
	}

	public void setRatingMax2(double ratingMax2) {
		this.ratingMax2 = ratingMax2;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
