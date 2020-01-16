package cn.mycommons.easyfeedback.entity;

public enum FeedbackStatus {

    /**
     * 未知
     */
    Unknown(0),
    /**
     * 已创建
     */
    Created(1),
    /**
     * 处理中
     */
    Processing(2),
    /**
     * 已处理
     */
    Done(3),
    /**
     * 拒绝
     */
    Reject(4);

    private final int status;

    FeedbackStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "FeedbackStatus{" + "status=" + status + '}';
    }
}