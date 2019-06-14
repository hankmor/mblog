/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;
import io.jpress.message.MessageKit;
import io.jpress.model.Metadata;
import io.jpress.model.core.JModel;
import io.jpress.model.query.MetaDataQuery;

import java.math.BigInteger;

/**
 *  Auto generated by JPress, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseContent<M extends BaseContent<M>> extends JModel<M> implements IBean {

    public static final String CACHE_NAME = "content";
    public static final String METADATA_TYPE = "content";

    public static final String ACTION_ADD = "content:add";
    public static final String ACTION_DELETE = "content:delete";
    public static final String ACTION_UPDATE = "content:update";

    public void removeCache(Object key) {
        if (key == null) return;
        CacheKit.remove(CACHE_NAME, key);
    }

    public void putCache(Object key, Object value) {
        CacheKit.put(CACHE_NAME, key, value);
    }

    public M getCache(Object key) {
        return CacheKit.get(CACHE_NAME, key);
    }

    public M getCache(Object key, IDataLoader dataloader) {
        return CacheKit.get(CACHE_NAME, key, dataloader);
    }

    public Metadata createMetadata() {
        Metadata md = new Metadata();
        md.setObjectId(getId());
        md.setObjectType(METADATA_TYPE);
        return md;
    }

    public Metadata createMetadata(String key, String value) {
        Metadata md = new Metadata();
        md.setObjectId(getId());
        md.setObjectType(METADATA_TYPE);
        md.setMetaKey(key);
        md.setMetaValue(value);
        return md;
    }

    public boolean saveOrUpdateMetadta(String key, String value) {
        Metadata metadata = MetaDataQuery.me().findByTypeAndIdAndKey(METADATA_TYPE, getId(), key);
        if (metadata == null) {
            metadata = createMetadata(key, value);
            return metadata.save();
        }
        metadata.setMetaValue(value);
        return metadata.update();
    }

    public String metadata(String key) {
        Metadata m = MetaDataQuery.me().findByTypeAndIdAndKey(METADATA_TYPE, getId(), key);
        if (m != null) {
            return m.getMetaValue();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof BaseContent<?>)) {
            return false;
        }

        BaseContent<?> m = (BaseContent<?>) o;
        if (m.getId() == null) {
            return false;
        }

        return m.getId().compareTo(this.getId()) == 0;
    }

    @Override
    public boolean save() {
        boolean saved = super.save();
        if (saved) {
            MessageKit.sendMessage(ACTION_ADD, this);
        }
        return saved;
    }

    @Override
    public boolean delete() {
        boolean deleted = super.delete();
        if (deleted) {
            MessageKit.sendMessage(ACTION_DELETE, this);
        }
        return deleted;
    }

    @Override
    public boolean deleteById(Object idValue) {
        boolean deleted = super.deleteById(idValue);
        if (deleted) {
            MessageKit.sendMessage(ACTION_DELETE, this);
        }
        return deleted;
    }

    @Override
    public boolean update() {
        boolean update = super.update();
        if (update) {
            MessageKit.sendMessage(ACTION_UPDATE, this);
        }
        return update;
    }

    public void setId(BigInteger id) {
        set("id", id);
    }

    public BigInteger getId() {
        Object id = get("id");
        if (id == null)
            return null;

        return id instanceof BigInteger ? (BigInteger) id : new BigInteger(id.toString());
    }

    public void setTitle(String title) {
        set("title", title);
    }

    public String getTitle() {
        return get("title");
    }

    public void setText(String text) {
        set("text", text);
    }

    public String getText() {
        return get("text");
    }

    public void setSummary(String summary) {
        set("summary", summary);
    }

    public String getSummary() {
        return get("summary");
    }

    public void setLinkTo(String linkTo) {
        set("link_to", linkTo);
    }

    public String getLinkTo() {
        return get("link_to");
    }

    public void setMarkdownEnable(Boolean markdownEnable) {
        set("markdown_enable", markdownEnable);
    }

    public Boolean getMarkdownEnable() {
        return get("markdown_enable");
    }

    public void setThumbnail(String thumbnail) {
        set("thumbnail", thumbnail);
    }

    public String getThumbnail() {
        return get("thumbnail");
    }

    public void setModule(String module) {
        set("module", module);
    }

    public String getModule() {
        return get("module");
    }

    public void setStyle(String style) {
        set("style", style);
    }

    public String getStyle() {
        return get("style");
    }

    public void setUserId(BigInteger userId) {
        set("user_id", userId);
    }

    public BigInteger getUserId() {
        return get("user_id");
    }

    public void setAuthor(String author) {
        set("author", author);
    }

    public String getAuthor() {
        return get("author");
    }

    public void setUserEmail(String userEmail) {
        set("user_email", userEmail);
    }

    public String getUserEmail() {
        return get("user_email");
    }

    public void setUserIp(String userIp) {
        set("user_ip", userIp);
    }

    public String getUserIp() {
        return get("user_ip");
    }

    public void setUserAgent(String userAgent) {
        set("user_agent", userAgent);
    }

    public String getUserAgent() {
        return get("user_agent");
    }

    public void setParentId(BigInteger parentId) {
        set("parent_id", parentId);
    }

    public BigInteger getParentId() {
        return get("parent_id");
    }

    public void setObjectId(BigInteger objectId) {
        set("object_id", objectId);
    }

    public BigInteger getObjectId() {
        return get("object_id");
    }

    public void setOrderNumber(Long orderNumber) {
        set("order_number", orderNumber);
    }

    public Long getOrderNumber() {
        return get("order_number");
    }

    public void setStatus(String status) {
        set("status", status);
    }

    public String getStatus() {
        return get("status");
    }

    public void setVoteUp(Long voteUp) {
        set("vote_up", voteUp);
    }

    public Long getVoteUp() {
        return get("vote_up");
    }

    public void setVoteDown(Long voteDown) {
        set("vote_down", voteDown);
    }

    public Long getVoteDown() {
        return get("vote_down");
    }

    public void setRate(Integer rate) {
        set("rate", rate);
    }

    public Integer getRate() {
        return get("rate");
    }

    public void setRateCount(Long rateCount) {
        set("rate_count", rateCount);
    }

    public Long getRateCount() {
        return get("rate_count");
    }

    public void setPrice(java.math.BigDecimal price) {
        set("price", price);
    }

    public java.math.BigDecimal getPrice() {
        return get("price");
    }

    public void setCommentStatus(String commentStatus) {
        set("comment_status", commentStatus);
    }

    public String getCommentStatus() {
        return get("comment_status");
    }

    public void setCommentCount(Long commentCount) {
        set("comment_count", commentCount);
    }

    public Long getCommentCount() {
        return get("comment_count");
    }

    public void setCommentTime(java.util.Date commentTime) {
        set("comment_time", commentTime);
    }

    public java.util.Date getCommentTime() {
        return get("comment_time");
    }

    public void setViewCount(Long viewCount) {
        set("view_count", viewCount);
    }

    public Long getViewCount() {
        return get("view_count");
    }

    public void setCreated(java.util.Date created) {
        set("created", created);
    }

    public java.util.Date getCreated() {
        return get("created");
    }

    public void setModified(java.util.Date modified) {
        set("modified", modified);
    }

    public java.util.Date getModified() {
        return get("modified");
    }

    public void setSlug(String slug) {
        set("slug", slug);
    }

    public String getSlug() {
        return get("slug");
    }

    public void setFlag(String flag) {
        set("flag", flag);
    }

    public String getFlag() {
        return get("flag");
    }

    public void setLng(java.math.BigDecimal lng) {
        set("lng", lng);
    }

    public java.math.BigDecimal getLng() {
        return get("lng");
    }

    public void setLat(java.math.BigDecimal lat) {
        set("lat", lat);
    }

    public java.math.BigDecimal getLat() {
        return get("lat");
    }

    public void setMetaKeywords(String metaKeywords) {
        set("meta_keywords", metaKeywords);
    }

    public String getMetaKeywords() {
        return get("meta_keywords");
    }

    public void setMetaDescription(String metaDescription) {
        set("meta_description", metaDescription);
    }

    public String getMetaDescription() {
        return get("meta_description");
    }

    public void setRemarks(String remarks) {
        set("remarks", remarks);
    }

    public String getRemarks() {
        return get("remarks");
    }

}
