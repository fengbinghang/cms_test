package com.fbh.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fbh.enums.ContentType;

/**
 * 
 * @ClassName: Article
 * @Description: 文章内容表
 * @author:冯炳航
 * @date: 2020年1月7日 下午1:07:22
 */
@Document(indexName = "text_cms", type = "Article")
public class Article implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 序列化id
	 */
	private static final long serialVersionUID = -7698107034367765100L;

	public Article() {
		super();
	}

	// 文章id
	@Id
	private Integer id;
	// 标题
	@Field(index = true, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.text)
	private String title;
	// 文章内容
	@Field(index = true, store = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.text)
	private String content;
	// 文章标题图片
	private String picture;
	// 栏目
	private Channel ch;
	// 类型
	private Category ca;
	// 发布人
	private User u;
	private String username;

	// 点击量
	private Integer hits;
	// 是否上热门 0=不上 1=上
	private Integer hot;
	// 是否审核通过 0=待审 -1=不通过 1=通过
	private Integer status;
	// 是否有效 0=失效 1=有效
	private Integer deleted;
	// 创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created;
	// 最后一次修改时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updated;
	private ContentType ct;
	private List<Content> cs;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Content> getCs() {
		return cs;
	}

	public void setCs(List<Content> cs) {
		this.cs = cs;
	}

	public ContentType getCt() {
		return ct;
	}

	public void setCt(ContentType ct) {
		this.ct = ct;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Channel getCh() {
		return ch;
	}

	public void setCh(Channel ch) {
		this.ch = ch;
	}

	public Category getCa() {
		return ca;
	}

	public void setCa(Category ca) {
		this.ca = ca;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", picture=" + picture + ", ch=" + ch
				+ ", ca=" + ca + ", u=" + u + ", hits=" + hits + ", hot=" + hot + ", status=" + status + ", deleted="
				+ deleted + ", created=" + created + ", updated=" + updated + "]";
	}

}
