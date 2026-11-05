package com.hanbit.inquiry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private String urgency;

    @Column(length = 500)
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String draft;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InquiryStatus status;

    protected Inquiry() {
    }

    public Inquiry(String category, String content, String urgency, String summary, String draft) {
        this.category = category;
        this.content = content;
        this.urgency = urgency;
        this.summary = summary;
        this.draft = draft;
        this.status = InquiryStatus.RECEIVED;
    }

    public void approve(String editedDraft) {
        if (status != InquiryStatus.RECEIVED) {
            throw new IllegalStateException("접수 상태의 문의만 승인할 수 있습니다.");
        }
        if (editedDraft != null && !editedDraft.isBlank()) {
            this.draft = editedDraft;
        }
        this.status = InquiryStatus.APPROVED;
    }

    public void markSent() {
        if (status != InquiryStatus.APPROVED) {
            throw new IllegalStateException("승인된 문의만 발송 완료로 바꿀 수 있습니다.");
        }
        this.status = InquiryStatus.SENT;
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getContent() {
        return content;
    }

    public String getUrgency() {
        return urgency;
    }

    public String getSummary() {
        return summary;
    }

    public String getDraft() {
        return draft;
    }

    public InquiryStatus getStatus() {
        return status;
    }
}
