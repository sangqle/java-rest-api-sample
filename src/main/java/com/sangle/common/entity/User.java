package com.sangle.common.entity;

public class User {
    private int id;
    private int userId;
    private int appId;
    private int count;
    private long updatedAt;

    public User() {
        this.updatedAt = System.currentTimeMillis();
    }

    public User(int userId, int appId) {
        this.userId = userId;
        this.appId = appId;
        this.updatedAt = System.currentTimeMillis();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the appId
     */
    public int getAppId() {
        return appId;
    }

    /**
     * @param appId the appId to set
     */
    public void setAppId(int appId) {
        this.appId = appId;
    }

    /**
     * @return the updatedAt
     */
    public long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("id: %s, appId: %s, userId: %s, count: %s, updatedAt: %s", id, appId, userId, count,
                updatedAt);
    }
}
